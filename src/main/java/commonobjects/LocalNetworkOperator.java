package commonobjects;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.net.ServerSocket;

public class LocalNetworkOperator  implements DataOperator {
    @Inject
    @Named("socket")
    private ServerSocket serverSocket;

    public LocalNetworkOperator() {
        //
    }


    public DataOperatorResponseQuery getResponseJson(DataOperatorRequestQuery dataOperatorRequestQuery) {
        //
    }

    public DataOperatorResponseQuery getResponseJson(DataOperatorResponseQuery dataOperatorResponseQuery) {
        //
    }
}