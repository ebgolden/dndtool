package domain.resultservice;

import lombok.Builder;
import lombok.Value;
import common.Result;

@Builder
@Value
public class UpdatedResultResponse {
    Result result;
}