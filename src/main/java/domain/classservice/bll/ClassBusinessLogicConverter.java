package domain.classservice.bll;

import domain.classservice.UpdatedClassRequest;
import domain.classservice.UpdatedClassResponse;
import domain.classservice.bll.bo.ClassBo;

public interface ClassBusinessLogicConverter {
    ClassBo getClassBoFromUpdatedClassRequest(UpdatedClassRequest updatedClassRequest);

    UpdatedClassResponse getUpdatedClassResponseFromClassBo(ClassBo classBo);
}