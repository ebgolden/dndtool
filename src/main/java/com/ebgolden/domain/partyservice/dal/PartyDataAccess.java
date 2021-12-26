package domain.partyservice.dal;

import domain.partyservice.dal.dao.*;

public interface PartyDataAccess {
    PartyDao getPartyDao(PartyAndCharacterDao partyAndCharacterDao);

    SplitPartiesDao getSplitPartiesDao(PartyAndSplitPartiesDao partyAndSplitPartiesDao);

    PartyDao getPartyDao(PartiesDao partiesDao);
}