package domain.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Action;

@Builder
@Value
public class ActionsBo {
    Action[] actions;
}