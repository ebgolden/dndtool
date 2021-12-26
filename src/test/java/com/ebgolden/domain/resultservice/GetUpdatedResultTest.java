package com.ebgolden.domain.resultservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.ebgolden.common.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ebgolden.domain.resultservice.module.ResultModule;
import com.ebgolden.persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedResultTest {
    @Mock
    Operator mockOperator;
    private GetUpdatedResult getUpdatedResult;

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
        Injector injector = Guice.createInjector(new ResultModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        GetUpdatedResult.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        getUpdatedResult = injector.getInstance(GetUpdatedResult.class);
    }

    @Test
    public void shouldReturnResultWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithResultWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String resultPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithResultWithVisibilityOfId(resultPlayerId, Visibility.EVERYONE);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(operatorResponseQuery, playerId, resultPlayerId, false);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String resultPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithResultWithVisibilityOfId(resultPlayerId, Visibility.EVERYONE);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(operatorResponseQuery, playerId, resultPlayerId, true);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithResultWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String resultPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithResultWithVisibilityOfId(resultPlayerId, Visibility.PLAYER);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(operatorResponseQuery, playerId, resultPlayerId, false);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String resultPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithResultWithVisibilityOfId(resultPlayerId, Visibility.PLAYER);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(operatorResponseQuery, playerId, resultPlayerId, true);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() == null)), "Result not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithResultWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() == null)), "Result not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String resultPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithResultWithVisibilityOfId(resultPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(operatorResponseQuery, playerId, resultPlayerId, false);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String resultPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithResultWithVisibilityOfId(resultPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(operatorResponseQuery, playerId, resultPlayerId, true);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() == null)), "Result not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnEmptyResultWhenEmptyResponse() {
        String playerId = "0";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedResultResponse.getResult(), "Result not null.");
    }

    @Test
    public void shouldReturnEmptyResultWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedResultResponse.getResult(), "Result not null.");
    }

    private OperatorResponseQuery createMockResponseWithResultWithVisibilityOfId(String resultPlayerId, Visibility idVisibility) {
        String queryId = "123";
        StringBuilder responseJson = new StringBuilder("{\"result\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson;
        String visibilityJson;
        String resultId = "1";
        try {
            resultJson = objectMapper.writeValueAsString(Result
                    .builder()
                    .id(resultId)
                    .playerId(resultPlayerId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(idVisibility);
        } catch (JsonProcessingException e) {
            resultJson = "{}";
            visibilityJson = "{}";
        }
        responseJson
                .append(resultJson)
                .append(",\"visibility\":{\"id\":")
                .append(visibilityJson)
                .append("}}");
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

    private UpdatedResultResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String resultPlayerId, boolean isPlayer) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
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
        UpdatedResultRequest updatedResultRequest = UpdatedResultRequest
                .builder()
                .result(Result
                        .builder()
                        .playerId(resultPlayerId)
                        .build())
                .player(player)
                .build();
        return getUpdatedResult.getUpdatedResultResponse(updatedResultRequest);
    }
}