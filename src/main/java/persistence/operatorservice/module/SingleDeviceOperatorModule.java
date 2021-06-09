package persistence.operatorservice.module;

import com.google.inject.Provides;
import com.google.inject.name.Named;
import common.*;
import java.util.HashMap;
import java.util.Map;

public class SingleDeviceOperatorModule extends OperatorModule {
    private final static Map<String, OperatorQuery> QUERY_MAP = new HashMap<>();

    public SingleDeviceOperatorModule(Player player, Object api) {
        super(player, api);
    }

    @Override
    protected void configure() {
        bind(Operator.class).to(SingleDeviceOperator.class);
        super.configure();
    }

    @Override
    @Provides
    @Named("timeout")
    public int provideTimeout() {
        return 0;
    }

    @Provides
    @Named("queryMap")
    public Map<String, OperatorQuery> provideQueryMap() {
        return QUERY_MAP;
    }
}