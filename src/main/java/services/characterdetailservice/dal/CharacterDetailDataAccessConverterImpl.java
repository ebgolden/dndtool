package services.characterdetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.CharacterObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.characterdetailservice.bll.bo.CharacterAndPlayerBo;
import services.characterdetailservice.bll.bo.CharacterDetailsAndVisibilityBo;
import services.characterdetailservice.dal.dao.CharacterDao;
import services.characterdetailservice.dal.dao.CharacterDetailsAndVisibilityDao;

public class CharacterDetailDataAccessConverterImpl implements CharacterDetailDataAccessConverter {
    public CharacterDao getCharacterDaoFromCharacterAndPlayerBo(CharacterAndPlayerBo characterAndPlayerBo) {
        CharacterObject character = characterAndPlayerBo.getCharacter();
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

    public CharacterDetailsAndVisibilityBo getCharacterDetailsAndVisibilityBoFromCharacterDetailsDao(CharacterDetailsAndVisibilityDao characterDetailsAndVisibilityDao) {
        String characterDetailsAndVisibilityJson = characterDetailsAndVisibilityDao.getCharacterDetailsAndVisibilityJson();
        if (characterDetailsAndVisibilityJson == null)
            characterDetailsAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(characterDetailsAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String characterDetailsJson;
        CharacterObject character = null;
        if (jsonObject.get("characterDetails") != null) {
            characterDetailsJson = ((JSONObject) jsonObject.get("characterDetails")).toJSONString();
            ObjectMapper objectMapper = new ObjectMapper();
            character = CharacterObject
                    .builder()
                    .build();
            try {
                character = objectMapper.readValue(characterDetailsJson, CharacterObject.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        String visibilityJson = "{}";
        if (jsonObject.get("visibility") != null)
            visibilityJson = ((JSONObject)jsonObject.get("visibility")).toJSONString();
        return CharacterDetailsAndVisibilityBo
                .builder()
                .character(character)
                .visibilityJson(visibilityJson)
                .build();
    }

    public CharacterDetailsAndVisibilityDao getCharacterDetailsAndVisibilityDaoFromLatestJsonObject(String latestJsonObject) {
        return CharacterDetailsAndVisibilityDao
                .builder()
                .characterDetailsAndVisibilityJson(latestJsonObject)
                .build();
    }
}