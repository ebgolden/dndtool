package services.characterservice.bll;

import com.google.inject.Inject;
import objects.Character;
import objects.DungeonMaster;
import objects.Player;
import objects.Visibility;
import services.characterservice.bll.bo.CharacterAndVisibilityAndPlayerBo;
import services.characterservice.bll.bo.CharacterBo;
import services.characterservice.dal.CharacterDataAccess;
import services.characterservice.dal.CharacterDataAccessConverter;
import services.characterservice.dal.dao.CharacterAndVisibilityAndPlayerDao;
import services.characterservice.dal.dao.CharacterDao;
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
}