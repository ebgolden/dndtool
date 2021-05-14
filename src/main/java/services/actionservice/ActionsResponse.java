package services.actionservice;

import lombok.Builder;
import lombok.Value;
import objects.Action;

@Builder
@Value
public class ActionsResponse {
    Action[] actions;
}