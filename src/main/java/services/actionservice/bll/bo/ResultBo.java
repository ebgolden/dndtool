package services.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Result;

@Builder
@Value
public class ResultBo {
    Result result;
}