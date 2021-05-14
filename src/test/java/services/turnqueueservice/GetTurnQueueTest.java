package services.turnqueueservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.CharacterObject;
import objects.DataOperator;
import objects.Encounter;
import objects.Party;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.turnqueueservice.module.TurnQueueModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTurnQueueTest {
    @Mock
    DataOperator mockDataOperator;
    private GetTurnQueue getTurnQueue;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new TurnQueueModule());
        getTurnQueue = injector.getInstance(GetTurnQueue.class);
    }

    @Test
    public void shouldReturnThreeCharactersWithCurrentTurnIndexTwo() {
        int characterCount = 3;
        int currentTurnIndex = 2;
        String responseJson = createMockResponseJson(characterCount, currentTurnIndex);
        TurnQueueResponse turnQueueResponse = mockJsonResponseAndReturnTurnQueueResponse(responseJson);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    @Test
    public void shouldReturnNoCharactersWithCurrentTurnIndexZero() {
        int characterCount = 0;
        int currentTurnIndex = 0;
        String responseJson = createMockResponseJson(characterCount, currentTurnIndex);
        TurnQueueResponse turnQueueResponse = mockJsonResponseAndReturnTurnQueueResponse(responseJson);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    @Test
    public void shouldReturnNoCharactersWithCurrentTurnIndexZeroWhenNoCharactersKey() {
        int characterCount = 0;
        int currentTurnIndex = 0;
        String responseJson = createMockResponseJsonNoCharactersKey(currentTurnIndex);
        TurnQueueResponse turnQueueResponse = mockJsonResponseAndReturnTurnQueueResponse(responseJson);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    @Test
    public void shouldReturnNoCharactersWithCurrentTurnIndexZeroWhenEmptyJson() {
        int characterCount = 0;
        int currentTurnIndex = 0;
        String responseJson = "{}";
        TurnQueueResponse turnQueueResponse = mockJsonResponseAndReturnTurnQueueResponse(responseJson);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    @Test
    public void shouldReturnNoCharactersWithCurrentTurnIndexZeroWhenNullJson() {
        int characterCount = 0;
        int currentTurnIndex = 0;
        TurnQueueResponse turnQueueResponse = mockJsonResponseAndReturnTurnQueueResponse(null);
        Assertions.assertTrue(((turnQueueResponse.getCharacters().length == characterCount) && (turnQueueResponse.getCurrentTurnIndex() == currentTurnIndex)), "Wrong amount of characters and/or wrong current turn index.");
    }

    private String createMockResponseJson(int characterCount, int currentTurnIndex) {
        StringBuilder responseJson = new StringBuilder("{\"characters\":[");
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson;
        try {
            characterJson = objectMapper.writeValueAsString(CharacterObject
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
        responseJson.append("],\"currentTurnIndex\":").append(currentTurnIndex).append("}");
        return responseJson.toString();
    }

    private String createMockResponseJsonNoCharactersKey(int currentTurnIndex) {
        return "{\"currentTurnIndex\":" + currentTurnIndex + "}";
    }

    private TurnQueueResponse mockJsonResponseAndReturnTurnQueueResponse(String responseJson) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
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