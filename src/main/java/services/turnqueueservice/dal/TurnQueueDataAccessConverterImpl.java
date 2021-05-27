package services.turnqueueservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Character;
import objects.Encounter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.turnqueueservice.bll.bo.EncounterBo;
import services.turnqueueservice.bll.bo.TurnQueueBo;
import services.turnqueueservice.dal.dao.EncounterDao;
import services.turnqueueservice.dal.dao.TurnQueueDao;

public class TurnQueueDataAccessConverterImpl implements TurnQueueDataAccessConverter {
    public EncounterDao getEncounterDaoFromEncounterBo(EncounterBo encounterBo) {
        Encounter encounter = encounterBo.getEncounter();
        ObjectMapper objectMapper = new ObjectMapper();
        String encounterJson = "{}";
        try {
            encounterJson = objectMapper.writeValueAsString(encounter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return EncounterDao
                .builder()
                .encounterJson(encounterJson)
                .build();
    }

    public TurnQueueBo getTurnQueueBoFromTurnQueueDao(TurnQueueDao turnQueueDao) {
        String turnQueueJson = turnQueueDao.getTurnQueueJson();
        if (turnQueueJson == null)
            turnQueueJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(turnQueueJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = (JSONArray)jsonObject.get("characters");
        String charactersJson = "{}";
        if (jsonArray != null)
            charactersJson = jsonArray.toJSONString();
        ObjectMapper objectMapper = new ObjectMapper();
        Character[] characters;
        try {
            characters = objectMapper.readValue(charactersJson, Character[].class);
        } catch (JsonProcessingException e) {
            characters = new Character[] {};
        }
        int currentTurnIndex = 0;
        if (jsonObject.get("currentTurnIndex") != null)
            currentTurnIndex = Integer.parseInt(jsonObject.get("currentTurnIndex").toString());
        return TurnQueueBo
                .builder()
                .characters(characters)
                .currentTurnIndex(currentTurnIndex)
                .build();
    }

    public TurnQueueDao getTurnQueueDaoFromTurnQueueJson(String turnQueueJson) {
        return TurnQueueDao
                .builder()
                .turnQueueJson(turnQueueJson)
                .build();
    }
}