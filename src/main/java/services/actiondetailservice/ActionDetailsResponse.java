package services.actiondetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Action;

@Builder
@Value
public class ActionDetailsResponse {
    Action action;
}