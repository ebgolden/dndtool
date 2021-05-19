package services.classdetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Class;

@Builder
@Value
public class ClassBo {
    Class cClass;
}