package persistence.operatorservice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import common.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import domain.partyservice.JoinParty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SendRequestQueryTest {
    private Campaign campaign;
    private Player player;
    private Object api;
    private QueryType queryType;
    @Mock
    private Operator mockOperator;
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
                        bind(Operator.class).toInstance(mockOperator);
                    }
                }));
        sendRequestQuery = injector.getInstance(SendRequestQuery.class);
    }

    @Test
    public void shouldReturnResponse() {
        OperatorResponseQuery operatorResponseQuery = OperatorResponseQuery
                .builder()
                .responseJson("{}")
                .build();
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
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
