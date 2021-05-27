package services.raceservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import commonobjects.DataOperator;
import commonobjects.Race;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.raceservice.module.RaceModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedRaceTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedRace getUpdatedRace;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new RaceModule(GetUpdatedRace.class));
        getUpdatedRace = injector.getInstance(GetUpdatedRace.class);
    }

    @Test
    public void shouldReturnRace() {
        String responseJson = createMockResponseJson();
        UpdatedRaceResponse updatedRaceResponse = mockJsonResponseAndReturnUpdatedRaceResponse(responseJson);
        Assertions.assertNotNull(updatedRaceResponse.getRace(), "Race null.");
    }

    @Test
    public void shouldReturnNoRaceWhenEmptyJson() {
        String responseJson = "{}";
        UpdatedRaceResponse updatedRaceResponse = mockJsonResponseAndReturnUpdatedRaceResponse(responseJson);
        Assertions.assertNull(updatedRaceResponse.getRace(), "Race not null.");
    }

    @Test
    public void shouldReturnNoRaceWhenNullJson() {
        UpdatedRaceResponse updatedRaceResponse = mockJsonResponseAndReturnUpdatedRaceResponse(null);
        Assertions.assertNull(updatedRaceResponse.getRace(), "Race not null.");
    }

    private String createMockResponseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String raceJson;
        try {
            raceJson = objectMapper.writeValueAsString(Race
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            raceJson = "{}";
        }
        return raceJson;
    }

    private UpdatedRaceResponse mockJsonResponseAndReturnUpdatedRaceResponse(String responseJson) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        UpdatedRaceRequest updatedRaceRequest = UpdatedRaceRequest
                .builder()
                .race(Race
                        .builder()
                        .build())
                .build();
        return getUpdatedRace.getUpdatedRaceResponse(updatedRaceRequest);
    }
}