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
public class GetActionsTest {
    @Mock
    DataOperator mockDataOperator;
    private GetActions getActions;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ActionModule());
        getActions = injector.getInstance(GetActions.class);
    }

    @Test
    public void shouldReturnThreeActions() {
        int actionCount = 3;
        String responseJson = createMockResponseJson(actionCount);
        ActionsResponse actionsResponse = mockJsonResponseAndReturnActionsResponse(responseJson);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Wrong amount of actions.");
    }

    @Test
    public void shouldReturnNoActions() {
        int actionCount = 0;
        String responseJson = createMockResponseJson(actionCount);
        ActionsResponse actionsResponse = mockJsonResponseAndReturnActionsResponse(responseJson);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Wrong amount of actions.");
    }

    @Test
    public void shouldReturnNoActionsWhenEmptyJson() {
        int actionCount = 0;
        String responseJson = "{}";
        ActionsResponse actionsResponse = mockJsonResponseAndReturnActionsResponse(responseJson);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Wrong amount of actions.");
    }

    @Test
    public void shouldReturnNoActionsWhenNullJson() {
        int actionCount = 0;
        ActionsResponse actionsResponse = mockJsonResponseAndReturnActionsResponse(null);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Wrong amount of actions.");
    }

    private String createMockResponseJson(int actionCount) {
        StringBuilder responseJson = new StringBuilder("{\"actions\":[");
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson;
        try {
            actionJson = objectMapper.writeValueAsString(Action
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            actionJson = "{}";
        }
        for (int currentActionIndex = 0; currentActionIndex < actionCount; ++currentActionIndex) {
            responseJson.append(actionJson);
            if (currentActionIndex < (actionCount - 1))
                responseJson.append(",");
        }
        responseJson.append("]}");
        return responseJson.toString();
    }

    private ActionsResponse mockJsonResponseAndReturnActionsResponse(String responseJson) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        ActionsRequest actionsRequest = ActionsRequest
                .builder()
                .character(Character
                        .builder()
                        .build())
                .build();
        return getActions.getActionsResponse(actionsRequest);
    }
}