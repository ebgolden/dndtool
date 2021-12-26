package com.ebgolden.persistence.operatorservice.module;

import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.ebgolden.common.Operator;
import com.ebgolden.common.Player;
import com.ebgolden.common.WideNetworkOperator;

public class WideNetworkOperatorModule extends OperatorModule {
    private final String IP_ADDRESS;
    private final int PORT;

    public WideNetworkOperatorModule(Player player, Object api) {
        super(player, api);
        IP_ADDRESS = "127.0.0.1";
        PORT = 0;
    }

    public WideNetworkOperatorModule(Player player, Object api, String ipAddress) {
        super(player, api);
        IP_ADDRESS = ipAddress;
        PORT = 0;
    }

    public WideNetworkOperatorModule(Player player, Object api, String ipAddress, int port) {
        super(player, api);
        IP_ADDRESS = ipAddress;
        PORT = port;
    }

    @Override
    protected void configure() {
        bind(Operator.class).to(WideNetworkOperator.class);
        super.configure();
    }

    @Provides
    @Named("threadCount")
    public int provideThreadCount() {
        return 100;
    }

    @Provides
    @Named("minimumPort")
    public int provideMinimumPort() {
        return 1;
    }

    @Provides
    @Named("maximumPort")
    public int provideMaximumPort() {
        return 65535;
    }

    @Provides
    @Named("ipAddress")
    public String provideIPAddress() {
        return IP_ADDRESS;
    }

    @Override
    @Provides
    @Named("timeout")
    public int provideTimeout() {
        return 15;
    }

    @Provides
    @Named("port")
    public int providePort() {
        return PORT;
    }

    @Provides
    @Named("ipCheckerURL")
    public String provideIPCheckerURL() {
        return "https://checkip.amazonaws.com";
    }
}