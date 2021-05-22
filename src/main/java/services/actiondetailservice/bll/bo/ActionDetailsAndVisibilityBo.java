package services.actiondetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Action;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class ActionDetailsAndVisibilityBo {
    Action action;
    Map<String, Visibility> visibilityMap;
}