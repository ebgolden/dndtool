package domain.characterservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.*;
import common.Character;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import domain.characterservice.bll.bo.*;
import domain.characterservice.dal.dao.*;
import java.util.HashMap;
import java.util.Map;

public class CharacterDataAccessConverterImpl implements CharacterDataAccessConverter {
    public CharacterAndVisibilityAndPlayerDao getCharacterAndVisibilityAndPlayerDaoFromCharacterAndVisibilityAndPlayerBo(CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo) {
        Character character = characterAndVisibilityAndPlayerBo.getCharacter();
        Map<String, Visibility> visibilityMap = characterAndVisibilityAndPlayerBo.getVisibilityMap();
        Player player = characterAndVisibilityAndPlayerBo.getPlayer();
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson = "{}";
        String visibilityJson = "{}";
        String playerJson = "{}";
        try {
            characterJson = objectMapper.writeValueAsString(character);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
            playerJson = objectMapper.writeValueAsString(player);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String characterAndVisibilityAndPlayerJson = "{}";
        if ((!characterJson.equals("{}") && (!characterJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))) || (!playerJson.equals("{}") && (!playerJson.equals("null"))))
            characterAndVisibilityAndPlayerJson = "{" +
                    "character:" +
                    characterJson +
                    ",visibility:" +
                    visibilityJson +
                    ",player:" +
                    playerJson +
                    "}";
        return CharacterAndVisibilityAndPlayerDao
                .builder()
                .characterAndVisibilityAndPlayerJson(characterAndVisibilityAndPlayerJson)
                .build();
    }

    public NonPlayableCharacterAndVisibilityAndDungeonMasterDao getNonPlayableCharacterAndVisibilityAndDungeonMasterDaoFromNonPlayableCharacterAndVisibilityAndDungeonMasterBo(NonPlayableCharacterAndVisibilityAndDungeonMasterBo nonPlayableCharacterAndVisibilityAndDungeonMasterBo) {
        NonPlayableCharacter nonPlayableCharacter = nonPlayableCharacterAndVisibilityAndDungeonMasterBo.getNonPlayableCharacter();
        Map<String, Visibility> visibilityMap = nonPlayableCharacterAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        DungeonMaster dungeonMaster = nonPlayableCharacterAndVisibilityAndDungeonMasterBo.getDungeonMaster();
        ObjectMapper objectMapper = new ObjectMapper();
        String nonPlayableCharacterJson = "{}";
        String visibilityJson = "{}";
        String dungeonMasterJson = "{}";
        try {
            nonPlayableCharacterJson = objectMapper.writeValueAsString(nonPlayableCharacter);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
            dungeonMasterJson = objectMapper.writeValueAsString(dungeonMaster);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String nonPlayableCharacterAndVisibilityAndDungeonMasterJson = "{}";
        if ((!nonPlayableCharacterJson.equals("{}") && (!nonPlayableCharacterJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))) || (!dungeonMasterJson.equals("{}") && (!dungeonMasterJson.equals("null"))))
            nonPlayableCharacterAndVisibilityAndDungeonMasterJson = "{" +
                    "nonPlayableCharacter:" +
                    nonPlayableCharacterJson +
                    ",visibility:" +
                    visibilityJson +
                    ",dungeonMaster:" +
                    dungeonMasterJson +
                    "}";
        return NonPlayableCharacterAndVisibilityAndDungeonMasterDao
                .builder()
                .nonPlayableCharacterAndVisibilityAndDungeonMasterJson(nonPlayableCharacterAndVisibilityAndDungeonMasterJson)
                .build();
    }

    public CharacterDao getCharacterDaoFromCharacterAndDungeonMasterBo(CharacterAndDungeonMasterBo characterAndDungeonMasterBo) {
        Character character = characterAndDungeonMasterBo.getCharacter();
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson = "{}";
        try {
            characterJson = objectMapper.writeValueAsString(character);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return CharacterDao
                .builder()
                .characterJson(characterJson)
                .build();
    }

    public NonPlayableCharacterDao getNonPlayableCharacterDaoFromNonPlayableCharacterAndDungeonMasterBo(NonPlayableCharacterAndDungeonMasterBo nonPlayableCharacterAndDungeonMasterBo) {
        NonPlayableCharacter nonPlayableCharacter = nonPlayableCharacterAndDungeonMasterBo.getNonPlayableCharacter();
        ObjectMapper objectMapper = new ObjectMapper();
        String nonPlayableCharacterJson = "{}";
        try {
            nonPlayableCharacterJson = objectMapper.writeValueAsString(nonPlayableCharacter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return NonPlayableCharacterDao
                .builder()
                .nonPlayableCharacterJson(nonPlayableCharacterJson)
                .build();
    }

    public CharacterDao getCharacterDaoFromCharacterAndPlayerBo(CharacterAndPlayerBo characterAndPlayerBo) {
        Character character = characterAndPlayerBo.getCharacter();
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson = "{}";
        try {
            characterJson = objectMapper.writeValueAsString(character);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return CharacterDao
                .builder()
                .characterJson(characterJson)
                .build();
    }

    public CharacterAndVisibilityDao getCharacterAndVisibilityDaoFromCharacterAndVisibilityBo(CharacterAndVisibilityBo characterAndVisibilityBo) {
        Character character = characterAndVisibilityBo.getCharacter();
        Map<String, Visibility> visibilityMap = characterAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson = "{}";
        String visibilityJson = "{}";
        try {
            characterJson = objectMapper.writeValueAsString(character);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String characterAndVisibilityJson = "{}";
        if ((!characterJson.equals("{}") && (!characterJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            characterAndVisibilityJson = "{" +
                    "character:" +
                    characterJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return CharacterAndVisibilityDao
                .builder()
                .characterAndVisibilityJson(characterAndVisibilityJson)
                .build();
    }

    public CharacterBo getCharacterBoFromCharacterDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        if (characterJson == null)
            characterJson = "{}";
        Character character = null;
        if (!characterJson.equals("{}")) {
            ObjectMapper objectMapper = new ObjectMapper();
            character = Character
                    .builder()
                    .build();
            try {
                character = objectMapper.readValue(characterJson, Character.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return CharacterBo
                .builder()
                .character(character)
                .build();
    }

    public NonPlayableCharacterBo getNonPlayableCharacterBoFromNonPlayableCharacterDao(NonPlayableCharacterDao nonPlayableCharacterDao) {
        String nonPlayableCharacterJson = nonPlayableCharacterDao.getNonPlayableCharacterJson();
        if (nonPlayableCharacterJson == null)
            nonPlayableCharacterJson = "{}";
        NonPlayableCharacter nonPlayableCharacter = null;
        if (!nonPlayableCharacterJson.equals("{}")) {
            ObjectMapper objectMapper = new ObjectMapper();
            nonPlayableCharacter = NonPlayableCharacter
                    .builder()
                    .build();
            try {
                nonPlayableCharacter = objectMapper.readValue(nonPlayableCharacterJson, NonPlayableCharacter.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return NonPlayableCharacterBo
                .builder()
                .nonPlayableCharacter(nonPlayableCharacter)
                .build();
    }

    public CharacterAndVisibilityBo getCharacterAndVisibilityBoFromCharacterAndVisibilityDao(CharacterAndVisibilityDao characterAndVisibilityDao) {
        String characterAndVisibilityJson = characterAndVisibilityDao.getCharacterAndVisibilityJson();
        if (characterAndVisibilityJson == null)
            characterAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(characterAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson;
        Character character = null;
        if (jsonObject.get("character") != null) {
            characterJson = ((JSONObject) jsonObject.get("character")).toJSONString();
            character = Character
                    .builder()
                    .build();
            try {
                character = objectMapper.readValue(characterJson, Character.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        String visibilityJson;
        Map<String, Visibility> visibilityMap = null;
        if (jsonObject.get("visibility") != null) {
            visibilityJson = ((JSONObject)jsonObject.get("visibility")).toJSONString();
            visibilityMap = new HashMap<>();
            try {
                TypeReference<Map<String, Visibility>> visibilityMapTypeReference = new TypeReference<Map<String, Visibility>>(){};
                visibilityMap = objectMapper.readValue(visibilityJson, visibilityMapTypeReference);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return CharacterAndVisibilityBo
                .builder()
                .character(character)
                .visibilityMap(visibilityMap)
                .build();
    }

    public CharacterDao getCharacterDaoFromCharacterJson(String characterJson) {
        return CharacterDao
                .builder()
                .characterJson(characterJson)
                .build();
    }

    public NonPlayableCharacterDao getNonPlayableCharacterDaoFromNonPlayableCharacterJson(String nonPlayableCharacterJson) {
        return NonPlayableCharacterDao
                .builder()
                .nonPlayableCharacterJson(nonPlayableCharacterJson)
                .build();
    }

    public CharacterAndVisibilityDao getCharacterAndVisibilityDaoFromCharacterAndVisibilityJson(String characterAndVisibilityJson) {
        return CharacterAndVisibilityDao
                .builder()
                .characterAndVisibilityJson(characterAndVisibilityJson)
                .build();
    }
}