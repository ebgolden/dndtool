package services.characterservice;

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
import services.characterservice.module.CharacterModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateNonPlayableCharacterTest {
    @Mock
    DataOperator mockDataOperator;
    private CreateNonPlayableCharacter createNonPlayableCharacter;

    @BeforeEach
    public void setup() {
        Campaign campaign = Campaign
                .builder()
                .id("1")
                .build();
        Player senderPlayer = Player
                .builder()
                .id("1")
                .build();
        Injector injector = Guice.createInjector(new CharacterModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        senderPlayer,
                        CreateNonPlayableCharacter.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        createNonPlayableCharacter = injector.getInstance(CreateNonPlayableCharacter.class);
    }

    @Test
    public void shouldReturnNonPlayableCharacter() {
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacter(dungeonMasterId);
        CreateNonPlayableCharacterResponse createNonPlayableCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateNonPlayableCharacterResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(createNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter null.");
    }

    @Test
    public void shouldReturnEmptyNonPlayableCharacterWhileDifferentDM() {
        String dungeonMasterId = "2";
        String nonPlayableCharacterDungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        CreateNonPlayableCharacterResponse createNonPlayableCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateNonPlayableCharacterResponse(dataOperatorResponseQuery, dungeonMasterId, nonPlayableCharacterDungeonMasterId);
        Assertions.assertNull(createNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter not null.");
    }

    @Test
    public void shouldReturnEmptyNonPlayableCharacterWhenEmptyResponse() {
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        CreateNonPlayableCharacterResponse createNonPlayableCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateNonPlayableCharacterResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(createNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter not null.");
    }

    @Test
    public void shouldReturnEmptyNonPlayableCharacterWhenNullResponse() {
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        CreateNonPlayableCharacterResponse createNonPlayableCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateNonPlayableCharacterResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(createNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithCharacter(String nonPlayableCharacterDungeonMasterId) {
        String queryId = "123";
        ObjectMapper objectMapper = new ObjectMapper();
        String nonPlayableCharacterJson;
        try {
            nonPlayableCharacterJson = objectMapper.writeValueAsString(NonPlayableCharacter
                    .builder()
                    .playerId(nonPlayableCharacterDungeonMasterId)
                    .build());
        } catch (JsonProcessingException e) {
            nonPlayableCharacterJson = "{}";
        }
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(nonPlayableCharacterJson)
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

    private CreateNonPlayableCharacterResponse mockJsonResponseAsPlayerOrDMAndReturnCreateNonPlayableCharacterResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String dungeonMasterId, String nonPlayableCharacterDungeonMasterId) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
        DungeonMaster dungeonMaster = DungeonMaster
                .builder()
                .id(dungeonMasterId)
                .build();
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        CreateNonPlayableCharacterRequest createNonPlayableCharacterRequest = CreateNonPlayableCharacterRequest
                .builder()
                .nonPlayableCharacter(NonPlayableCharacter
                        .builder()
                        .playerId(nonPlayableCharacterDungeonMasterId)
                        .build())
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
        return createNonPlayableCharacter.getCreateNonPlayableCharacterResponse(createNonPlayableCharacterRequest);
    }
}