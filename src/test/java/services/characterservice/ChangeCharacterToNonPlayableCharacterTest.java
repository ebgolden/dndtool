package services.characterservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.Character;
import objects.DataOperator;
import objects.DungeonMaster;
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
    DataOperator<ChangeCharacterToNonPlayableCharacter> mockDataOperator;
    private ChangeCharacterToNonPlayableCharacter changeCharacterToNonPlayableCharacter;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CharacterModule());
        changeCharacterToNonPlayableCharacter = injector.getInstance(ChangeCharacterToNonPlayableCharacter.class);
    }

    @Test
    public void shouldReturnCharacter() {
        String responseJson = createMockResponseJson();
        ChangeCharacterToNonPlayableCharacterResponse changeCharacterToNonPlayableCharacterResponse = mockJsonResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(responseJson);
        Assertions.assertNotNull(changeCharacterToNonPlayableCharacterResponse.getNonPlayableCharacter(), "Character null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhenEmptyJson() {
        String responseJson = "{}";
        ChangeCharacterToNonPlayableCharacterResponse changeCharacterToNonPlayableCharacterResponse = mockJsonResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(responseJson);
        Assertions.assertNull(changeCharacterToNonPlayableCharacterResponse.getNonPlayableCharacter(), "Character not null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhenNullJson() {
        ChangeCharacterToNonPlayableCharacterResponse changeCharacterToNonPlayableCharacterResponse = mockJsonResponseAndReturnChangeCharacterToNonPlayableCharacterResponse(null);
        Assertions.assertNull(changeCharacterToNonPlayableCharacterResponse.getNonPlayableCharacter(), "Character not null.");
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