package services.classdetailservice.bll;

import services.classdetailservice.bll.bo.ClassBo;
import services.classdetailservice.bll.bo.ClassDetailsBo;

public interface ClassDetailBusinessLogic {
    ClassDetailsBo getClassDetailsBo(ClassBo classBo);
}