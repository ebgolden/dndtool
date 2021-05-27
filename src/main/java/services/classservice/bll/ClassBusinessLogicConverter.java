package services.classservice.bll;

import services.classservice.UpdatedClassRequest;
import services.classservice.UpdatedClassResponse;
import services.classservice.bll.bo.ClassBo;

public interface ClassBusinessLogicConverter {
    ClassBo getClassBoFromUpdatedClassRequest(UpdatedClassRequest updatedClassRequest);

    UpdatedClassResponse getUpdatedClassResponseFromClassBo(ClassBo classBo);
}