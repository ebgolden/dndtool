package services.partyservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Character;
import objects.Party;
import services.partyservice.bll.bo.PartyBo;
import services.partyservice.bll.bo.PartyAndCharacterAndPlayerBo;
import services.partyservice.dal.dao.PartyAndCharacterDao;
import services.partyservice.dal.dao.PartyDao;

public class PartyDataAccessConverterImpl implements PartyDataAccessConverter {
    public PartyAndCharacterDao getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo) {
        Party party = partyAndCharacterAndPlayerBo.getParty();
        Character character = partyAndCharacterAndPlayerBo.getCharacter();
        ObjectMapper objectMapper = new ObjectMapper();
        String partyJson = "{}";
        String characterJson = "{}";
        try {
            partyJson = objectMapper.writeValueAsString(party);
            characterJson = objectMapper.writeValueAsString(character);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String partyAndCharacterJson = "{party:" +
                partyJson +
                ",character:" +
                characterJson +
                "}";
        return PartyAndCharacterDao
                .builder()
                .partyAndCharacterJson(partyAndCharacterJson)
                .build();
    }

    public PartyBo getPartyBoFromPartyDao(PartyDao partyDao) {
        String partyJson = partyDao.getPartyJson();
        if (partyJson == null)
            partyJson = "{}";
        Party party = null;
        if (!partyJson.equals("{}")) {
            ObjectMapper objectMapper = new ObjectMapper();
            party = Party
                    .builder()
                    .build();
            try {
                party = objectMapper.readValue(partyJson, Party.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return PartyBo
                .builder()
                .party(party)
                .build();
    }

    public PartyDao getPartyDaoFromUpdatedJsonObject(String updatedJsonObject) {
        return PartyDao
                .builder()
                .partyJson(updatedJsonObject)
                .build();
    }
}