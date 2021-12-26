package com.ebgolden.domain.partyservice.dal;

import com.ebgolden.domain.partyservice.dal.dao.*;

public interface PartyDataAccess {
    PartyDao getPartyDao(PartyAndCharacterDao partyAndCharacterDao);

    SplitPartiesDao getSplitPartiesDao(PartyAndSplitPartiesDao partyAndSplitPartiesDao);

    PartyDao getPartyDao(PartiesDao partiesDao);
}