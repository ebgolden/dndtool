package services.partyservice;

public interface LeaveParty {
    /**
     * Returns a LeavePartyResponse containing a leftParty boolean.
     * Accepts a Party object, a Character object, and a Player object
     * in an LeavePartyRequest.
     * @param leavePartyRequest LeavePartyRequest
     *                                          containing a Party object,
     *                                          a Character object, and a
     *                                          Player object
     * @return LeavePartyResponse containing a leftParty boolean
     */
    LeavePartyResponse getLeavePartyResponse(LeavePartyRequest leavePartyRequest);
}