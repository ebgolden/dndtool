package domain.classservice;

import lombok.Builder;
import lombok.Value;
import common.Class;

@Builder
@Value
public class UpdatedClassRequest {
    Class cClass;
}