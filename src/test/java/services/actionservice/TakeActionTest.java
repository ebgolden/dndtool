package services.actionservice;

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
        }, new ActionModule());
        takeAction = injector.getInstance(TakeAction.class);
    }

    @Test
    public void shouldReturnResult() {
        String responseJson = createMockResponseJson();
        TakeActionResponse takeActionResponse = mockJsonResponseAndReturnTakeActionResponse(responseJson);
        Assertions.assertNotEquals(null, takeActionResponse.getResult(), "Wrong amount of results.");
    }

    @Test
    public void shouldReturnNoResultWhenEmptyJson() {
        String responseJson = "{}";
        TakeActionResponse takeActionResponse = mockJsonResponseAndReturnTakeActionResponse(responseJson);
        Assertions.assertNull(takeActionResponse.getResult(), "Wrong amount of results.");
    }

    @Test
    public void shouldReturnNoResultWhenNullJson() {
        TakeActionResponse takeActionResponse = mockJsonResponseAndReturnTakeActionResponse(null);
        Assertions.assertNull(takeActionResponse.getResult(), "Wrong amount of results.");
    }

    private String createMockResponseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson;
        try {
            resultJson = objectMapper.writeValueAsString(Result
                    .builder()
                    .id("0")
                    .build());
        } catch (JsonProcessingException e) {
            resultJson = "{}";
        }
        return resultJson;
    }

    private TakeActionResponse mockJsonResponseAndReturnTakeActionResponse(String responseJson) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        TakeActionRequest takeActionRequest = TakeActionRequest
                .builder()
                .action(Action
                        .builder()
                        .build())
                .diceRolls(new int[] {})
                .build();
        return takeAction.getTakeActionResponse(takeActionRequest);
    }
}
