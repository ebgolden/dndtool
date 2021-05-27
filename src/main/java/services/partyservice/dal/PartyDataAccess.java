package services.partyservice.dal;

import services.partyservice.dal.dao.*;

public interface PartyDataAccess {
    PartyDao getPartyDao(PartyAndCharacterDao partyAndCharacterDao);

    SplitPartiesDao getSplitPartiesDao(PartyAndSplitPartiesDao partyAndSplitPartiesDao);

    PartyDao getPartyDao(PartiesDao partiesDao);
}