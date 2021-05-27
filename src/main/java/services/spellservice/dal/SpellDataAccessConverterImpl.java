package services.spellservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonobjects.Spell;
import commonobjects.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.spellservice.bll.bo.SpellAndPlayerBo;
import services.spellservice.bll.bo.SpellAndVisibilityBo;
import services.spellservice.dal.dao.SpellDao;
import services.spellservice.dal.dao.SpellAndVisibilityDao;
import java.util.HashMap;
import java.util.Map;

public class SpellDataAccessConverterImpl implements SpellDataAccessConverter {
    public SpellDao getSpellDaoFromSpellAndPlayerBo(SpellAndPlayerBo spellAndPlayerBo) {
        Spell spell = spellAndPlayerBo.getSpell();
        ObjectMapper objectMapper = new ObjectMapper();
        String spellJson = "{}";
        try {
            spellJson = objectMapper.writeValueAsString(spell);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return SpellDao
                .builder()
                .spellJson(spellJson)
                .build();
    }

    public SpellAndVisibilityDao getSpellAndVisibilityDaoFromSpellAndVisibilityBo(SpellAndVisibilityBo spellAndVisibilityBo) {
        Spell spell = spellAndVisibilityBo.getSpell();
        Map<String, Visibility> visibilityMap = spellAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String spellJson = "{}";
        String visibilityJson = "{}";
        try {
            spellJson = objectMapper.writeValueAsString(spell);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String spellAndVisibilityJson = "{}";
        if ((!spellJson.equals("{}") && (!spellJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            spellAndVisibilityJson = "{" +
                    "spell:" +
                    spellJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return SpellAndVisibilityDao
                .builder()
                .spellAndVisibilityJson(spellAndVisibilityJson)
                .build();
    }

    public SpellAndVisibilityBo getSpellAndVisibilityBoFromSpellAndVisibilityDao(SpellAndVisibilityDao spellAndVisibilityDao) {
        String spellAndVisibilityJson = spellAndVisibilityDao.getSpellAndVisibilityJson();
        if (spellAndVisibilityJson == null)
            spellAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(spellAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String spellJson;
        Spell spell = null;
        if (jsonObject.get("spell") != null) {
            spellJson = ((JSONObject) jsonObject.get("spell")).toJSONString();
            spell = Spell
                    .builder()
                    .build();
            try {
                spell = objectMapper.readValue(spellJson, Spell.class);
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
        return SpellAndVisibilityBo
                .builder()
                .spell(spell)
                .visibilityMap(visibilityMap)
                .build();
    }

    public SpellAndVisibilityDao getSpellAndVisibilityDaoFromSpellAndVisibilityJson(String spellAndVisibilityJson) {
        return SpellAndVisibilityDao
                .builder()
                .spellAndVisibilityJson(spellAndVisibilityJson)
                .build();
    }
}