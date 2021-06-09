package domain.actionservice.bll.bo;

import common.Action;
import common.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class ActionAndVisibilityBo {
    Action action;
    Map<String, Visibility> visibilityMap;
}