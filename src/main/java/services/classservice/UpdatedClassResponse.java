package services.classservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Class;

@Builder
@Value
public class UpdatedClassResponse {
    Class cClass;
}