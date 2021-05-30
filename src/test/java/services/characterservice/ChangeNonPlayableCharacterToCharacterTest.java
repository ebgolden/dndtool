package services.characterservice;

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
import services.characterservice.module.CharacterModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeNonPlayableCharacterToCharacterTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeNonPlayableCharacterToCharacter changeNonPlayableCharacterToCharacter;

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
        Injector injector = Guice.createInjector(new CharacterModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        senderPlayer,
                        ChangeNonPlayableCharacterToCharacter.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        changeNonPlayableCharacterToCharacter = injector.getInstance(ChangeNonPlayableCharacterToCharacter.class);
    }

    @Test
    public void shouldReturnCharacter() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacter();
        ChangeNonPlayableCharacterToCharacterResponse changeNonPlayableCharacterToCharacterResponse = mockResponseAndReturnChangeNonPlayableCharacterToCharacterResponse(dataOperatorResponseQuery);
        Assertions.assertNotNull(changeNonPlayableCharacterToCharacterResponse.getCharacter(), "Character null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhenEmptyResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeNonPlayableCharacterToCharacterResponse changeNonPlayableCharacterToCharacterResponse = mockResponseAndReturnChangeNonPlayableCharacterToCharacterResponse(dataOperatorResponseQuery);
        Assertions.assertNull(changeNonPlayableCharacterToCharacterResponse.getCharacter(), "Character not null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhenNullResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        ChangeNonPlayableCharacterToCharacterResponse changeNonPlayableCharacterToCharacterResponse = mockResponseAndReturnChangeNonPlayableCharacterToCharacterResponse(dataOperatorResponseQuery);
        Assertions.assertNull(changeNonPlayableCharacterToCharacterResponse.getCharacter(), "Character not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithCharacter() {
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
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(characterJson)
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

    private ChangeNonPlayableCharacterToCharacterResponse mockResponseAndReturnChangeNonPlayableCharacterToCharacterResponse(DataOperatorResponseQuery dataOperatorResponseQuery) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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