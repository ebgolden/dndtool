package domain.partyservice.bll;

import common.Character;
import common.DungeonMaster;
import common.Party;
import common.Player;
import domain.partyservice.*;
import domain.partyservice.bll.bo.*;

public class PartyBusinessLogicConverterImpl implements PartyBusinessLogicConverter {
    public PartyAndCharacterAndPlayerBo getPartyAndCharacterAndPlayerBoFromLeavePartyRequest(LeavePartyRequest leavePartyRequest) {
        Party party = leavePartyRequest.getParty();
        Character character = leavePartyRequest.getCharacter();
        Player player = leavePartyRequest.getPlayer();
        return PartyAndCharacterAndPlayerBo
                .builder()
                .party(party)
                .character(character)
                .player(player)
                .build();
    }

    public PartyAndCharacterAndPlayerAndAcceptedByPartyBo getPartyAndCharacterAndPlayerAndAcceptedByPartyBoFromJoinPartyRequest(JoinPartyRequest joinPartyRequest) {
        Party party = joinPartyRequest.getParty();
        Character character = joinPartyRequest.getCharacter();
        Player player = joinPartyRequest.getPlayer();
        boolean acceptedByParty = joinPartyRequest.isAcceptedByParty();
        return PartyAndCharacterAndPlayerAndAcceptedByPartyBo
                .builder()
                .party(party)
                .character(character)
                .player(player)
                .acceptedByParty(acceptedByParty)
                .build();
    }

    public PartyAndSplitPartiesAndDungeonMasterBo getPartyAndSplitPartiesAndDungeonMasterBoFromSplitPartyRequest(SplitPartyRequest splitPartyRequest) {
        Party party = splitPartyRequest.getParty();
        Party[] splitParties = splitPartyRequest.getSplitParties();
        DungeonMaster dungeonMaster = splitPartyRequest.getDungeonMaster();
        return PartyAndSplitPartiesAndDungeonMasterBo
                .builder()
                .party(party)
                .splitParties(splitParties)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    public PartiesAndDungeonMasterBo getPartiesAndDungeonMasterBoFromMergePartiesRequest(MergePartiesRequest mergePartiesRequest) {
        Party[] parties = mergePartiesRequest.getParties();
        DungeonMaster dungeonMaster = mergePartiesRequest.getDungeonMaster();
        return PartiesAndDungeonMasterBo
                .builder()
                .parties(parties)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    public LeavePartyResponse getLeavePartyResponseFromPartyBo(PartyBo partyBo) {
        Party party = partyBo.getParty();
        return LeavePartyResponse
                .builder()
                .party(party)
                .build();
    }

    public JoinPartyResponse getJoinPartyResponseFromPartyBo(PartyBo partyBo) {
        Party party = partyBo.getParty();
        return JoinPartyResponse
                .builder()
                .party(party)
                .build();
    }

    public SplitPartyResponse getSplitPartyResponseFromSplitPartiesBo(SplitPartiesBo splitPartiesBo) {
        Party[] splitParties = splitPartiesBo.getSplitParties();
        return SplitPartyResponse
                .builder()
                .splitParties(splitParties)
                .build();
    }

    public MergePartiesResponse getMergePartiesResponseFromPartyBo(PartyBo partyBo) {
        Party party = partyBo.getParty();
        return MergePartiesResponse
                .builder()
                .party(party)
                .build();
    }
}