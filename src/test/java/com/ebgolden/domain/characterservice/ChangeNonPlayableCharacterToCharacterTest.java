package com.ebgolden.domain.characterservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.ebgolden.common.*;
import com.ebgolden.common.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ebgolden.domain.characterservice.module.CharacterModule;
import com.ebgolden.persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeNonPlayableCharacterToCharacterTest {
    @Mock
    Operator mockOperator;
    private ChangeNonPlayableCharacterToCharacter changeNonPlayableCharacterToCharacter;

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
        Injector injector = Guice.createInjector(new CharacterModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        ChangeNonPlayableCharacterToCharacter.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        changeNonPlayableCharacterToCharacter = injector.getInstance(ChangeNonPlayableCharacterToCharacter.class);
    }

    @Test
    public void shouldReturnCharacter() {
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacter();
        ChangeNonPlayableCharacterToCharacterResponse changeNonPlayableCharacterToCharacterResponse = mockResponseAndReturnChangeNonPlayableCharacterToCharacterResponse(operatorResponseQuery);
        Assertions.assertNotNull(changeNonPlayableCharacterToCharacterResponse.getCharacter(), "Character null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhenEmptyResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ChangeNonPlayableCharacterToCharacterResponse changeNonPlayableCharacterToCharacterResponse = mockResponseAndReturnChangeNonPlayableCharacterToCharacterResponse(operatorResponseQuery);
        Assertions.assertNull(changeNonPlayableCharacterToCharacterResponse.getCharacter(), "Character not null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhenNullResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        ChangeNonPlayableCharacterToCharacterResponse changeNonPlayableCharacterToCharacterResponse = mockResponseAndReturnChangeNonPlayableCharacterToCharacterResponse(operatorResponseQuery);
        Assertions.assertNull(changeNonPlayableCharacterToCharacterResponse.getCharacter(), "Character not null.");
    }

    private OperatorResponseQuery createMockResponseWithCharacter() {
        String queryId = "123";
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson;
        try {
            characterJson = objectMapper.writeValueAsString(Character
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            characterJson = "{}";
        }
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(characterJson)
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

    private ChangeNonPlayableCharacterToCharacterResponse mockResponseAndReturnChangeNonPlayableCharacterToCharacterResponse(OperatorResponseQuery operatorResponseQuery) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
        ChangeNonPlayableCharacterToCharacterRequest changeNonPlayableCharacterToCharacterRequest = ChangeNonPlayableCharacterToCharacterRequest
                .builder()
                .nonPlayableCharacter(NonPlayableCharacter
                        .builder()
                        .build())
                .dungeonMaster(DungeonMaster
                        .builder()
                        .build())
                .build();
        return changeNonPlayableCharacterToCharacter.getChangeNonPlayableCharacterToCharacterResponse(changeNonPlayableCharacterToCharacterRequest);
    }
}