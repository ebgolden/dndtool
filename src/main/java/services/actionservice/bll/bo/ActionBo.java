package services.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Action;

@Builder
@Value
public class ActionBo {
    Action action;
}