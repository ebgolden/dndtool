package services.dicedetailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.dicedetailservice.module.DiceDetailModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetDiceDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private GetDiceDetails getDiceDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new DiceDetailModule(GetDiceDetails.class));
        getDiceDetails = injector.getInstance(GetDiceDetails.class);
    }

    @Test
    public void shouldReturnThreeDiceWhilePlayer() {
        int dieCount = 3;
        String responseJson = createMockResponseJson(dieCount);
        DiceDetailsResponse diceDetailsResponse = mockJsonResponseAndReturnDiceDetailsResponse(responseJson, true);
        Assertions.assertEquals(dieCount, diceDetailsResponse.getDice().length, "Wrong amount of dice.");
    }

    @Test
    public void shouldReturnNoDiceWhileDungeonMaster() {
        int dieCount = 0;
        String responseJson = "{}";
        DiceDetailsResponse diceDetailsResponse = mockJsonResponseAndReturnDiceDetailsResponse(responseJson, false);
        Assertions.assertEquals(dieCount, diceDetailsResponse.getDice().length, "Dice not empty.");
    }

    @Test
    public void shouldReturnNoDice() {
        int dieCount = 0;
        String responseJson = createMockResponseJson(dieCount);
        DiceDetailsResponse diceDetailsResponse = mockJsonResponseAndReturnDiceDetailsResponse(responseJson, true);
        Assertions.assertEquals(dieCount, diceDetailsResponse.getDice().length, "Dice not empty.");
    }

    @Test
    public void shouldReturnNoDiceWhenEmptyJson() {
        int dieCount = 0;
        String responseJson = "{}";
        DiceDetailsResponse diceDetailsResponse = mockJsonResponseAndReturnDiceDetailsResponse(responseJson, true);
        Assertions.assertEquals(dieCount, diceDetailsResponse.getDice().length, "Dice not empty.");
    }

    @Test
    public void shouldReturnNoDiceWhenNullJson() {
        int dieCount = 0;
        DiceDetailsResponse diceDetailsResponse = mockJsonResponseAndReturnDiceDetailsResponse(null, true);
        Assertions.assertEquals(dieCount, diceDetailsResponse.getDice().length, "Dice not empty.");
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

    private DiceDetailsResponse mockJsonResponseAndReturnDiceDetailsResponse(String responseJson, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        Player player;
        if (isPlayer)
            player = Player
                    .builder()
                    .build();
        else player = DungeonMaster
                .builder()
                .build();
        DiceDetailsRequest diceDetailsRequest = DiceDetailsRequest
                .builder()
                .dice(new Die[] {})
                .player(player)
                .build();
        return getDiceDetails.getDiceDetailsResponse(diceDetailsRequest);
    }
}