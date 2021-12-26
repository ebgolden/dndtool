package com.ebgolden.domain.partyservice;

public interface JoinParty {
    /**
     * Returns a JoinPartyResponse containing a joinedParty boolean.
     * Accepts a Party object, a Character object, a Player object,
     * and a acceptedByParty boolean in a JoinPartyRequest.
     * @param joinPartyRequest JoinPartyRequest containing Party
     *                         object, Character object, Player
     *                         object, and acceptedByParty boolean
     * @return JoinPartyResponse containing joinedParty boolean
     */
    JoinPartyResponse getJoinPartyResponse(JoinPartyRequest joinPartyRequest);
}