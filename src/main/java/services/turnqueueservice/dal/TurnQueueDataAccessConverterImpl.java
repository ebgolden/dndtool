package services.turnqueueservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.CharacterObject;
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
        int numberOfCharacters = 0;
        if (jsonArray != null)
            numberOfCharacters = jsonArray.size();
        CharacterObject[] characters = new CharacterObject[numberOfCharacters];
        ObjectMapper objectMapper = new ObjectMapper();
        for (int characterIndex = 0; characterIndex < numberOfCharacters; ++characterIndex) {
            String characterJson = ((JSONObject)jsonArray.get(characterIndex)).toJSONString();
            try {
                characters[characterIndex] = objectMapper.readValue(characterJson, CharacterObject.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
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

    @Override
    public TurnQueueDao getTurnQueueDaoFromLatestObjectJson(String latestObjectJson) {
        return TurnQueueDao
                .builder()
                .turnQueueJson(latestObjectJson)
                .build();
    }
}