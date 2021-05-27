package services.characterservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import commonobjects.*;
import commonobjects.Character;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.characterservice.bll.bo.*;
import services.characterservice.dal.CharacterDataAccess;
import services.characterservice.dal.CharacterDataAccessConverter;
import services.characterservice.dal.dao.*;
import java.util.Map;

public class CharacterBusinessLogicImpl implements CharacterBusinessLogic {
    @Inject
    private CharacterDataAccessConverter characterDataAccessConverter;
    @Inject
    private CharacterDataAccess characterDataAccess;
    @Inject
    private CharacterBusinessLogicConverter characterBusinessLogicConverter;

    public CharacterBo getCharacterBo(CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo) {
        CharacterAndVisibilityAndPlayerBo filteredCharacterAndVisibilityAndPlayerBo = filterCharacterAndVisibilityAndPlayerBo(characterAndVisibilityAndPlayerBo);
        CharacterAndVisibilityAndPlayerDao characterAndVisibilityDao = characterDataAccessConverter.getCharacterAndVisibilityAndPlayerDaoFromCharacterAndVisibilityAndPlayerBo(filteredCharacterAndVisibilityAndPlayerBo);
        CharacterDao characterDao = characterDataAccess.getCharacterDao(characterAndVisibilityDao);
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

    public CharacterBo getCharacterBo(NonPlayableCharacterAndDungeonMasterBo nonPlayableCharacterAndDungeonMasterBo) {
        NonPlayableCharacterDao nonPlayableCharacterDao = characterDataAccessConverter.getNonPlayableCharacterDaoFromNonPlayableCharacterAndDungeonMasterBo(nonPlayableCharacterAndDungeonMasterBo);
        CharacterDao characterDao = characterDataAccess.getCharacterDao(nonPlayableCharacterDao);
        return characterDataAccessConverter.getCharacterBoFromCharacterDao(characterDao);
    }

    public CharacterAndVisibilityBo getCharacterAndVisibilityBo(CharacterAndPlayerBo characterAndPlayerBo) {
        CharacterDao characterDao = characterDataAccessConverter.getCharacterDaoFromCharacterAndPlayerBo(characterAndPlayerBo);
        CharacterAndVisibilityDao characterAndVisibilityDao = characterDataAccess.getCharacterAndVisibilityDao(characterDao);
        CharacterAndVisibilityBo characterAndVisibilityBo = characterDataAccessConverter.getCharacterAndVisibilityBoFromCharacterAndVisibilityDao(characterAndVisibilityDao);
        Player player = characterAndPlayerBo.getPlayer();
        if (characterAndVisibilityBo.getCharacter() == null)
            return characterAndVisibilityBo;
        return filterCharacterAndVisibilityBo(characterAndVisibilityBo, player);
    }

    public CharacterAndVisibilityBo getCharacterAndVisibilityBo(CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo) {
        CharacterAndVisibilityAndPlayerBo filteredCharacterAndVisibilityAndPlayerBo = filterCharacterAndVisibilityAndPlayerBo(characterAndVisibilityAndPlayerBo);
        CharacterAndVisibilityBo characterAndVisibilityBo = characterBusinessLogicConverter.getCharacterAndVisibilityBoFromCharacterAndVisibilityAndPlayerBo(filteredCharacterAndVisibilityAndPlayerBo);
        CharacterAndVisibilityDao characterAndVisibilityDao = characterDataAccessConverter.getCharacterAndVisibilityDaoFromCharacterAndVisibilityBo(characterAndVisibilityBo);
        characterAndVisibilityDao = characterDataAccess.getCharacterAndVisibilityDao(characterAndVisibilityDao);
        return characterDataAccessConverter.getCharacterAndVisibilityBoFromCharacterAndVisibilityDao(characterAndVisibilityDao);
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
        Map<String, Visibility> filteredVisibilityMap = visibilityMap;
        if (filteredVisibilityMap != null) {
            Character finalCharacter = character;
            visibilityMap.keySet().forEach(key -> correctVisibility(player, characterPlayerId, filteredVisibilityMap, key, finalCharacter));
        }
        return CharacterAndVisibilityAndPlayerBo
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

    private CharacterAndVisibilityBo filterCharacterAndVisibilityBo(CharacterAndVisibilityBo characterAndVisibilityBo, Player player) {
        Character character = characterAndVisibilityBo.getCharacter();
        Map<String, Visibility> visibilityMap = characterAndVisibilityBo.getVisibilityMap();
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
        return CharacterAndVisibilityBo
                .builder()
                .character(filteredCharacter)
                .visibilityMap(visibilityMap)
                .build();
    }
}