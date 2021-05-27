package services.resultservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Result;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Value
public class ResultAndVisibilityBo {
    Result result;
    Map<String, Visibility> visibilityMap;
}