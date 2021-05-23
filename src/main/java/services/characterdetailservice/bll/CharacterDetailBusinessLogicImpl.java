package services.characterdetailservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import objects.Character;
import objects.DungeonMaster;
import objects.Player;
import objects.Visibility;
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
import java.util.Map;

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
        Map<String, Visibility> visibilityMap = characterDetailsAndVisibilityBo.getVisibilityMap();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        Character filteredCharacter = character;
        if (player.getClass() != DungeonMaster.class) {
            ObjectMapper objectMapper = new ObjectMapper();
            String characterJson = "{}";
            String visibilityJson = "{}";
            try {
                characterJson = objectMapper.writeValueAsString(character);
                visibilityJson = objectMapper.writeValueAsString(visibilityMap);
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
                Visibility visibility = Visibility.valueOf(visibilityJsonObject.get(visibilityKey).toString().toUpperCase());
                if (characterJsonObject.containsKey(visibilityKey) &&
                        (((!playerId.equals(characterPlayerId)) && (visibility != Visibility.EVERYONE)) ||
                        ((playerId.equals(characterPlayerId)) && (visibility == Visibility.DUNGEON_MASTER))))
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
                .visibilityMap(visibilityMap)
                .build();
    }

    private CharacterDetailsAndVisibilityAndPlayerBo filterCharacterDetailsAndVisibilityAndPlayerBo(CharacterDetailsAndVisibilityAndPlayerBo characterDetailsAndVisibilityAndPlayerBo) {
        Character character = characterDetailsAndVisibilityAndPlayerBo.getCharacter();
        Map<String, Visibility> visibilityMap = characterDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        Player player = characterDetailsAndVisibilityAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        if (!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class)) {
            character = null;
            visibilityMap = null;
        }
        Map<String, Visibility> filteredVisibilityMap = visibilityMap;
        if (filteredVisibilityMap != null) {
            Character finalCharacter = character;
            visibilityMap.keySet().forEach(key -> correctVisibility(player, characterPlayerId, filteredVisibilityMap, key, finalCharacter));
        }
        return CharacterDetailsAndVisibilityAndPlayerBo
                .builder()
                .character(character)
                .visibilityMap(filteredVisibilityMap)
                .player(player)
                .build();
    }

    private void correctVisibility(Player player, String characterPlayerId, Map<String, Visibility> visibilityMap, String key, Character character) {
        String playerId = player.getId();
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson = "{}";
        try {
            characterJson = objectMapper.writeValueAsString(character);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject characterJsonObject = new JSONObject();
        try {
            characterJsonObject = (JSONObject)jsonParser.parse(characterJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!characterJsonObject.containsKey(key))
            visibilityMap.remove(key);
        else if ((player.getClass() != DungeonMaster.class) && (visibilityMap.get(key) == Visibility.DUNGEON_MASTER))
            visibilityMap.replace(key, Visibility.PLAYER);
        else if (playerId.equals(characterPlayerId) && (player.getClass() == DungeonMaster.class) && (visibilityMap.get(key) == Visibility.PLAYER))
            visibilityMap.replace(key, Visibility.DUNGEON_MASTER);
    }
}