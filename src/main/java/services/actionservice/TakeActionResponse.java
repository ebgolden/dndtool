package services.actionservice;

import lombok.Builder;
import lombok.Value;
import objects.Result;

@Builder
@Value
public class TakeActionResponse {
    Result result;
}