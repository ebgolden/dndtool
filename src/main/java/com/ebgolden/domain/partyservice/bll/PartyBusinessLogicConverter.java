package com.ebgolden.domain.partyservice.bll;

import com.ebgolden.domain.partyservice.*;
import com.ebgolden.domain.partyservice.bll.bo.*;

public interface PartyBusinessLogicConverter {
    PartyAndCharacterAndPlayerBo getPartyAndCharacterAndPlayerBoFromLeavePartyRequest(LeavePartyRequest leavePartyRequest);

    PartyAndCharacterAndPlayerAndAcceptedByPartyBo getPartyAndCharacterAndPlayerAndAcceptedByPartyBoFromJoinPartyRequest(JoinPartyRequest joinPartyRequest);

    PartyAndSplitPartiesAndDungeonMasterBo getPartyAndSplitPartiesAndDungeonMasterBoFromSplitPartyRequest(SplitPartyRequest splitPartyRequest);

    PartiesAndDungeonMasterBo getPartiesAndDungeonMasterBoFromMergePartiesRequest(MergePartiesRequest mergePartiesRequest);

    LeavePartyResponse getLeavePartyResponseFromPartyBo(PartyBo partyBo);

    JoinPartyResponse getJoinPartyResponseFromPartyBo(PartyBo partyBo);

    SplitPartyResponse getSplitPartyResponseFromSplitPartiesBo(SplitPartiesBo splitPartiesBo);

    MergePartiesResponse getMergePartiesResponseFromPartyBo(PartyBo partyBo);
}