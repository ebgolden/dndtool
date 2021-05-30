package services.dataoperatorservice.module;

import commonobjects.Campaign;
import commonobjects.DataOperator;
import commonobjects.GlobalNetworkOperator;
import commonobjects.Player;

public class GlobalNetworkOperatorModule extends DataOperatorModule {
    public GlobalNetworkOperatorModule(Campaign campaign, Player senderPlayer, Object api) {
        super(campaign, senderPlayer, api);
    }

    @Override
    protected void configure() {
        bind(DataOperator.class).to(GlobalNetworkOperator.class);
        super.configure();
    }
}