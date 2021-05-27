package services.partyservice.dal;

import services.partyservice.dal.dao.PartyAndCharacterDao;
import services.partyservice.dal.dao.PartyAndSplitPartiesDao;
import services.partyservice.dal.dao.PartyDao;
import services.partyservice.dal.dao.SplitPartiesDao;

public interface PartyDataAccess {
    PartyDao getPartyDao(PartyAndCharacterDao partyAndCharacterDao);

    SplitPartiesDao getSplitPartiesDao(PartyAndSplitPartiesDao partyAndSplitPartiesDao);
}