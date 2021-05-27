package services.partyservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.DataOperator;
import objects.DungeonMaster;
import objects.Party;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.partyservice.module.PartyModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MergePartiesTest {
    @Mock
    DataOperator<MergeParties> mockDataOperator;
    private MergeParties mergeParties;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new PartyModule());
        mergeParties = injector.getInstance(MergeParties.class);
    }

    @Test
    public void shouldReturnParty() {
        String responseJson = createMockResponseJson();
        MergePartiesResponse mergePartiesResponse = mockJsonResponseAndReturnMergePartiesResponse(responseJson);
        Assertions.assertNotNull(mergePartiesResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenEmptyJson() {
        String responseJson = "{}";
        MergePartiesResponse mergePartiesResponse = mockJsonResponseAndReturnMergePartiesResponse(responseJson);
        Assertions.assertNull(mergePartiesResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenNullJson() {
        MergePartiesResponse mergePartiesResponse = mockJsonResponseAndReturnMergePartiesResponse(null);
        Assertions.assertNull(mergePartiesResponse.getParty(), "Party not null.");
    }

    private String createMockResponseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String partyJson;
        try {
            partyJson = objectMapper.writeValueAsString(Party
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            partyJson = "{}";
        }
        return partyJson;
    }

    private MergePartiesResponse mockJsonResponseAndReturnMergePartiesResponse(String responseJson) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        MergePartiesRequest mergePartiesRequest = MergePartiesRequest
                .builder()
                .parties(new Party[] {})
                .dungeonMaster(DungeonMaster
                        .builder()
                        .build())
                .build();
        return mergeParties.getMergePartiesResponse(mergePartiesRequest);
    }
}