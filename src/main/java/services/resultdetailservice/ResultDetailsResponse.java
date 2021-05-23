package services.resultdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Result;

@Builder
@Value
public class ResultDetailsResponse {
    Result result;
}