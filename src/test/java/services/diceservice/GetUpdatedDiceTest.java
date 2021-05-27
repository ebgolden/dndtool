package services.diceservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import commonobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.diceservice.module.DiceModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedDiceTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedDice getUpdatedDice;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new DiceModule(GetUpdatedDice.class));
        getUpdatedDice = injector.getInstance(GetUpdatedDice.class);
    }

    @Test
    public void shouldReturnThreeDiceWhilePlayer() {
        int dieCount = 3;
        String responseJson = createMockResponseJson(dieCount);
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(responseJson, true);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Wrong amount of dice.");
    }

    @Test
    public void shouldReturnNoDiceWhileDungeonMaster() {
        int dieCount = 0;
        String responseJson = "{}";
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(responseJson, false);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Dice not empty.");
    }

    @Test
    public void shouldReturnNoDice() {
        int dieCount = 0;
        String responseJson = createMockResponseJson(dieCount);
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(responseJson, true);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Dice not empty.");
    }

    @Test
    public void shouldReturnNoDiceWhenEmptyJson() {
        int dieCount = 0;
        String responseJson = "{}";
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(responseJson, true);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Dice not empty.");
    }

    @Test
    public void shouldReturnNoDiceWhenNullJson() {
        int dieCount = 0;
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(null, true);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Dice not empty.");
    }

    private String createMockResponseJson(int dieCount) {
        StringBuilder responseJson = new StringBuilder("[");
        ObjectMapper objectMapper = new ObjectMapper();
        String dieJson;
        try {
            dieJson = objectMapper.writeValueAsString(Die
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            dieJson = "{}";
        }
        for (int currentDieIndex = 0; currentDieIndex < dieCount; ++currentDieIndex) {
            responseJson.append(dieJson);
            if (currentDieIndex < (dieCount - 1))
                responseJson.append(",");
        }
        responseJson.append("]");
        return responseJson.toString();
    }

    private UpdatedDiceResponse mockJsonResponseAndReturnUpdatedDiceResponse(String responseJson, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        Player player;
        if (isPlayer)
            player = Player
                    .builder()
                    .build();
        else player = DungeonMaster
                .builder()
                .build();
        UpdatedDiceRequest updatedDiceRequest = UpdatedDiceRequest
                .builder()
                .dice(new Die[] {})
                .player(player)
                .build();
        return getUpdatedDice.getUpdatedDiceResponse(updatedDiceRequest);
    }
}