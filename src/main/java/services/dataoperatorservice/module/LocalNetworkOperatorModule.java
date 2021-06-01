package services.dataoperatorservice.module;

import com.google.inject.Provides;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.DataOperator;
import commonobjects.LocalNetworkOperator;
import commonobjects.Player;
import java.net.ServerSocket;

public class LocalNetworkOperatorModule  extends DataOperatorModule {
    private ServerSocket serverSocket;

    public LocalNetworkOperatorModule(Campaign campaign, Player senderPlayer, Object api, ServerSocket serverSocket) {
        super(campaign, senderPlayer, api);
        this.serverSocket = serverSocket;
    }

    @Override
    protected void configure() {
        bind(DataOperator.class).to(LocalNetworkOperator.class);
        super.configure();
    }

    @Provides
    @Named("socket")
    public ServerSocket provideServerSocketClass() {
        return serverSocket;
    }
}