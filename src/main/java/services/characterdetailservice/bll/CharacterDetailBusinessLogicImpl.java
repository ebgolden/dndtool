package services.characterdetailservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import objects.Character;
import objects.DungeonMaster;
import objects.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.characterdetailservice.bll.bo.CharacterAndPlayerBo;
import services.characterdetailservice.bll.bo.CharacterDetailsAndVisibilityAndPlayerBo;
import services.characterdetailservice.bll.bo.CharacterDetailsAndVisibilityBo;
import services.characterdetailservice.dal.CharacterDetailDataAccess;
import services.characterdetailservice.dal.CharacterDetailDataAccessConverter;
import services.characterdetailservice.dal.dao.CharacterDao;
import services.characterdetailservice.dal.dao.CharacterDetailsAndVisibilityDao;

public class CharacterDetailBusinessLogicImpl implements CharacterDetailBusinessLogic {
    @Inject
    private CharacterDetailDataAccessConverter characterDetailDataAccessConverter;
    @Inject
    private CharacterDetailDataAccess characterDetailDataAccess;
    @Inject
    private CharacterDetailBusinessLogicConverter characterDetailBusinessLogicConverter;

    public CharacterDetailsAndVisibilityBo getCharacterDetailsAndVisibilityBo(CharacterAndPlayerBo characterAndPlayerBo) {
        CharacterDao characterDao = characterDetailDataAccessConverter.getCharacterDaoFromCharacterAndPlayerBo(characterAndPlayerBo);
        CharacterDetailsAndVisibilityDao characterDetailsAndVisibilityDao = characterDetailDataAccess.getCharacterDetailsAndVisibilityDao(characterDao);
        CharacterDetailsAndVisibilityBo characterDetailsAndVisibilityBo = characterDetailDataAccessConverter.getCharacterDetailsAndVisibilityBoFromCharacterDetailsAndVisibilityDao(characterDetailsAndVisibilityDao);
        Player player = characterAndPlayerBo.getPlayer();
        if (characterDetailsAndVisibilityBo.getCharacter() == null)
            return characterDetailsAndVisibilityBo;
        return filterCharacterDetailsAndVisibilityBo(characterDetailsAndVisibilityBo, player);
    }

    public CharacterDetailsAndVisibilityBo getCharacterDetailsAndVisibilityBo(CharacterDetailsAndVisibilityAndPlayerBo characterDetailsAndVisibilityAndPlayerBo) {
        CharacterDetailsAndVisibilityAndPlayerBo filteredCharacterDetailsAndVisibilityAndPlayerBo = filterCharacterDetailsAndVisibilityAndPlayerBo(characterDetailsAndVisibilityAndPlayerBo);
        CharacterDetailsAndVisibilityBo characterDetailsAndVisibilityBo = characterDetailBusinessLogicConverter.getCharacterDetailsAndVisibilityBoFromCharacterDetailsAndVisibilityAndPlayerBo(filteredCharacterDetailsAndVisibilityAndPlayerBo);
        CharacterDetailsAndVisibilityDao characterDetailsAndVisibilityDao = characterDetailDataAccessConverter.getCharacterDetailsAndVisibilityDaoFromCharacterDetailsAndVisibilityBo(characterDetailsAndVisibilityBo);
        characterDetailsAndVisibilityDao = characterDetailDataAccess.getCharacterDetailsAndVisibilityDao(characterDetailsAndVisibilityDao);
        return characterDetailDataAccessConverter.getCharacterDetailsAndVisibilityBoFromCharacterDetailsAndVisibilityDao(characterDetailsAndVisibilityDao);
    }

    private CharacterDetailsAndVisibilityBo filterCharacterDetailsAndVisibilityBo(CharacterDetailsAndVisibilityBo characterDetailsAndVisibilityBo, Player player) {
        Character character = characterDetailsAndVisibilityBo.getCharacter();
        String visibilityJson = characterDetailsAndVisibilityBo.getVisibilityJson();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        Character filteredCharacter = character;
        if (!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String characterJson = "{}";
            try {
                characterJson = objectMapper.writeValueAsString(character);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject characterJsonObject = new JSONObject();
            JSONObject visibilityJsonObject = new JSONObject();
            try {
                characterJsonObject = (JSONObject)jsonParser.parse(characterJson);
                visibilityJsonObject = (JSONObject)jsonParser.parse(visibilityJson);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String[] visibilityKeys = new String[] {};
            visibilityKeys = (String[])visibilityJsonObject
                    .keySet()
                    .toArray(visibilityKeys);
            for (String visibilityKey : visibilityKeys) {
                if (characterJsonObject.containsKey(visibilityKey) && (!Boolean.parseBoolean(visibilityJsonObject.get(visibilityKey).toString())))
                    characterJsonObject.remove(visibilityKey);
            }
            JSONObject filteredCharacterJsonObject = characterJsonObject;
            String filteredCharacterJson = filteredCharacterJsonObject.toJSONString();
            try {
                filteredCharacter = objectMapper.readValue(filteredCharacterJson, Character.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return CharacterDetailsAndVisibilityBo
                .builder()
                .character(filteredCharacter)
                .visibilityJson(visibilityJson)
                .build();
    }

    private CharacterDetailsAndVisibilityAndPlayerBo filterCharacterDetailsAndVisibilityAndPlayerBo(CharacterDetailsAndVisibilityAndPlayerBo characterDetailsAndVisibilityAndPlayerBo) {
        Character character = characterDetailsAndVisibilityAndPlayerBo.getCharacter();
        String visibilityJson = characterDetailsAndVisibilityAndPlayerBo.getVisibilityJson();
        Player player = characterDetailsAndVisibilityAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        if (!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class)) {
            visibilityJson = "{}";
            character = null;
        }
        return CharacterDetailsAndVisibilityAndPlayerBo
                .builder()
                .character(character)
                .visibilityJson(visibilityJson)
                .player(player)
                .build();
    }
}