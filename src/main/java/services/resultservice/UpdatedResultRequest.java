package services.resultservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Player;
import commonobjects.Result;

@Builder
@Value
public class UpdatedResultRequest {
    Result result;
    Player player;
}