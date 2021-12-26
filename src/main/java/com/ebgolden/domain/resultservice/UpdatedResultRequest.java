package domain.resultservice;

import lombok.Builder;
import lombok.Value;
import common.Player;
import common.Result;

@Builder
@Value
public class UpdatedResultRequest {
    Result result;
    Player player;
}