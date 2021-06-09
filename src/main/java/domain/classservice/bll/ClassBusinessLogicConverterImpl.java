package domain.classservice.bll;

import common.Class;
import domain.classservice.UpdatedClassRequest;
import domain.classservice.UpdatedClassResponse;
import domain.classservice.bll.bo.ClassBo;

public class ClassBusinessLogicConverterImpl implements ClassBusinessLogicConverter {
    public ClassBo getClassBoFromUpdatedClassRequest(UpdatedClassRequest updatedClassRequest) {
        Class cClass = updatedClassRequest.getCClass();
        return ClassBo
                .builder()
                .cClass(cClass)
                .build();
    }

    public UpdatedClassResponse getUpdatedClassResponseFromClassBo(ClassBo classBo) {
        Class cClass = classBo.getCClass();
        return UpdatedClassResponse
                .builder()
                .cClass(cClass)
                .build();
    }
}