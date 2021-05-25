package services.characterservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Character;
import objects.Player;
import objects.Visibility;
import services.characterservice.bll.bo.CharacterAndVisibilityAndPlayerBo;
import services.characterservice.bll.bo.CharacterBo;
import services.characterservice.dal.dao.CharacterAndVisibilityAndPlayerDao;
import services.characterservice.dal.dao.CharacterDao;
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

    public CharacterDao getCharacterDaoFromCharacterJsonObject(String characterJsonObject) {
        return CharacterDao
                .builder()
                .characterJson(characterJsonObject)
                .build();
    }
}