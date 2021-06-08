package services.raceservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import services.raceservice.module.RaceModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedRaceTest {
    @Mock
    DataOperator mockDataOperator;
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
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        getUpdatedRace = injector.getInstance(GetUpdatedRace.class);
    }

    @Test
    public void shouldReturnRace() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithRace();
        UpdatedRaceResponse updatedRaceResponse = mockResponseAndReturnUpdatedRaceResponse(dataOperatorResponseQuery);
        Assertions.assertNotNull(updatedRaceResponse.getRace(), "Race null.");
    }

    @Test
    public void shouldReturnNoRaceWhenEmptyResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        UpdatedRaceResponse updatedRaceResponse = mockResponseAndReturnUpdatedRaceResponse(dataOperatorResponseQuery);
        Assertions.assertNull(updatedRaceResponse.getRace(), "Race not null.");
    }

    @Test
    public void shouldReturnNoRaceWhenNullResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        UpdatedRaceResponse updatedRaceResponse = mockResponseAndReturnUpdatedRaceResponse(dataOperatorResponseQuery);
        Assertions.assertNull(updatedRaceResponse.getRace(), "Race not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithRace() {
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
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(raceJson)
                .build();
    }

    private DataOperatorResponseQuery createMockResponse(String responseJson) {
        String queryId = "123";
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    private UpdatedRaceResponse mockResponseAndReturnUpdatedRaceResponse(DataOperatorResponseQuery dataOperatorResponseQuery) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
        UpdatedRaceRequest updatedRaceRequest = UpdatedRaceRequest
                .builder()
                .race(Race
                        .builder()
                        .build())
                .build();
        return getUpdatedRace.getUpdatedRaceResponse(updatedRaceRequest);
    }
}