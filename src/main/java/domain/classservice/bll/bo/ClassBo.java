package domain.classservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Class;

@Builder
@Value
public class ClassBo {
    Class cClass;
}