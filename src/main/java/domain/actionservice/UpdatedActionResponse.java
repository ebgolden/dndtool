package domain.actionservice;

import common.Action;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedActionResponse {
    Action action;
}