package services.actiondetailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.actiondetailservice.module.ActionDetailModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateActionDetailsVisibilityTest {
    @Mock
    DataOperator<UpdateActionDetailsVisibility> mockDataOperator;
    private UpdateActionDetailsVisibility updateActionDetailsVisibility;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ActionDetailModule());
        updateActionDetailsVisibility = injector.getInstance(UpdateActionDetailsVisibility.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId);
        ActionDetailsVisibilityResponse actionDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsVisibilityResponse(responseJson, playerId, playerId, true);
        Assertions.assertNotNull(actionDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId);
        ActionDetailsVisibilityResponse actionDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsVisibilityResponse(responseJson, playerId, actionPlayerId, false);
        Assertions.assertNotNull(actionDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = "{}";
        ActionDetailsVisibilityResponse actionDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsVisibilityResponse(responseJson, playerId, actionPlayerId, true);
        Assertions.assertNull(actionDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        ActionDetailsVisibilityResponse actionDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsVisibilityResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(actionDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullJson() {
        String playerId = "1";
        ActionDetailsVisibilityResponse actionDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsVisibilityResponse(null, playerId, playerId, true);
        Assertions.assertNull(actionDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String actionPlayerId) {
        StringBuilder responseJson = new StringBuilder("{\"actionDetails\":");
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

    private ActionDetailsVisibilityResponse mockJsonResponseAsPlayerOrDMAndReturnActionDetailsVisibilityResponse(String responseJson, String playerId, String actionPlayerId, boolean isPlayer) {
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
        ActionDetailsVisibilityRequest actionDetailsVisibilityRequest = ActionDetailsVisibilityRequest
                .builder()
                .action(Action
                        .builder()
                        .playerId(actionPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return updateActionDetailsVisibility.getActionDetailsVisibilityResponse(actionDetailsVisibilityRequest);
    }
}