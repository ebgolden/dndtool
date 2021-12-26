package domain.actionservice;

import lombok.Builder;
import lombok.Value;
import common.Result;

@Builder
@Value
public class TakeActionResponse {
    Result result;
}