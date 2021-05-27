package services.actionservice;

import commonobjects.Action;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedActionResponse {
    Action action;
}