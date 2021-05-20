package services.racedetailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.DataOperator;
import objects.Race;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.racedetailservice.module.RaceDetailModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetRaceDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private GetRaceDetails getRaceDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new RaceDetailModule());
        getRaceDetails = injector.getInstance(GetRaceDetails.class);
    }

    @Test
    public void shouldReturnRace() {
        String responseJson = createMockResponseJson();
        RaceDetailsResponse raceDetailsResponse = mockJsonResponseAndReturnRaceDetailsResponse(responseJson);
        Assertions.assertNotNull(raceDetailsResponse.getRace(), "Race null.");
    }

    @Test
    public void shouldReturnNoRaceWhenEmptyJson() {
        String responseJson = "{}";
        RaceDetailsResponse raceDetailsResponse = mockJsonResponseAndReturnRaceDetailsResponse(responseJson);
        Assertions.assertNull(raceDetailsResponse.getRace(), "Race not null.");
    }

    @Test
    public void shouldReturnNoRaceWhenNullJson() {
        RaceDetailsResponse raceDetailsResponse = mockJsonResponseAndReturnRaceDetailsResponse(null);
        Assertions.assertNull(raceDetailsResponse.getRace(), "Race not null.");
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

    private RaceDetailsResponse mockJsonResponseAndReturnRaceDetailsResponse(String responseJson) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        RaceDetailsRequest raceDetailsRequest = RaceDetailsRequest
                .builder()
                .race(Race
                        .builder()
                        .build())
                .build();
        return getRaceDetails.getRaceDetailsResponse(raceDetailsRequest);
    }
}