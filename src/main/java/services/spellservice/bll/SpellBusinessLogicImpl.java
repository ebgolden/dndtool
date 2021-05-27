package services.spellservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import commonobjects.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.spellservice.bll.bo.SpellAndPlayerBo;
import services.spellservice.bll.bo.SpellAndVisibilityAndPlayerBo;
import services.spellservice.bll.bo.SpellAndVisibilityBo;
import services.spellservice.dal.SpellDataAccess;
import services.spellservice.dal.SpellDataAccessConverter;
import services.spellservice.dal.dao.SpellDao;
import services.spellservice.dal.dao.SpellAndVisibilityDao;
import java.util.Map;

public class SpellBusinessLogicImpl implements SpellBusinessLogic {
    @Inject
    private SpellDataAccessConverter spellDataAccessConverter;
    @Inject
    private SpellDataAccess spellDataAccess;
    @Inject
    private SpellBusinessLogicConverter spellBusinessLogicConverter;

    public SpellAndVisibilityBo getSpellAndVisibilityBo(SpellAndPlayerBo spellAndPlayerBo) {
        SpellDao spellDao = spellDataAccessConverter.getSpellDaoFromSpellAndPlayerBo(spellAndPlayerBo);
        SpellAndVisibilityDao spellAndVisibilityDao = spellDataAccess.getSpellAndVisibilityDao(spellDao);
        SpellAndVisibilityBo spellAndVisibilityBo = spellDataAccessConverter.getSpellAndVisibilityBoFromSpellAndVisibilityDao(spellAndVisibilityDao);
        Player player = spellAndPlayerBo.getPlayer();
        if (spellAndVisibilityBo.getSpell() == null)
            return spellAndVisibilityBo;
        return filterSpellAndVisibilityBo(spellAndVisibilityBo, player);
    }

    public SpellAndVisibilityBo getSpellAndVisibilityBo(SpellAndVisibilityAndPlayerBo spellAndVisibilityAndPlayerBo) {
        SpellAndVisibilityAndPlayerBo filteredSpellAndVisibilityAndPlayerBo = filterSpellAndVisibilityAndPlayerBo(spellAndVisibilityAndPlayerBo);
        SpellAndVisibilityBo spellAndVisibilityBo = spellBusinessLogicConverter.getSpellAndVisibilityBoFromSpellAndVisibilityAndPlayerBo(filteredSpellAndVisibilityAndPlayerBo);
        SpellAndVisibilityDao spellAndVisibilityDao = spellDataAccessConverter.getSpellAndVisibilityDaoFromSpellAndVisibilityBo(spellAndVisibilityBo);
        spellAndVisibilityDao = spellDataAccess.getSpellAndVisibilityDao(spellAndVisibilityDao);
        return spellDataAccessConverter.getSpellAndVisibilityBoFromSpellAndVisibilityDao(spellAndVisibilityDao);
    }

    private SpellAndVisibilityBo filterSpellAndVisibilityBo(SpellAndVisibilityBo spellAndVisibilityBo, Player player) {
        Spell spell = spellAndVisibilityBo.getSpell();
        Map<String, Visibility> visibilityMap = spellAndVisibilityBo.getVisibilityMap();
        String playerId = player.getId();
        String spellPlayerId = spell.getPlayerId();
        Spell filteredSpell = spell;
        if (player.getClass() != DungeonMaster.class) {
            ObjectMapper objectMapper = new ObjectMapper();
            String spellJson = "{}";
            String visibilityJson = "{}";
            try {
                spellJson = objectMapper.writeValueAsString(spell);
                visibilityJson = objectMapper.writeValueAsString(visibilityMap);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject spellJsonObject = new JSONObject();
            JSONObject visibilityJsonObject = new JSONObject();
            try {
                spellJsonObject = (JSONObject)jsonParser.parse(spellJson);
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
                if (spellJsonObject.containsKey(visibilityKey) &&
                        (((!playerId.equals(spellPlayerId)) && (visibility != Visibility.EVERYONE)) ||
                                ((playerId.equals(spellPlayerId)) && (visibility == Visibility.DUNGEON_MASTER))))
                    spellJsonObject.remove(visibilityKey);
            }
            JSONObject filteredSpellJsonObject = spellJsonObject;
            String filteredSpellJson = filteredSpellJsonObject.toJSONString();
            try {
                filteredSpell = objectMapper.readValue(filteredSpellJson, Spell.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return SpellAndVisibilityBo
                .builder()
                .spell(filteredSpell)
                .visibilityMap(visibilityMap)
                .build();
    }

    private SpellAndVisibilityAndPlayerBo filterSpellAndVisibilityAndPlayerBo(SpellAndVisibilityAndPlayerBo spellAndVisibilityAndPlayerBo) {
        Spell spell = spellAndVisibilityAndPlayerBo.getSpell();
        Map<String, Visibility> visibilityMap = spellAndVisibilityAndPlayerBo.getVisibilityMap();
        Player player = spellAndVisibilityAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String spellPlayerId = spell.getPlayerId();
        if (!playerId.equals(spellPlayerId) && (player.getClass() != DungeonMaster.class)) {
            spell = null;
            visibilityMap = null;
        }
        Map<String, Visibility> filteredVisibilityMap = visibilityMap;
        if (filteredVisibilityMap != null) {
            Spell finalSpell = spell;
            visibilityMap.keySet().forEach(key -> correctVisibility(player, spellPlayerId, filteredVisibilityMap, key, finalSpell));
        }
        return SpellAndVisibilityAndPlayerBo
                .builder()
                .spell(spell)
                .visibilityMap(filteredVisibilityMap)
                .player(player)
                .build();
    }

    private void correctVisibility(Player player, String spellPlayerId, Map<String, Visibility> visibilityMap, String key, Spell spell) {
        String playerId = player.getId();
        ObjectMapper objectMapper = new ObjectMapper();
        String spellJson = "{}";
        try {
            spellJson = objectMapper.writeValueAsString(spell);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject spellJsonObject = new JSONObject();
        try {
            spellJsonObject = (JSONObject)jsonParser.parse(spellJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!spellJsonObject.containsKey(key))
            visibilityMap.remove(key);
        else if ((player.getClass() != DungeonMaster.class) && (visibilityMap.get(key) == Visibility.DUNGEON_MASTER))
            visibilityMap.replace(key, Visibility.PLAYER);
        else if (playerId.equals(spellPlayerId) && (player.getClass() == DungeonMaster.class) && (visibilityMap.get(key) == Visibility.PLAYER))
            visibilityMap.replace(key, Visibility.DUNGEON_MASTER);
    }
}