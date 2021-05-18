package services.partyservice;

public interface JoinParty {
    /**
     * Returns a JoinPartyResponse containing a joinedParty boolean.
     * Accepts a Party object, a Character object, a Player object,
     * and a acceptedByParty boolean in an JoinPartyRequest.
     * @param joinPartyRequest JoinPartyRequest
     *                         containing a Party object,
     *                         a Character object, a
     *                         Player object, and a
     *                         acceptedByParty boolean
     * @return JoinPartyResponse containing a joinedParty boolean
     */
    JoinPartyResponse getJoinPartyResponse(JoinPartyRequest joinPartyRequest);
}