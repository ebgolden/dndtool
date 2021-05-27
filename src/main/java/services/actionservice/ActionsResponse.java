package services.actionservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Action;

@Builder
@Value
public class ActionsResponse {
    Action[] actions;
}