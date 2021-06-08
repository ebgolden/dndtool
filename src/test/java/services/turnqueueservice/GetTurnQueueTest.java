package services.turnqueueservice;

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
import services.turnqueueservice.module.TurnQueueModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTurnQueueTest {
    @Mock
    DataOperator mockDataOperator;
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
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        getTurnQueue = injector.getInstance(GetTurnQueue.class);
    }

    @Test
    public void shouldReturnThreeCharactersWithCurrentTurnIndexTwo() {
        int characterCount = 3;
        int currentTurnIndex = 2;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithTurnQueue(characterCount, currentTurnIndex);
        TurnQueueResponse turnQueueResponse = mockResponseAndReturnTurnQueueResponse(dataOperatorResponseQuery);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    @Test
    public void shouldReturnNoCharactersWithCurrentTurnIndexZero() {
        int characterCount = 0;
        int currentTurnIndex = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithTurnQueue(characterCount, currentTurnIndex);
        TurnQueueResponse turnQueueResponse = mockResponseAndReturnTurnQueueResponse(dataOperatorResponseQuery);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    @Test
    public void shouldReturnNoCharactersWithCurrentTurnIndexZeroWhenNoCharactersKey() {
        int characterCount = 0;
        int currentTurnIndex = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithTurnQueueNoCharactersKey(currentTurnIndex);
        TurnQueueResponse turnQueueResponse = mockResponseAndReturnTurnQueueResponse(dataOperatorResponseQuery);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    @Test
    public void shouldReturnNoCharactersWithCurrentTurnIndexZeroWhenEmptyResponse() {
        int characterCount = 0;
        int currentTurnIndex = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        TurnQueueResponse turnQueueResponse = mockResponseAndReturnTurnQueueResponse(dataOperatorResponseQuery);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    @Test
    public void shouldReturnNoCharactersWithCurrentTurnIndexZeroWhenNullResponse() {
        int characterCount = 0;
        int currentTurnIndex = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        TurnQueueResponse turnQueueResponse = mockResponseAndReturnTurnQueueResponse(dataOperatorResponseQuery);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    private DataOperatorResponseQuery createMockResponseWithTurnQueue(int characterCount, int currentTurnIndex) {
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
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson.toString())
                .build();
    }

    private DataOperatorResponseQuery createMockResponseWithTurnQueueNoCharactersKey(int currentTurnIndex) {
        String queryId = "123";
        String responseJson = "{\"currentTurnIndex\":" + currentTurnIndex + "}";
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
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

    private TurnQueueResponse mockResponseAndReturnTurnQueueResponse(DataOperatorResponseQuery dataOperatorResponseQuery) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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