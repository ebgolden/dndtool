package common;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

public class LocalNetworkOperator implements Operator {
    @Inject
    @Named("ipAddress")
    protected String ipAddress;
    @Inject
    @Named("port")
    private int port;
    @Inject
    @Named("timeout")
    private int timeout;
    @Inject
    @Named("threadCount")
    private int threadCount;
    @Inject
    @Named("minimumPort")
    private int minimumPort;
    @Inject
    @Named("maximumPort")
    private int maximumPort;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public OperatorResponseQuery getOperatorResponseQuery(OperatorRequestQuery operatorRequestQuery) {
        UUID uuid = UUID.randomUUID();
        String queryId = uuid.toString();
        String playerId = operatorRequestQuery.getPlayerId();
        String apiName = operatorRequestQuery.getApiName();
        String queryType = operatorRequestQuery.getQueryType();
        String requestJson = operatorRequestQuery.getRequestJson();
        OperatorRequestWithQueryIdQuery operatorRequestWithQueryIdQuery = OperatorRequestWithQueryIdQuery
                .builder()
                .queryId(queryId)
                .playerId(playerId)
                .apiName(apiName)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
        try {
            init();
            objectOutputStream.writeObject(operatorRequestWithQueryIdQuery);
        } catch (Exception e) {
            return OperatorResponseQuery
                    .builder()
                    .build();
        }
        String responseJson = waitForResponseAndReturnResponseJson(queryId);
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public OperatorResponseQuery getOperatorResponseQuery(OperatorResponseQuery operatorResponseQuery) {
        String queryId = operatorResponseQuery.getQueryId();
        String responseJson;
        try {
            init();
            objectOutputStream.writeObject(operatorResponseQuery);
            responseJson = waitForResponseAndReturnResponseJson(queryId);
        } catch (Exception e) {
            return OperatorResponseQuery
                    .builder()
                    .build();
        }
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public int[] getOpenPorts() {
        List<Integer> openPorts = new ArrayList<>();
        Map<Integer, Future<Boolean>> portStatusMap = new HashMap<>();
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int port = minimumPort; port <= maximumPort; ++port) {
            Future<Boolean> isPortOpenFuture = pingPort(executorService, port);
            portStatusMap.put(port, isPortOpenFuture);
        }
        portStatusMap.forEach((port, portStatus) -> {
            try {
                if (portStatus.get())
                    openPorts.add(port);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        return openPorts
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int openAndReturnUnusedPort() {
        int port = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            port = serverSocket.getLocalPort();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return port;
    }

    public String findAndReturnIPAddress() {
        return ipAddress;
    }

    private void init() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ipAddress, port), timeout);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            if (inputStream.available() > 0)
                objectInputStream = new ObjectInputStream(inputStream);
        } catch (Exception e) {
            objectInputStream = null;
        }
    }

    private Future<Boolean> pingPort(ExecutorService executorService, int port) {
        return executorService.submit(() -> {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ipAddress, port), timeout);
                socket.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    private String waitForResponseAndReturnResponseJson(String queryId) {
        String responseJson = "{}";
        try {
            OperatorResponseQuery operatorResponseQuery = (OperatorResponseQuery)objectInputStream.readObject();
            String responseQueryId = operatorResponseQuery.getQueryId();
            String response = operatorResponseQuery.getResponseJson();
            if (!response.equals("{}") && responseQueryId.equals(queryId))
                responseJson = response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseJson;
    }
}