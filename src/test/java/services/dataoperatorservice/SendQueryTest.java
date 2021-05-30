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
public class SendQueryTest {
    private Campaign campaign;
    private Player senderPlayer;
    private Object api;
    @Mock
    private DataOperator mockDataOperator;
    private SendQuery sendQuery;

    @BeforeEach
    public void setup() {
        campaign = Campaign
                .builder()
                .id("1")
                .build();
        senderPlayer = Player
                .builder()
                .id("1")
                .build();
        api = JoinParty.class;
        Injector injector = Guice.createInjector(Modules.override(new GlobalNetworkOperatorModule(campaign,
                        senderPlayer,
                api))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        sendQuery = injector.getInstance(SendQuery.class);
    }

    @Test
    public void shouldReturnSuccessfulMessage() {
        DataOperatorResponseQuery dataOperatorResponseQuery = DataOperatorResponseQuery
                .builder()
                .responseJson("{}")
                .build();
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
        QueryType queryType = QueryType.PUSH;
        String requestJson = "{\"party\":{},\"character\":{}}";
        QueryRequest queryRequest = QueryRequest
                .builder()
                .campaign(campaign)
                .senderPlayer(senderPlayer)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        Assertions.assertNotNull(queryResponse.getResponseJson(), "Response json null.");
    }
}
