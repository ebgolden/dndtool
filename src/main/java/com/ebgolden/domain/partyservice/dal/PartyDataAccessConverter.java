package com.ebgolden.domain.partyservice.dal;

import com.ebgolden.domain.partyservice.bll.bo.*;
import com.ebgolden.domain.partyservice.dal.dao.*;

public interface PartyDataAccessConverter {
    PartyAndCharacterDao getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo);

    PartyAndCharacterDao getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerAndAcceptedByPartyBo(PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo);

    PartyAndSplitPartiesDao getPartyAndSplitPartiesDaoFromPartyAndSplitPartiesAndDungeonMasterBo(PartyAndSplitPartiesAndDungeonMasterBo partyAndSplitPartiesAndDungeonMasterBo);

    PartiesDao getPartiesDaoFromPartiesAndDungeonMasterBo(PartiesAndDungeonMasterBo partiesAndDungeonMasterBo);

    PartyBo getPartyBoFromPartyDao(PartyDao partyDao);

    SplitPartiesBo getSplitPartiesBoFromSplitPartiesDao(SplitPartiesDao splitPartiesDao);

    PartyDao getPartyDaoFromPartyJson(String partyJson);

    SplitPartiesDao getSplitPartiesDaoFromSplitPartiesJson(String splitPartiesJson);
}