package services.dataoperatorservice.module;

import com.google.inject.Provides;
import com.google.inject.name.Named;
import commonobjects.*;
import java.util.HashMap;
import java.util.Map;

public class SingleDeviceOperatorModule extends DataOperatorModule {
    private final static Map<String, DataOperatorQuery> QUERY_MAP = new HashMap<>();

    public SingleDeviceOperatorModule(Player player, Object api) {
        super(player, api);
    }

    @Override
    protected void configure() {
        bind(DataOperator.class).to(SingleDeviceOperator.class);
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
    public Map<String, DataOperatorQuery> provideQueryMap() {
        return QUERY_MAP;
    }
}