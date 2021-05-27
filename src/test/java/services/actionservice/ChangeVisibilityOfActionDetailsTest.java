package services.actionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import commonobjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.actionservice.module.ActionModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfActionDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeVisibilityOfActionDetails changeVisibilityOfActionDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ActionModule(ChangeVisibilityOfActionDetails.class));
        changeVisibilityOfActionDetails = injector.getInstance(ChangeVisibilityOfActionDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId);
        ChangeVisibilityOfActionDetailsResponse changeVisibilityOfActionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfActionDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertNotNull(changeVisibilityOfActionDetailsResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId);
        ChangeVisibilityOfActionDetailsResponse changeVisibilityOfActionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfActionDetailsResponse(responseJson, playerId, actionPlayerId, false);
        Assertions.assertNotNull(changeVisibilityOfActionDetailsResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = "{}";
        ChangeVisibilityOfActionDetailsResponse changeVisibilityOfActionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfActionDetailsResponse(responseJson, playerId, actionPlayerId, true);
        Assertions.assertNull(changeVisibilityOfActionDetailsResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        ChangeVisibilityOfActionDetailsResponse changeVisibilityOfActionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfActionDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfActionDetailsResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullJson() {
        String playerId = "1";
        ChangeVisibilityOfActionDetailsResponse changeVisibilityOfActionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfActionDetailsResponse(null, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfActionDetailsResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String actionPlayerId) {
        StringBuilder responseJson = new StringBuilder("{\"action\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson;
        String visibilityJson;
        String actionId = "1";
        try {
            actionJson = objectMapper.writeValueAsString(Action
                    .builder()
                    .id(actionId)
                    .playerId(actionPlayerId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(Visibility.PLAYER);
        } catch (JsonProcessingException e) {
            actionJson = "{}";
            visibilityJson = "{}";
        }
        responseJson
                .append(actionJson)
                .append(",\"visibility\":{\"id\":")
                .append(visibilityJson)
                .append("}}");
        return responseJson.toString();
    }

    private ChangeVisibilityOfActionDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfActionDetailsResponse(String responseJson, String playerId, String actionPlayerId, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
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
        ChangeVisibilityOfActionDetailsRequest changeVisibilityOfActionDetailsRequest = ChangeVisibilityOfActionDetailsRequest
                .builder()
                .action(Action
                        .builder()
                        .playerId(actionPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return changeVisibilityOfActionDetails.getChangeVisibilityOfActionDetailsResponse(changeVisibilityOfActionDetailsRequest);
    }
}