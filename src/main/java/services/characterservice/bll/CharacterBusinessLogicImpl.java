package services.characterservice.bll;

import com.google.inject.Inject;
import objects.*;
import objects.Character;
import services.characterservice.bll.bo.*;
import services.characterservice.dal.CharacterDataAccess;
import services.characterservice.dal.CharacterDataAccessConverter;
import services.characterservice.dal.dao.CharacterAndVisibilityAndPlayerDao;
import services.characterservice.dal.dao.CharacterDao;
import services.characterservice.dal.dao.NonPlayableCharacterAndVisibilityAndDungeonMasterDao;
import services.characterservice.dal.dao.NonPlayableCharacterDao;
import java.util.Map;

public class CharacterBusinessLogicImpl implements CharacterBusinessLogic {
    @Inject
    private CharacterDataAccessConverter characterDataAccessConverter;
    @Inject
    private CharacterDataAccess characterDataAccess;

    public CharacterBo getCharacterBo(CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo) {
        CharacterAndVisibilityAndPlayerBo filteredCharacterAndVisibilityAndPlayerBo = filterCharacterAndVisibilityAndPlayerBo(characterAndVisibilityAndPlayerBo);
        CharacterAndVisibilityAndPlayerDao characterDetailsAndVisibilityDao = characterDataAccessConverter.getCharacterAndVisibilityAndPlayerDaoFromCharacterAndVisibilityAndPlayerBo(filteredCharacterAndVisibilityAndPlayerBo);
        CharacterDao characterDao = characterDataAccess.getCharacterDao(characterDetailsAndVisibilityDao);
        return characterDataAccessConverter.getCharacterBoFromCharacterDao(characterDao);
    }

    public NonPlayableCharacterBo getNonPlayableCharacterBo(NonPlayableCharacterAndVisibilityAndDungeonMasterBo nonPlayableCharacterAndVisibilityAndDungeonMasterBo) {
        NonPlayableCharacterAndVisibilityAndDungeonMasterBo filteredNonPlayableCharacterAndVisibilityAndDungeonMasterBo = filterNonPlayableCharacterAndVisibilityAndDungeonMasterBo(nonPlayableCharacterAndVisibilityAndDungeonMasterBo);
        NonPlayableCharacterAndVisibilityAndDungeonMasterDao nonPlayableCharacterAndVisibilityAndDungeonMasterDao = characterDataAccessConverter.getNonPlayableCharacterAndVisibilityAndDungeonMasterDaoFromNonPlayableCharacterAndVisibilityAndDungeonMasterBo(filteredNonPlayableCharacterAndVisibilityAndDungeonMasterBo);
        NonPlayableCharacterDao nonPlayableCharacterDao = characterDataAccess.getNonPlayableCharacterDao(nonPlayableCharacterAndVisibilityAndDungeonMasterDao);
        return characterDataAccessConverter.getNonPlayableCharacterBoFromNonPlayableCharacterDao(nonPlayableCharacterDao);
    }

    public NonPlayableCharacterBo getNonPlayableCharacterBo(CharacterAndDungeonMasterBo characterAndDungeonMasterBo) {
        CharacterDao characterDao = characterDataAccessConverter.getCharacterDaoFromCharacterAndDungeonMasterBo(characterAndDungeonMasterBo);
        NonPlayableCharacterDao nonPlayableCharacterDao = characterDataAccess.getNonPlayableCharacterDao(characterDao);
        return characterDataAccessConverter.getNonPlayableCharacterBoFromNonPlayableCharacterDao(nonPlayableCharacterDao);
    }

    private CharacterAndVisibilityAndPlayerBo filterCharacterAndVisibilityAndPlayerBo(CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo) {
        Character character = characterAndVisibilityAndPlayerBo.getCharacter();
        Map<String, Visibility> visibilityMap = characterAndVisibilityAndPlayerBo.getVisibilityMap();
        Player player = characterAndVisibilityAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        if (!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class)) {
            character = null;
            visibilityMap = null;
        }
        return CharacterAndVisibilityAndPlayerBo
                .builder()
                .character(character)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    NonPlayableCharacterAndVisibilityAndDungeonMasterBo filterNonPlayableCharacterAndVisibilityAndDungeonMasterBo(NonPlayableCharacterAndVisibilityAndDungeonMasterBo nonPlayableCharacterAndVisibilityAndDungeonMasterBo) {
        NonPlayableCharacter nonPlayableCharacter = nonPlayableCharacterAndVisibilityAndDungeonMasterBo.getNonPlayableCharacter();
        Map<String, Visibility> visibilityMap = nonPlayableCharacterAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        DungeonMaster dungeonMaster = nonPlayableCharacterAndVisibilityAndDungeonMasterBo.getDungeonMaster();
        String dungeonMasterId = dungeonMaster.getId();
        String nonPlayableCharacterDungeonMasterId = nonPlayableCharacter.getPlayerId();
        if (!dungeonMasterId.equals(nonPlayableCharacterDungeonMasterId)) {
            nonPlayableCharacter = null;
            visibilityMap = null;
        }
        return NonPlayableCharacterAndVisibilityAndDungeonMasterBo
                .builder()
                .nonPlayableCharacter(nonPlayableCharacter)
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
    }
}