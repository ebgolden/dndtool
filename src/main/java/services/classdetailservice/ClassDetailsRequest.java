package services.classdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Class;

@Builder
@Value
public class ClassDetailsRequest {
    Class cClass;
}