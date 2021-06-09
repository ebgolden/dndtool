package domain.classservice;

public interface GetUpdatedClass {
    /**
     * Returns a UpdatedClassResponse containing a Class object.
     * Accepts a Class object in a UpdatedClassRequest.
     * @param updatedClassRequest UpdatedClassRequest containing
     *                            Class object
     * @return UpdatedClassResponse containing Class object
     */
    UpdatedClassResponse getUpdatedClassResponse(UpdatedClassRequest updatedClassRequest);
}