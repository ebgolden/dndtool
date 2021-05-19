package services.racedetailservice;

public interface GetRaceDetails {
    /**
     * Returns a RaceDetailsResponse containing a Race object.
     * Accepts a Race object in a RaceDetailsRequest.
     * @param raceDetailsRequest RaceDetailsRequest containing
     *                           Race object
     * @return RaceDetailsResponse containing Race object
     */
    RaceDetailsResponse getRaceDetailsResponse(RaceDetailsRequest raceDetailsRequest);
}