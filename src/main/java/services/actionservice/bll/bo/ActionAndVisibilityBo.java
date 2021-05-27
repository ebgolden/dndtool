package services.actionservice.bll.bo;

import commonobjects.Action;
import commonobjects.Visibility;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Builder
@Value
public class ActionAndVisibilityBo {
    Action action;
    Map<String, Visibility> visibilityMap;
}