package services.characterservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import commonobjects.*;
import commonobjects.Character;
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
public class CreateCharacterTest {
    @Mock
    DataOperator mockDataOperator;
    private CreateCharacter createCharacter;

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
        Injector injector = Guice.createInjector(new CharacterModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        CreateCharacter.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        createCharacter = injector.getInstance(CreateCharacter.class);
    }

    @Test
    public void shouldReturnCharacterWhilePlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacter(playerId);
        CreateCharacterResponse createCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCharacterResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(createCharacterResponse.getCharacter(), "Character null.");
    }

    @Test
    public void shouldReturnCharacterWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacter(characterPlayerId);
        CreateCharacterResponse createCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCharacterResponse(dataOperatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertNotNull(createCharacterResponse.getCharacter(), "Character null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        CreateCharacterResponse createCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCharacterResponse(dataOperatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertNull(createCharacterResponse.getCharacter(), "Character not null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhenEmptyResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        CreateCharacterResponse createCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCharacterResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(createCharacterResponse.getCharacter(), "Character not null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        CreateCharacterResponse createCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCharacterResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(createCharacterResponse.getCharacter(), "Character not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithCharacter(String characterPlayerId) {
        String queryId = "123";
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson;
        try {
            characterJson = objectMapper.writeValueAsString(Character
                    .builder()
                    .playerId(characterPlayerId)
                    .build());
        } catch (JsonProcessingException e) {
            characterJson = "{}";
        }
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(characterJson)
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

    private CreateCharacterResponse mockJsonResponseAsPlayerOrDMAndReturnCreateCharacterResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
        Player player;
        if (isPlayer)
            player = Player
                    .builder()
                    .id(playerId)
                    .build();
        else player = DungeonMaster
                .builder()
                .id(playerId)
                .build();
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        CreateCharacterRequest createCharacterRequest = CreateCharacterRequest
                .builder()
                .character(Character
                        .builder()
                        .playerId(characterPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return createCharacter.getCreateCharacterResponse(createCharacterRequest);
    }
}