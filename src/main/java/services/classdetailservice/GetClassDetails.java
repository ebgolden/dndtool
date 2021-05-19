package services.classdetailservice;

public interface GetClassDetails {
    /**
     * Returns a ClassDetailsResponse containing a Class object.
     * Accepts a Class object in a ClassDetailsRequest.
     * @param classDetailsRequest ClassDetailsRequest containing
     *                            Class object
     * @return ClassDetailsResponse containing Class object
     */
    ClassDetailsResponse getClassDetailsResponse(ClassDetailsRequest classDetailsRequest);
}