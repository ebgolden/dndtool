package services.classdetailservice.bll;

import services.classdetailservice.ClassDetailsRequest;
import services.classdetailservice.ClassDetailsResponse;
import services.classdetailservice.bll.bo.ClassBo;
import services.classdetailservice.bll.bo.ClassDetailsBo;

public interface ClassDetailBusinessLogicConverter {
    ClassBo getClassBoFromClassDetailsRequest(ClassDetailsRequest classDetailsRequest);

    ClassDetailsResponse getClassDetailsResponseFromClassDetailsBo(ClassDetailsBo classDetailsBo);
}