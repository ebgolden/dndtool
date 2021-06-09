package domain.partyservice.bll;

import domain.partyservice.*;
import domain.partyservice.bll.bo.*;

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