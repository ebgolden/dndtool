package services.characterservice.bll;

import objects.Character;
import objects.Player;
import objects.Visibility;
import services.characterservice.CreateCharacterRequest;
import services.characterservice.CreateCharacterResponse;
import services.characterservice.bll.bo.CharacterAndVisibilityAndPlayerBo;
import services.characterservice.bll.bo.CharacterBo;
import java.util.Map;

public class CharacterBusinessLogicConverterImpl implements CharacterBusinessLogicConverter {
    public CharacterAndVisibilityAndPlayerBo getCharacterAndVisibilityAndPlayerBoFromCreateCharacterRequest(CreateCharacterRequest createCharacterRequest) {
        Character character = createCharacterRequest.getCharacter();
        Map<String, Visibility> visibilityMap = createCharacterRequest.getVisibilityMap();
        Player player = createCharacterRequest.getPlayer();
        return CharacterAndVisibilityAndPlayerBo
                .builder()
                .character(character)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public CreateCharacterResponse getCreateCharacterResponseFromCharacterBo(CharacterBo characterBo) {
        Character character = characterBo.getCharacter();
        return CreateCharacterResponse
                .builder()
                .character(character)
                .build();
    }
}