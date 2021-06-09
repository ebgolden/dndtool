package domain.diceservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import common.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import domain.diceservice.module.DiceModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedDiceTest {
    @Mock
    Operator mockOperator;
    private GetUpdatedDice getUpdatedDice;

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
        Injector injector = Guice.createInjector(new DiceModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        GetUpdatedDice.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        getUpdatedDice = injector.getInstance(GetUpdatedDice.class);
    }

    @Test
    public void shouldReturnThreeDiceWhilePlayer() {
        int dieCount = 3;
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithDice(dieCount);
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(operatorResponseQuery, true);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Wrong amount of dice.");
    }

    @Test
    public void shouldReturnNoDiceWhileDungeonMaster() {
        int dieCount = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(operatorResponseQuery, false);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Dice not empty.");
    }

    @Test
    public void shouldReturnNoDice() {
        int dieCount = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithDice(dieCount);
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(operatorResponseQuery, true);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Dice not empty.");
    }

    @Test
    public void shouldReturnNoDiceWhenEmptyResponse() {
        int dieCount = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(operatorResponseQuery, true);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Dice not empty.");
    }

    @Test
    public void shouldReturnNoDiceWhenNullResponse() {
        int dieCount = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        UpdatedDiceResponse updatedDiceResponse = mockJsonResponseAndReturnUpdatedDiceResponse(operatorResponseQuery, true);
        Assertions.assertEquals(dieCount, updatedDiceResponse.getDice().length, "Dice not empty.");
    }

    private OperatorResponseQuery createMockResponseWithDice(int dieCount) {
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
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson.toString())
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

    private UpdatedDiceResponse mockJsonResponseAndReturnUpdatedDiceResponse(OperatorResponseQuery operatorResponseQuery, boolean isPlayer) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
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