package services.partyservice.bll;

import services.partyservice.*;
import services.partyservice.bll.bo.*;

public interface PartyBusinessLogicConverter {
    PartyAndCharacterAndPlayerBo getPartyAndCharacterAndPlayerBoFromLeavePartyRequest(LeavePartyRequest leavePartyRequest);

    PartyAndCharacterAndPlayerAndAcceptedByPartyBo getPartyAndCharacterAndPlayerAndAcceptedByPartyBoFromJoinPartyRequest(JoinPartyRequest joinPartyRequest);

    PartyAndSplitPartiesAndDungeonMasterBo getPartyAndSplitPartiesAndDungeonMasterBoFromSplitPartyRequest(SplitPartyRequest splitPartyRequest);

    PartiesAndDungeonMasterBo getPartiesAndDungeonMasterBoFromMergePartiesRequest(MergePartiesRequest mergePartiesRequest);

    LeavePartyResponse getLeavePartyResponseFromLeftPartyBo(LeftPartyBo leftPartyBo);

    JoinPartyResponse getJoinPartyResponseFromJoinedPartyBo(JoinedPartyBo joinedPartyBo);

    SplitPartyResponse getSplitPartyResponseFromSplitPartiesBo(SplitPartiesBo splitPartiesBo);

    MergePartiesResponse getMergePartiesResponseFromPartyBo(PartyBo partyBo);

    LeftPartyBo getLeftPartyBoFromPartyBo(PartyBo partyBo);

    JoinedPartyBo getJoinedPartyBoFromPartyBo(PartyBo partyBo);
}