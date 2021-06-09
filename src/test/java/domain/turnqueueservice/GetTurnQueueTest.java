package domain.turnqueueservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import common.*;
import common.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.turnqueueservice.module.TurnQueueModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTurnQueueTest {
    @Mock
    Operator mockOperator;
    private GetTurnQueue getTurnQueue;

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
        Injector injector = Guice.createInjector(new TurnQueueModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        GetTurnQueue.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        getTurnQueue = injector.getInstance(GetTurnQueue.class);
    }

    @Test
    public void shouldReturnThreeCharactersWithCurrentTurnIndexTwo() {
        int characterCount = 3;
        int currentTurnIndex = 2;
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithTurnQueue(characterCount, currentTurnIndex);
        TurnQueueResponse turnQueueResponse = mockResponseAndReturnTurnQueueResponse(operatorResponseQuery);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    @Test
    public void shouldReturnNoCharactersWithCurrentTurnIndexZero() {
        int characterCount = 0;
        int currentTurnIndex = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithTurnQueue(characterCount, currentTurnIndex);
        TurnQueueResponse turnQueueResponse = mockResponseAndReturnTurnQueueResponse(operatorResponseQuery);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    @Test
    public void shouldReturnNoCharactersWithCurrentTurnIndexZeroWhenNoCharactersKey() {
        int characterCount = 0;
        int currentTurnIndex = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithTurnQueueNoCharactersKey(currentTurnIndex);
        TurnQueueResponse turnQueueResponse = mockResponseAndReturnTurnQueueResponse(operatorResponseQuery);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    @Test
    public void shouldReturnNoCharactersWithCurrentTurnIndexZeroWhenEmptyResponse() {
        int characterCount = 0;
        int currentTurnIndex = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        TurnQueueResponse turnQueueResponse = mockResponseAndReturnTurnQueueResponse(operatorResponseQuery);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    @Test
    public void shouldReturnNoCharactersWithCurrentTurnIndexZeroWhenNullResponse() {
        int characterCount = 0;
        int currentTurnIndex = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        TurnQueueResponse turnQueueResponse = mockResponseAndReturnTurnQueueResponse(operatorResponseQuery);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    private OperatorResponseQuery createMockResponseWithTurnQueue(int characterCount, int currentTurnIndex) {
        String queryId = "123";
        StringBuilder responseJson = new StringBuilder("{\"characters\":[");
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson;
        try {
            characterJson = objectMapper.writeValueAsString(Character
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            characterJson = "{}";
        }
        for (int currentCharacterIndex = 0; currentCharacterIndex < characterCount; ++currentCharacterIndex) {
            responseJson.append(characterJson);
            if (currentCharacterIndex < (characterCount - 1))
                responseJson.append(",");
        }
        responseJson
                .append("],\"currentTurnIndex\":")
                .append(currentTurnIndex)
                .append("}");
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson.toString())
                .build();
    }

    private OperatorResponseQuery createMockResponseWithTurnQueueNoCharactersKey(int currentTurnIndex) {
        String queryId = "123";
        String responseJson = "{\"currentTurnIndex\":" + currentTurnIndex + "}";
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    private OperatorResponseQuery createMockResponse(String responseJson) {
        String queryId = "123";
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    private TurnQueueResponse mockResponseAndReturnTurnQueueResponse(OperatorResponseQuery operatorResponseQuery) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
        TurnQueueRequest turnQueueRequest = TurnQueueRequest
                .builder()
                .party(Party
                        .builder()
                        .currentEncounter(Encounter
                                .builder()
                                .build())
                        .build())
                .build();
        return getTurnQueue.getTurnQueueResponse(turnQueueRequest);
    }
}