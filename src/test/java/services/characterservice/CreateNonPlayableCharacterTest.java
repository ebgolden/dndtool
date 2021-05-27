package services.characterservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.characterservice.module.CharacterModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateNonPlayableCharacterTest {
    @Mock
    DataOperator mockDataOperator;
    private CreateNonPlayableCharacter createNonPlayableCharacter;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CharacterModule(CreateNonPlayableCharacter.class));
        createNonPlayableCharacter = injector.getInstance(CreateNonPlayableCharacter.class);
    }

    @Test
    public void shouldReturnNonPlayableCharacter() {
        String dungeonMasterId = "1";
        String responseJson = createMockResponseJson(dungeonMasterId);
        CreateNonPlayableCharacterResponse createNonPlayableCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateNonPlayableCharacterResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(createNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter null.");
    }

    @Test
    public void shouldReturnEmptyNonPlayableCharacterWhileDifferentDM() {
        String dungeonMasterId = "2";
        String nonPlayableCharacterDungeonMasterId = "1";
        String responseJson = "{}";
        CreateNonPlayableCharacterResponse createNonPlayableCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateNonPlayableCharacterResponse(responseJson, dungeonMasterId, nonPlayableCharacterDungeonMasterId);
        Assertions.assertNull(createNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter not null.");
    }

    @Test
    public void shouldReturnEmptyNonPlayableCharacterWhenEmptyJson() {
        String dungeonMasterId = "1";
        String responseJson = "{}";
        CreateNonPlayableCharacterResponse createNonPlayableCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateNonPlayableCharacterResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(createNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter not null.");
    }

    @Test
    public void shouldReturnEmptyNonPlayableCharacterWhenNullJson() {
        String dungeonMasterId = "1";
        CreateNonPlayableCharacterResponse createNonPlayableCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateNonPlayableCharacterResponse(null, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(createNonPlayableCharacterResponse.getNonPlayableCharacter(), "NonPlayableCharacter not null.");
    }

    private String createMockResponseJson(String nonPlayableCharacterDungeonMasterId) {
        ObjectMapper objectMapper = new ObjectMapper();
        String nonPlayableCharacterJson;
        try {
            nonPlayableCharacterJson = objectMapper.writeValueAsString(NonPlayableCharacter
                    .builder()
                    .playerId(nonPlayableCharacterDungeonMasterId)
                    .build());
        } catch (JsonProcessingException e) {
            nonPlayableCharacterJson = "{}";
        }
        return nonPlayableCharacterJson;
    }

    private CreateNonPlayableCharacterResponse mockJsonResponseAsPlayerOrDMAndReturnCreateNonPlayableCharacterResponse(String responseJson, String dungeonMasterId, String nonPlayableCharacterDungeonMasterId) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        DungeonMaster dungeonMaster = DungeonMaster
                .builder()
                .id(dungeonMasterId)
                .build();
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        CreateNonPlayableCharacterRequest createNonPlayableCharacterRequest = CreateNonPlayableCharacterRequest
                .builder()
                .nonPlayableCharacter(NonPlayableCharacter
                        .builder()
                        .playerId(nonPlayableCharacterDungeonMasterId)
                        .build())
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
        return createNonPlayableCharacter.getCreateNonPlayableCharacterResponse(createNonPlayableCharacterRequest);
    }
}