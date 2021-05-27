package services.actionservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Result;

@Builder
@Value
public class TakeActionResponse {
    Result result;
}