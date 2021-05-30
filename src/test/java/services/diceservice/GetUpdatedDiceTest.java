package services.diceservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import commonobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import services.diceservice.module.DiceModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedDiceTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedDice getUpdatedDice;

    @BeforeEach
    public void setup() {
        Campaign campaign = Campaign
                .builder()
                .id("1")
                .build();
        Player senderPlayer = Player
                .builder()
                .id("1")
                .build();
        Injector injector = Guice.createInjector(new DiceModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        senderPlayer,
                        GetUpdatedDice.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        getUpdatedDice = injector.getInstance(GetUpdatedDice.class);
    }

    @Test
    public void shouldReturnThreeDiceWhilePlayer() {
        int dieCount = 3;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithDice(dieCount);
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(dataOperatorResponseQuery, true);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Wrong amount of dice.");
    }

    @Test
    public void shouldReturnNoDiceWhileDungeonMaster() {
        int dieCount = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(dataOperatorResponseQuery, false);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Dice not empty.");
    }

    @Test
    public void shouldReturnNoDice() {
        int dieCount = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithDice(dieCount);
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(dataOperatorResponseQuery, true);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Dice not empty.");
    }

    @Test
    public void shouldReturnNoDiceWhenEmptyResponse() {
        int dieCount = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(dataOperatorResponseQuery, true);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Dice not empty.");
    }

    @Test
    public void shouldReturnNoDiceWhenNullResponse() {
        int dieCount = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(dataOperatorResponseQuery, true);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Dice not empty.");
    }

    private DataOperatorResponseQuery createMockResponseWithDice(int dieCount) {
        String queryId = "123";
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
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson.toString())
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

    private UpdatedDiceResponse mockJsonResponseAndReturnUpdatedDiceResponse(DataOperatorResponseQuery dataOperatorResponseQuery, boolean isPlayer) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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