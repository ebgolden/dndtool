package services.worldservice;

public interface GetUpdatedWorldDetails {
    /**
     * Returns a GetUpdatedWorldResponse containing a World object.
     * Accepts a World object and a Player object in a
     * GetUpdatedWorldRequest.
     * @param getUpdatedWorldRequest GetUpdatedWorldRequest containing
     *                               World object and Player object
     * @return GetUpdatedWorldResponse containing World object
     */
    GetUpdatedWorldResponse getGetUpdatedWorldResponse(GetUpdatedWorldRequest getUpdatedWorldRequest);
}