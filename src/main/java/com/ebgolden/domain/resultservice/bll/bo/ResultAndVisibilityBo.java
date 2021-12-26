package domain.resultservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Result;
import common.Visibility;
import java.util.Map;

@Builder
@Value
public class ResultAndVisibilityBo {
    Result result;
    Map<String, Visibility> visibilityMap;
}