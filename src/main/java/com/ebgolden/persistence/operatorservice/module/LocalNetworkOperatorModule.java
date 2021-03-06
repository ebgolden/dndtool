package com.ebgolden.persistence.operatorservice.module;

import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.ebgolden.common.Operator;
import com.ebgolden.common.LocalNetworkOperator;
import com.ebgolden.common.Player;

public class LocalNetworkOperatorModule extends OperatorModule {
    private final int PORT;

    public LocalNetworkOperatorModule(Player player, Object api) {
        super(player, api);
        PORT = 0;
    }

    public LocalNetworkOperatorModule(Player player, Object api, int port) {
        super(player, api);
        PORT = port;
    }

    @Override
    protected void configure() {
        bind(Operator.class).to(LocalNetworkOperator.class);
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
        return "127.0.0.1";
    }

    @Override
    @Provides
    @Named("timeout")
    public int provideTimeout() {
        return 5;
    }

    @Provides
    @Named("port")
    public int providePort() {
        return PORT;
    }
}