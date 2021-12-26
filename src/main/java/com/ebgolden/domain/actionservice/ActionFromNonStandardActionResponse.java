package domain.actionservice;

import lombok.Builder;
import lombok.Value;
import common.Action;

@Builder
@Value
public class ActionFromNonStandardActionResponse {
    Action action;
}