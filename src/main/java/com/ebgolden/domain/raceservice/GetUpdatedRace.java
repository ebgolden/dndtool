package domain.raceservice;

public interface GetUpdatedRace {
    /**
     * Returns a UpdatedRaceResponse containing a Race object.
     * Accepts a Race object in a UpdatedRaceRequest.
     * @param updatedRaceRequest UpdatedRaceRequest containing
     *                           Race object
     * @return UpdatedRaceResponse containing Race object
     */
    UpdatedRaceResponse getUpdatedRaceResponse(UpdatedRaceRequest updatedRaceRequest);
}