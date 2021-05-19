package services.classdetailservice;

import com.google.inject.Inject;
import services.classdetailservice.bll.ClassDetailBusinessLogic;
import services.classdetailservice.bll.ClassDetailBusinessLogicConverter;
import services.classdetailservice.bll.bo.ClassBo;
import services.classdetailservice.bll.bo.ClassDetailsBo;

public class GetClassDetailsImpl implements GetClassDetails {
    @Inject
    private ClassDetailBusinessLogicConverter classDetailBusinessLogicConverter;
    @Inject
    private ClassDetailBusinessLogic classDetailBusinessLogic;

    public ClassDetailsResponse getClassDetailsResponse(ClassDetailsRequest classDetailsRequest) {
        ClassBo classBo = classDetailBusinessLogicConverter.getClassBoFromClassDetailsRequest(classDetailsRequest);
        ClassDetailsBo classDetailsBo = classDetailBusinessLogic.getClassDetailsBo(classBo);
        return classDetailBusinessLogicConverter.getClassDetailsResponseFromClassDetailsBo(classDetailsBo);
    }
}