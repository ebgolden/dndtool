package services.worlddetailservice;

public interface GetWorldDetails {
    /**
     * Returns a WorldDetailsResponse containing a World object.
     * Accepts a World object and a Player object in an WorldDetailsRequest.
     * @param worldDetailsRequest WorldDetailsRequest containing World object
     *                            and Player object
     * @return WorldDetailsResponse containing a World object
     */
    WorldDetailsResponse getWorldDetailsResponse(WorldDetailsRequest worldDetailsRequest);
}