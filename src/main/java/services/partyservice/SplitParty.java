package services.partyservice;

public interface SplitParty {
    /**
     * Returns a SplitPartyResponse containing an array of Party objects.
     * Accepts a Party object, an array of Party objects, and a
     * DungeonMaster object in a SplitPartyRequest.
     * @param splitPartyRequest SplitPartyRequest containing Party
     *                          object, array of Party objects, and
     *                          DungeonMaster object
     * @return SplitPartyResponse containing array of Party objects
     */
    SplitPartyResponse getSplitPartyResponse(SplitPartyRequest splitPartyRequest);
}