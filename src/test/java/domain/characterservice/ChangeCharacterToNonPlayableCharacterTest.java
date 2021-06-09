package domain.characterservice;

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
import domain.characterservice.module.CharacterModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeCharacterToNonPlayableCharacterTest {
    @Mock
    Operator mockOperator;
    private ChangeCharacterToNonPlayableCharacter changeCharacterToNonPlayableCharacter;

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
                        ChangeCharacterToNonPlayableCharacter.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        changeCharacterToNonPlayableCharacter = injector.getInstance(ChangeCharacterToNonPlayableCharacter.class);
    }

    @Test
    public void shouldReturnNonPlayableCharacter() {
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithNonPlayableCharacter();
        ChangeCharacterToNonPlayableCharacterResponse changeCharacterToNonPlayableCharacterResponse = mockResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(operatorResponseQuery);
        Assertions.assertNotNull(changeCharacterToNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter null.");
    }

    @Test
    public void shouldReturnEmptyNonPlayableCharacterWhenEmptyResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ChangeCharacterToNonPlayableCharacterResponse changeCharacterToNonPlayableCharacterResponse = mockResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(operatorResponseQuery);
        Assertions.assertNull(changeCharacterToNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter not null.");
    }

    @Test
    public void shouldReturnEmptyNonPlayableCharacterWhenNullResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        ChangeCharacterToNonPlayableCharacterResponse changeCharacterToNonPlayableCharacterResponse = mockResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(operatorResponseQuery);
        Assertions.assertNull(changeCharacterToNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter not null.");
    }

    private OperatorResponseQuery createMockResponseWithNonPlayableCharacter() {
        String queryId = "123";
        ObjectMapper objectMapper = new ObjectMapper();
        String nonPlayableCharacterJson;
        try {
            nonPlayableCharacterJson = objectMapper.writeValueAsString(NonPlayableCharacter
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            nonPlayableCharacterJson = "{}";
        }
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(nonPlayableCharacterJson)
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

    private ChangeCharacterToNonPlayableCharacterResponse mockResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(OperatorResponseQuery operatorResponseQuery) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
        ChangeCharacterToNonPlayableCharacterRequest changeCharacterToNonPlayableCharacterRequest = ChangeCharacterToNonPlayableCharacterRequest
                .builder()
                .character(Character
                        .builder()
                        .build())
                .dungeonMaster(DungeonMaster
                        .builder()
                        .build())
                .build();
        return changeCharacterToNonPlayableCharacter.getChangeCharacterToNonPlayableCharacterResponse(changeCharacterToNonPlayableCharacterRequest);
    }
}