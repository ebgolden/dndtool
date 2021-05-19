package services.classdetailservice.bll;

import objects.Class;
import services.classdetailservice.ClassDetailsRequest;
import services.classdetailservice.ClassDetailsResponse;
import services.classdetailservice.bll.bo.ClassBo;
import services.classdetailservice.bll.bo.ClassDetailsBo;

public class ClassDetailBusinessLogicConverterImpl implements ClassDetailBusinessLogicConverter {
    public ClassBo getClassBoFromClassDetailsRequest(ClassDetailsRequest classDetailsRequest) {
        Class cClass = classDetailsRequest.getCClass();
        return ClassBo
                .builder()
                .cClass(cClass)
                .build();
    }

    public ClassDetailsResponse getClassDetailsResponseFromClassDetailsBo(ClassDetailsBo classDetailsBo) {
        Class cClass = classDetailsBo.getCClass();
        return ClassDetailsResponse
                .builder()
                .cClass(cClass)
                .build();
    }
}