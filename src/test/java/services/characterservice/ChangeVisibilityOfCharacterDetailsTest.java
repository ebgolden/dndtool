package services.characterservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import commonobjects.Character;
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
public class ChangeVisibilityOfCharacterDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeVisibilityOfCharacterDetails changeVisibilityOfCharacterDetails;

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
                        ChangeVisibilityOfCharacterDetails.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        changeVisibilityOfCharacterDetails = injector.getInstance(ChangeVisibilityOfCharacterDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(playerId);
        ChangeVisibilityOfCharacterDetailsResponse changeVisibilityOfUpdatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfCharacterDetailsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(changeVisibilityOfUpdatedCharacterResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId);
        ChangeVisibilityOfCharacterDetailsResponse changeVisibilityOfUpdatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfCharacterDetailsResponse(dataOperatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertNotNull(changeVisibilityOfUpdatedCharacterResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfCharacterDetailsResponse changeVisibilityOfUpdatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfCharacterDetailsResponse(dataOperatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedCharacterResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfCharacterDetailsResponse changeVisibilityOfUpdatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfCharacterDetailsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedCharacterResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        ChangeVisibilityOfCharacterDetailsResponse changeVisibilityOfUpdatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfCharacterDetailsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedCharacterResponse.getVisibilityMap(), "Visibility not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithCharacterWithVisibilityOfId(String characterPlayerId) {
        String queryId = "123";
        StringBuilder responseJson = new StringBuilder("{\"character\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson;
        String visibilityJson;
        String characterId = "1";
        try {
            characterJson = objectMapper.writeValueAsString(Character
                    .builder()
                    .id(characterId)
                    .playerId(characterPlayerId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(Visibility.PLAYER);
        } catch (JsonProcessingException e) {
            characterJson = "{}";
            visibilityJson = "{}";
        }
        responseJson
                .append(characterJson)
                .append(",\"visibility\":{\"id\":")
                .append(visibilityJson)
                .append("}}");
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson.toString())
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

    private ChangeVisibilityOfCharacterDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfCharacterDetailsResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer) {
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
        ChangeVisibilityOfCharacterDetailsRequest changeVisibilityOfUpdatedCharacterRequest = ChangeVisibilityOfCharacterDetailsRequest
                .builder()
                .character(Character
                        .builder()
                        .playerId(characterPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return changeVisibilityOfCharacterDetails.getChangeVisibilityOfCharacterDetailsResponse(changeVisibilityOfUpdatedCharacterRequest);
    }
}