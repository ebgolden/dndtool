package services.spelldetailservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import objects.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.spelldetailservice.bll.bo.SpellAndPlayerBo;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityAndPlayerBo;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityBo;
import services.spelldetailservice.dal.SpellDetailDataAccess;
import services.spelldetailservice.dal.SpellDetailDataAccessConverter;
import services.spelldetailservice.dal.dao.SpellDao;
import services.spelldetailservice.dal.dao.SpellDetailsAndVisibilityDao;
import java.util.Map;

public class SpellDetailBusinessLogicImpl implements SpellDetailBusinessLogic {
    @Inject
    private SpellDetailDataAccessConverter spellDetailDataAccessConverter;
    @Inject
    private SpellDetailDataAccess spellDetailDataAccess;
    @Inject
    private SpellDetailBusinessLogicConverter spellDetailBusinessLogicConverter;

    public SpellDetailsAndVisibilityBo getSpellDetailsAndVisibilityBo(SpellAndPlayerBo spellAndPlayerBo) {
        SpellDao spellDao = spellDetailDataAccessConverter.getSpellDaoFromSpellAndPlayerBo(spellAndPlayerBo);
        SpellDetailsAndVisibilityDao spellDetailsAndVisibilityDao = spellDetailDataAccess.getSpellDetailsAndVisibilityDao(spellDao);
        SpellDetailsAndVisibilityBo spellDetailsAndVisibilityBo = spellDetailDataAccessConverter.getSpellDetailsAndVisibilityBoFromSpellDetailsAndVisibilityDao(spellDetailsAndVisibilityDao);
        Player player = spellAndPlayerBo.getPlayer();
        if (spellDetailsAndVisibilityBo.getSpell() == null)
            return spellDetailsAndVisibilityBo;
        return filterSpellDetailsAndVisibilityBo(spellDetailsAndVisibilityBo, player);
    }

    public SpellDetailsAndVisibilityBo getSpellDetailsAndVisibilityBo(SpellDetailsAndVisibilityAndPlayerBo spellDetailsAndVisibilityAndPlayerBo) {
        SpellDetailsAndVisibilityAndPlayerBo filteredSpellDetailsAndVisibilityAndPlayerBo = filterSpellDetailsAndVisibilityAndPlayerBo(spellDetailsAndVisibilityAndPlayerBo);
        SpellDetailsAndVisibilityBo spellDetailsAndVisibilityBo = spellDetailBusinessLogicConverter.getSpellDetailsAndVisibilityBoFromSpellDetailsAndVisibilityAndPlayerBo(filteredSpellDetailsAndVisibilityAndPlayerBo);
        SpellDetailsAndVisibilityDao spellDetailsAndVisibilityDao = spellDetailDataAccessConverter.getSpellDetailsAndVisibilityDaoFromSpellDetailsAndVisibilityBo(spellDetailsAndVisibilityBo);
        spellDetailsAndVisibilityDao = spellDetailDataAccess.getSpellDetailsAndVisibilityDao(spellDetailsAndVisibilityDao);
        return spellDetailDataAccessConverter.getSpellDetailsAndVisibilityBoFromSpellDetailsAndVisibilityDao(spellDetailsAndVisibilityDao);
    }

    private SpellDetailsAndVisibilityBo filterSpellDetailsAndVisibilityBo(SpellDetailsAndVisibilityBo spellDetailsAndVisibilityBo, Player player) {
        Spell spell = spellDetailsAndVisibilityBo.getSpell();
        Map<String, Visibility> visibilityMap = spellDetailsAndVisibilityBo.getVisibilityMap();
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
        return SpellDetailsAndVisibilityBo
                .builder()
                .spell(filteredSpell)
                .visibilityMap(visibilityMap)
                .build();
    }

    private SpellDetailsAndVisibilityAndPlayerBo filterSpellDetailsAndVisibilityAndPlayerBo(SpellDetailsAndVisibilityAndPlayerBo spellDetailsAndVisibilityAndPlayerBo) {
        Spell spell = spellDetailsAndVisibilityAndPlayerBo.getSpell();
        Map<String, Visibility> visibilityMap = spellDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        Player player = spellDetailsAndVisibilityAndPlayerBo.getPlayer();
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
        return SpellDetailsAndVisibilityAndPlayerBo
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