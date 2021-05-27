package services.classservice;

import com.google.inject.Inject;
import services.classservice.bll.ClassBusinessLogic;
import services.classservice.bll.ClassBusinessLogicConverter;
import services.classservice.bll.bo.ClassBo;

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