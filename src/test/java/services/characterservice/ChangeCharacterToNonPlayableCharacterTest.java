package services.characterservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import commonobjects.Character;
import commonobjects.DataOperator;
import commonobjects.DungeonMaster;
import commonobjects.NonPlayableCharacter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.characterservice.module.CharacterModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeCharacterToNonPlayableCharacterTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeCharacterToNonPlayableCharacter changeCharacterToNonPlayableCharacter;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CharacterModule(ChangeCharacterToNonPlayableCharacter.class));
        changeCharacterToNonPlayableCharacter = injector.getInstance(ChangeCharacterToNonPlayableCharacter.class);
    }

    @Test
    public void shouldReturnNonPlayableCharacter() {
        String responseJson = createMockResponseJson();
        ChangeCharacterToNonPlayableCharacterResponse changeCharacterToNonPlayableCharacterResponse = mockJsonResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(responseJson);
        Assertions.assertNotNull(changeCharacterToNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter null.");
    }

    @Test
    public void shouldReturnEmptyNonPlayableCharacterWhenEmptyJson() {
        String responseJson = "{}";
        ChangeCharacterToNonPlayableCharacterResponse changeCharacterToNonPlayableCharacterResponse = mockJsonResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(responseJson);
        Assertions.assertNull(changeCharacterToNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter not null.");
    }

    @Test
    public void shouldReturnEmptyNonPlayableCharacterWhenNullJson() {
        ChangeCharacterToNonPlayableCharacterResponse changeCharacterToNonPlayableCharacterResponse = mockJsonResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(null);
        Assertions.assertNull(changeCharacterToNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter not null.");
    }

    private String createMockResponseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String nonPlayableCharacterJson;
        try {
            nonPlayableCharacterJson = objectMapper.writeValueAsString(NonPlayableCharacter
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            nonPlayableCharacterJson = "{}";
        }
        return nonPlayableCharacterJson;
    }

    private ChangeCharacterToNonPlayableCharacterResponse mockJsonResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(String responseJson) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
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