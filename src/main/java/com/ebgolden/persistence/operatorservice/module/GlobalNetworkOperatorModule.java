package persistence.operatorservice.module;

import com.amazonaws.regions.Regions;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import common.Campaign;
import common.Operator;
import common.GlobalNetworkOperator;
import common.Player;

public class GlobalNetworkOperatorModule extends OperatorModule {
    public GlobalNetworkOperatorModule(Campaign campaign, Player player, Object api) {
        super(campaign, player, api);
    }

    @Override
    protected void configure() {
        bind(Operator.class).to(GlobalNetworkOperator.class);
        super.configure();
    }

    @Provides
    @Named("region")
    public Regions provideReqion() {
        return Regions.US_EAST_2;
    }

    @Provides
    @Named("tableName")
    public String provideTableName() {
        return "campaigntraffic";
    }

    @Provides
    @Named("retries")
    public int provideRetries() {
        return 4;
    }

    @Override
    @Provides
    @Named("timeout")
    public int provideTimeout() {
        return 60000;
    }
}