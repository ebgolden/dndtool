package domain.partyservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Character;
import common.Party;
import domain.partyservice.bll.bo.*;
import domain.partyservice.dal.dao.*;

public class PartyDataAccessConverterImpl implements PartyDataAccessConverter {
    public PartyAndCharacterDao getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo) {
        Party party = partyAndCharacterAndPlayerBo.getParty();
        Character character = partyAndCharacterAndPlayerBo.getCharacter();
        return getPartyAndCharacterDaoFromPartyAndCharacter(party, character);
    }

    public PartyAndCharacterDao getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerAndAcceptedByPartyBo(PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo) {
        Party party = partyAndCharacterAndPlayerAndAcceptedByPartyBo.getParty();
        Character character = partyAndCharacterAndPlayerAndAcceptedByPartyBo.getCharacter();
        return getPartyAndCharacterDaoFromPartyAndCharacter(party, character);
    }

    public PartyAndSplitPartiesDao getPartyAndSplitPartiesDaoFromPartyAndSplitPartiesAndDungeonMasterBo(PartyAndSplitPartiesAndDungeonMasterBo partyAndSplitPartiesAndDungeonMasterBo) {
        Party party = partyAndSplitPartiesAndDungeonMasterBo.getParty();
        Party[] splitParties = partyAndSplitPartiesAndDungeonMasterBo.getSplitParties();
        ObjectMapper objectMapper = new ObjectMapper();
        String partyJson = "{}";
        String splitPartiesJson = "{}";
        try {
            partyJson = objectMapper.writeValueAsString(party);
            splitPartiesJson = objectMapper.writeValueAsString(splitParties);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String partyAndSplitPartiesJson = "{" +
                "party:" +
                partyJson +
                ",splitParties:" +
                splitPartiesJson +
                "}";
        return PartyAndSplitPartiesDao
                .builder()
                .partyAndSplitPartiesJson(partyAndSplitPartiesJson)
                .build();
    }

    public PartiesDao getPartiesDaoFromPartiesAndDungeonMasterBo(PartiesAndDungeonMasterBo partiesAndDungeonMasterBo) {
        Party[] parties = partiesAndDungeonMasterBo.getParties();
        ObjectMapper objectMapper = new ObjectMapper();
        String partiesJson = "{}";
        try {
            partiesJson = objectMapper.writeValueAsString(parties);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return PartiesDao
                .builder()
                .partiesJson(partiesJson)
                .build();
    }

    private PartyAndCharacterDao getPartyAndCharacterDaoFromPartyAndCharacter(Party party, Character character) {
        ObjectMapper objectMapper = new ObjectMapper();
        String partyJson = "{}";
        String characterJson = "{}";
        try {
            partyJson = objectMapper.writeValueAsString(party);
            characterJson = objectMapper.writeValueAsString(character);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String partyAndCharacterJson = "{" +
                "party:" +
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

    public SplitPartiesBo getSplitPartiesBoFromSplitPartiesDao(SplitPartiesDao splitPartiesDao) {
        String splitPartiesJson = splitPartiesDao.getSplitPartiesJson();
        if (splitPartiesJson == null)
            splitPartiesJson = "{}";
        ObjectMapper objectMapper = new ObjectMapper();
        Party[] splitParties;
        try {
            splitParties = objectMapper.readValue(splitPartiesJson, Party[].class);
        } catch (JsonProcessingException e) {
            splitParties = new Party[] {};
        }
        return SplitPartiesBo
                .builder()
                .splitParties(splitParties)
                .build();
    }

    public PartyDao getPartyDaoFromPartyJson(String partyJson) {
        return PartyDao
                .builder()
                .partyJson(partyJson)
                .build();
    }

    public SplitPartiesDao getSplitPartiesDaoFromSplitPartiesJson(String splitPartiesJson) {
        return SplitPartiesDao
                .builder()
                .splitPartiesJson(splitPartiesJson)
                .build();
    }
}