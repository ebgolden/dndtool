package services.actionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.*;
import objects.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.actionservice.module.ActionModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TakeActionTest {
    @Mock
    DataOperator mockDataOperator;
    private TakeAction takeAction;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ActionModule(TakeAction.class));
        takeAction = injector.getInstance(TakeAction.class);
    }

    @Test
    public void shouldReturnResultWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJson();
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(responseJson, playerId, playerId, true);
        Assertions.assertNotNull(takeActionResponse.getResult(), "Action null.");
    }

    @Test
    public void shouldReturnResultWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJson();
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertNotNull(takeActionResponse.getResult(), "Action null.");
    }

    @Test
    public void shouldReturnNoResultWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = "{}";
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertNull(takeActionResponse.getResult(), "Action not null.");
    }

    @Test
    public void shouldReturnNoResultWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(takeActionResponse.getResult(), "Result not null.");
    }

    @Test
    public void shouldReturnNoResultWhenNullJson() {
        String playerId = "1";
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(null, playerId, playerId, true);
        Assertions.assertNull(takeActionResponse.getResult(), "Result not null.");
    }

    private String createMockResponseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson;
        try {
            resultJson = objectMapper.writeValueAsString(Result
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            resultJson = "{}";
        }
        return resultJson;
    }

    private TakeActionResponse mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(String responseJson, String playerId, String characterPlayerId, boolean isPlayer) {
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
        TakeActionRequest takeActionRequest = TakeActionRequest
                .builder()
                .action(Action
                        .builder()
                        .build())
                .dice(new Die[] {})
                .character(Character
                        .builder()
                        .playerId(characterPlayerId)
                        .build())
                .player(player)
                .build();
        return takeAction.getTakeActionResponse(takeActionRequest);
    }
}