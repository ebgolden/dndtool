package services.characterservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.Character;
import objects.DataOperator;
import objects.DungeonMaster;
import objects.NonPlayableCharacter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.characterservice.module.CharacterModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeNonPlayableCharacterToCharacterTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeNonPlayableCharacterToCharacter changeNonPlayableCharacterToCharacter;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CharacterModule(ChangeNonPlayableCharacterToCharacter.class));
        changeNonPlayableCharacterToCharacter = injector.getInstance(ChangeNonPlayableCharacterToCharacter.class);
    }

    @Test
    public void shouldReturnCharacter() {
        String responseJson = createMockResponseJson();
        ChangeNonPlayableCharacterToCharacterResponse changeNonPlayableCharacterToCharacterResponse = mockJsonResponseAndReturnChangeNonPlayableCharacterToCharacterResponse(responseJson);
        Assertions.assertNotNull(changeNonPlayableCharacterToCharacterResponse.getCharacter(), "Character null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhenEmptyJson() {
        String responseJson = "{}";
        ChangeNonPlayableCharacterToCharacterResponse changeNonPlayableCharacterToCharacterResponse = mockJsonResponseAndReturnChangeNonPlayableCharacterToCharacterResponse(responseJson);
        Assertions.assertNull(changeNonPlayableCharacterToCharacterResponse.getCharacter(), "Character not null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhenNullJson() {
        ChangeNonPlayableCharacterToCharacterResponse changeNonPlayableCharacterToCharacterResponse = mockJsonResponseAndReturnChangeNonPlayableCharacterToCharacterResponse(null);
        Assertions.assertNull(changeNonPlayableCharacterToCharacterResponse.getCharacter(), "Character not null.");
    }

    private String createMockResponseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson;
        try {
            characterJson = objectMapper.writeValueAsString(Character
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            characterJson = "{}";
        }
        return characterJson;
    }

    private ChangeNonPlayableCharacterToCharacterResponse mockJsonResponseAndReturnChangeNonPlayableCharacterToCharacterResponse(String responseJson) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
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