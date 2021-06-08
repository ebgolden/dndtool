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
public class ChangeCharacterToNonPlayableCharacterTest {
    @Mock
    DataOperator mockDataOperator;
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
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        changeCharacterToNonPlayableCharacter = injector.getInstance(ChangeCharacterToNonPlayableCharacter.class);
    }

    @Test
    public void shouldReturnNonPlayableCharacter() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithNonPlayableCharacter();
        ChangeCharacterToNonPlayableCharacterResponse changeCharacterToNonPlayableCharacterResponse = mockResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(dataOperatorResponseQuery);
        Assertions.assertNotNull(changeCharacterToNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter null.");
    }

    @Test
    public void shouldReturnEmptyNonPlayableCharacterWhenEmptyResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeCharacterToNonPlayableCharacterResponse changeCharacterToNonPlayableCharacterResponse = mockResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(dataOperatorResponseQuery);
        Assertions.assertNull(changeCharacterToNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter not null.");
    }

    @Test
    public void shouldReturnEmptyNonPlayableCharacterWhenNullResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        ChangeCharacterToNonPlayableCharacterResponse changeCharacterToNonPlayableCharacterResponse = mockResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(dataOperatorResponseQuery);
        Assertions.assertNull(changeCharacterToNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithNonPlayableCharacter() {
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
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(nonPlayableCharacterJson)
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

    private ChangeCharacterToNonPlayableCharacterResponse mockResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(DataOperatorResponseQuery dataOperatorResponseQuery) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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