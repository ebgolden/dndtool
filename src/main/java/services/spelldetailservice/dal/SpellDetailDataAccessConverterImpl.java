package services.spelldetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Spell;
import objects.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.spelldetailservice.bll.bo.SpellAndPlayerBo;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityBo;
import services.spelldetailservice.dal.dao.SpellDao;
import services.spelldetailservice.dal.dao.SpellDetailsAndVisibilityDao;
import java.util.HashMap;
import java.util.Map;

public class SpellDetailDataAccessConverterImpl implements SpellDetailDataAccessConverter {
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

    public SpellDetailsAndVisibilityDao getSpellDetailsAndVisibilityDaoFromSpellDetailsAndVisibilityBo(SpellDetailsAndVisibilityBo spellDetailsAndVisibilityBo) {
        Spell spell = spellDetailsAndVisibilityBo.getSpell();
        Map<String, Visibility> visibilityMap = spellDetailsAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String spellJson = "{}";
        String visibilityJson = "{}";
        try {
            spellJson = objectMapper.writeValueAsString(spell);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String spellDetailsAndVisibilityJson = "{}";
        if ((!spellJson.equals("{}") && (!spellJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            spellDetailsAndVisibilityJson = "{" +
                    "spellDetails:" +
                    spellJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return SpellDetailsAndVisibilityDao
                .builder()
                .spellDetailsAndVisibilityJson(spellDetailsAndVisibilityJson)
                .build();
    }

    public SpellDetailsAndVisibilityBo getSpellDetailsAndVisibilityBoFromSpellDetailsAndVisibilityDao(SpellDetailsAndVisibilityDao spellDetailsAndVisibilityDao) {
        String spellDetailsAndVisibilityJson = spellDetailsAndVisibilityDao.getSpellDetailsAndVisibilityJson();
        if (spellDetailsAndVisibilityJson == null)
            spellDetailsAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(spellDetailsAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String spellDetailsJson;
        Spell spell = null;
        if (jsonObject.get("spellDetails") != null) {
            spellDetailsJson = ((JSONObject) jsonObject.get("spellDetails")).toJSONString();
            spell = Spell
                    .builder()
                    .build();
            try {
                spell = objectMapper.readValue(spellDetailsJson, Spell.class);
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
                visibilityMap = objectMapper.readValue(visibilityJson, Map.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return SpellDetailsAndVisibilityBo
                .builder()
                .spell(spell)
                .visibilityMap(visibilityMap)
                .build();
    }

    public SpellDetailsAndVisibilityDao getSpellDetailsAndVisibilityDaoFromSpellDetailsAndVisibilityJson(String spellDetailsAndVisibilityJson) {
        return SpellDetailsAndVisibilityDao
                .builder()
                .spellDetailsAndVisibilityJson(spellDetailsAndVisibilityJson)
                .build();
    }
}