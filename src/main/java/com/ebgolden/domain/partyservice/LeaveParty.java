package com.ebgolden.domain.partyservice;

public interface LeaveParty {
    /**
     * Returns a LeavePartyResponse containing a leftParty boolean.
     * Accepts a Party object, a Character object, and a Player
     * object in a LeavePartyRequest.
     * @param leavePartyRequest LeavePartyRequest containing Party
     *                          object, Character object, and
     *                          Player object
     * @return LeavePartyResponse containing leftParty boolean
     */
    LeavePartyResponse getLeavePartyResponse(LeavePartyRequest leavePartyRequest);
}