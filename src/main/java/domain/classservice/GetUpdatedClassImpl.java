package domain.classservice;

import com.google.inject.Inject;
import domain.classservice.bll.ClassBusinessLogic;
import domain.classservice.bll.ClassBusinessLogicConverter;
import domain.classservice.bll.bo.ClassBo;

public class GetUpdatedClassImpl implements GetUpdatedClass {
    @Inject
    private ClassBusinessLogicConverter classBusinessLogicConverter;
    @Inject
    private ClassBusinessLogic classBusinessLogic;

    public UpdatedClassResponse getUpdatedClassResponse(UpdatedClassRequest updatedClassRequest) {
        ClassBo classBo = classBusinessLogicConverter.getClassBoFromUpdatedClassRequest(updatedClassRequest);
        classBo = classBusinessLogic.getClassBo(classBo);
        return classBusinessLogicConverter.getUpdatedClassResponseFromClassBo(classBo);
    }
}