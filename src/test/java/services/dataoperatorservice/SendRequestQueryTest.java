package services.dataoperatorservice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import commonobjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import services.partyservice.JoinParty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SendRequestQueryTest {
    private Campaign campaign;
    private Player player;
    private Object api;
    private QueryType queryType;
    @Mock
    private DataOperator mockDataOperator;
    private SendRequestQuery sendRequestQuery;

    @BeforeEach
    public void setup() {
        campaign = Campaign
                .builder()
                .id("1")
                .build();
        player = Player
                .builder()
                .id("1")
                .build();
        api = JoinParty.class;
        queryType = QueryType.PUSH;
        Injector injector = Guice.createInjector(Modules.override(new GlobalNetworkOperatorModule(campaign,
                player,
                api))
                .with(new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(DataOperator.class).toInstance(mockDataOperator);
                    }
                }));
        sendRequestQuery = injector.getInstance(SendRequestQuery.class);
    }

    @Test
    public void shouldReturnResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = DataOperatorResponseQuery
                .builder()
                .responseJson("{}")
                .build();
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
        String requestJson = "{\"party\":{},\"character\":{}}";
        RequestQueryRequest requestQueryRequest = RequestQueryRequest
                .builder()
                .campaign(campaign)
                .player(player)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        Assertions.assertNotNull(requestQueryResponse.getResponseJson(), "Response json null.");
    }
}
