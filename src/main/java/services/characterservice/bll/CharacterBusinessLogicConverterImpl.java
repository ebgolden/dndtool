package services.characterservice.bll;

import commonobjects.*;
import commonobjects.Character;
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

    public NonPlayableCharacterAndDungeonMasterBo getNonPlayableCharacterAndDungeonMasterBoFromChangeNonPlayableCharacterToCharacterRequest(ChangeNonPlayableCharacterToCharacterRequest changeNonPlayableCharacterToCharacterRequest) {
        NonPlayableCharacter nonPlayableCharacter = changeNonPlayableCharacterToCharacterRequest.getNonPlayableCharacter();
        DungeonMaster dungeonMaster = changeNonPlayableCharacterToCharacterRequest.getDungeonMaster();
        return NonPlayableCharacterAndDungeonMasterBo
                .builder()
                .nonPlayableCharacter(nonPlayableCharacter)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    public CharacterAndPlayerBo getCharacterAndPlayerBoFromUpdatedCharacterRequest(UpdatedCharacterRequest updatedCharacterRequest) {
        Character character = updatedCharacterRequest.getCharacter();
        Player player = updatedCharacterRequest.getPlayer();
        return CharacterAndPlayerBo
                .builder()
                .character(character)
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

    public ChangeNonPlayableCharacterToCharacterResponse getChangeNonPlayableCharacterToCharacterResponseFromCharacterBo(CharacterBo characterBo) {
        Character character = characterBo.getCharacter();
        return ChangeNonPlayableCharacterToCharacterResponse
                .builder()
                .character(character)
                .build();
    }

    public UpdatedCharacterResponse getUpdatedCharacterResponseFromCharacterBo(CharacterAndVisibilityBo characterAndVisibilityBo) {
        Character character = characterAndVisibilityBo.getCharacter();
        return UpdatedCharacterResponse
                .builder()
                .character(character)
                .build();
    }

    public ChangeVisibilityOfCharacterDetailsResponse getChangeVisibilityOfCharacterDetailsResponseFromCharacterAndVisibilityBo(CharacterAndVisibilityBo characterAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = characterAndVisibilityBo.getVisibilityMap();
        return ChangeVisibilityOfCharacterDetailsResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public CharacterAndVisibilityAndPlayerBo getCharacterAndVisibilityAndPlayerBoFromChangeVisibilityOfCharacterDetailsRequest(ChangeVisibilityOfCharacterDetailsRequest changeVisibilityOfUpdatedCharacterRequest) {
        Character character = changeVisibilityOfUpdatedCharacterRequest.getCharacter();
        Map<String, Visibility> visibilityMap = changeVisibilityOfUpdatedCharacterRequest.getVisibilityMap();
        Player player = changeVisibilityOfUpdatedCharacterRequest.getPlayer();
        return CharacterAndVisibilityAndPlayerBo
                .builder()
                .character(character)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public CharacterAndVisibilityBo getCharacterAndVisibilityBoFromCharacterAndVisibilityAndPlayerBo(CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo) {
        Character character = characterAndVisibilityAndPlayerBo.getCharacter();
        Map<String, Visibility> visibilityMap = characterAndVisibilityAndPlayerBo.getVisibilityMap();
        return CharacterAndVisibilityBo
                .builder()
                .character(character)
                .visibilityMap(visibilityMap)
                .build();
    }
}