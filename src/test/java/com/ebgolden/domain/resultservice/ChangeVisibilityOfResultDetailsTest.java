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
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfResultDetailsTest {
    @Mock
    Operator mockOperator;
    private ChangeVisibilityOfResultDetails changeVisibilityOfResultDetails;

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
                        ChangeVisibilityOfResultDetails.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        changeVisibilityOfResultDetails = injector.getInstance(ChangeVisibilityOfResultDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseJsonWithResultVisibilityOfId(playerId);
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String resultPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseJsonWithResultVisibilityOfId(resultPlayerId);
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(operatorResponseQuery, playerId, resultPlayerId, false);
        Assertions.assertNotNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String resultPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(operatorResponseQuery, playerId, resultPlayerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility not null.");
    }

    private OperatorResponseQuery createMockResponseJsonWithResultVisibilityOfId(String resultPlayerId) {
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
            visibilityJson = objectMapper.writeValueAsString(Visibility.PLAYER);
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

    private ChangeVisibilityOfResultDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String resultPlayerId, boolean isPlayer) {
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
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        ChangeVisibilityOfResultDetailsRequest changeVisibilityOfUpdatedResultRequest = ChangeVisibilityOfResultDetailsRequest
                .builder()
                .result(Result
                        .builder()
                        .playerId(resultPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return changeVisibilityOfResultDetails.getChangeVisibilityOfResultDetailsResponse(changeVisibilityOfUpdatedResultRequest);
    }
}