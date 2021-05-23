package services.resultdetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Result;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class ResultDetailsAndVisibilityBo {
    Result result;
    Map<String, Visibility> visibilityMap;
}