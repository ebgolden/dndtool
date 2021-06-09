package domain.raceservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import domain.raceservice.module.RaceModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedRaceTest {
    @Mock
    Operator mockOperator;
    private GetUpdatedRace getUpdatedRace;

    @BeforeEach
    public void setup() {
        Campaign campaign = Campaign
                .builder()
                .id("1")
                .build();
        Player player = Player
                .builder()
                .id("1")
                .build();
        Injector injector = Guice.createInjector(new RaceModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        GetUpdatedRace.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        getUpdatedRace = injector.getInstance(GetUpdatedRace.class);
    }

    @Test
    public void shouldReturnRace() {
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithRace();
        UpdatedRaceResponse updatedRaceResponse = mockResponseAndReturnUpdatedRaceResponse(operatorResponseQuery);
        Assertions.assertNotNull(updatedRaceResponse.getRace(), "Race null.");
    }

    @Test
    public void shouldReturnNoRaceWhenEmptyResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        UpdatedRaceResponse updatedRaceResponse = mockResponseAndReturnUpdatedRaceResponse(operatorResponseQuery);
        Assertions.assertNull(updatedRaceResponse.getRace(), "Race not null.");
    }

    @Test
    public void shouldReturnNoRaceWhenNullResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        UpdatedRaceResponse updatedRaceResponse = mockResponseAndReturnUpdatedRaceResponse(operatorResponseQuery);
        Assertions.assertNull(updatedRaceResponse.getRace(), "Race not null.");
    }

    private OperatorResponseQuery createMockResponseWithRace() {
        String queryId = "123";
        ObjectMapper objectMapper = new ObjectMapper();
        String raceJson;
        try {
            raceJson = objectMapper.writeValueAsString(Race
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            raceJson = "{}";
        }
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(raceJson)
                .build();
    }

    private OperatorResponseQuery createMockResponse(String responseJson) {
        String queryId = "123";
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    private UpdatedRaceResponse mockResponseAndReturnUpdatedRaceResponse(OperatorResponseQuery operatorResponseQuery) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
        UpdatedRaceRequest updatedRaceRequest = UpdatedRaceRequest
                .builder()
                .race(Race
                        .builder()
                        .build())
                .build();
        return getUpdatedRace.getUpdatedRaceResponse(updatedRaceRequest);
    }
}