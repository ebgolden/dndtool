package services.characterservice.bll;

import objects.*;
import objects.Character;
import services.characterservice.*;
import services.characterservice.bll.bo.*;
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

    public NonPlayableCharacterAndVisibilityAndDungeonMasterBo getNonPlayableCharacterAndVisibilityAndDungeonMasterBoFromCreateNonPlayableCharacterRequest(CreateNonPlayableCharacterRequest createNonPlayableCharacterRequest) {
        NonPlayableCharacter nonPlayableCharacter = createNonPlayableCharacterRequest.getNonPlayableCharacter();
        Map<String, Visibility> visibilityMap = createNonPlayableCharacterRequest.getVisibilityMap();
        DungeonMaster dungeonMaster = createNonPlayableCharacterRequest.getDungeonMaster();
        return NonPlayableCharacterAndVisibilityAndDungeonMasterBo
                .builder()
                .nonPlayableCharacter(nonPlayableCharacter)
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    public CharacterAndDungeonMasterBo getCharacterAndDungeonMasterBoFromChangeCharacterToNonPlayableCharacterRequest(ChangeCharacterToNonPlayableCharacterRequest changeCharacterToNonPlayableCharacterRequest) {
        Character character = changeCharacterToNonPlayableCharacterRequest.getCharacter();
        DungeonMaster dungeonMaster = changeCharacterToNonPlayableCharacterRequest.getDungeonMaster();
        return CharacterAndDungeonMasterBo
                .builder()
                .character(character)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    public CreateCharacterResponse getCreateCharacterResponseFromCharacterBo(CharacterBo characterBo) {
        Character character = characterBo.getCharacter();
        return CreateCharacterResponse
                .builder()
                .character(character)
                .build();
    }

    public CreateNonPlayableCharacterResponse getCreateNonPlayableCharacterResponseFromNonPlayableCharacterBo(NonPlayableCharacterBo nonPlayableCharacterBo) {
        NonPlayableCharacter nonPlayableCharacter = nonPlayableCharacterBo.getNonPlayableCharacter();
        return CreateNonPlayableCharacterResponse
                .builder()
                .nonPlayableCharacter(nonPlayableCharacter)
                .build();
    }

    public ChangeCharacterToNonPlayableCharacterResponse getChangeCharacterToNonPlayableCharacterResponseFromNonPlayableCharacterBo(NonPlayableCharacterBo nonPlayableCharacterBo) {
        NonPlayableCharacter nonPlayableCharacter = nonPlayableCharacterBo.getNonPlayableCharacter();
        return ChangeCharacterToNonPlayableCharacterResponse
                .builder()
                .nonPlayableCharacter(nonPlayableCharacter)
                .build();
    }
}