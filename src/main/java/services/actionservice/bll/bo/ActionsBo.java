package services.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Action;

@Builder
@Value
public class ActionsBo {
    Action[] actions;
}