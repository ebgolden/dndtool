package domain.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Result;

@Builder
@Value
public class ResultBo {
    Result result;
}