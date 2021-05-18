package services.partyservice.dal;

import services.partyservice.dal.dao.PartyAndCharacterDao;
import services.partyservice.dal.dao.PartyDao;

public interface PartyDataAccess {
    PartyDao getPartyDao(PartyAndCharacterDao partyAndCharacterDao);
}