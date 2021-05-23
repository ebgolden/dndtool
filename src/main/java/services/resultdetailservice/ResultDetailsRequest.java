package services.resultdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Player;
import objects.Result;

@Builder
@Value
public class ResultDetailsRequest {
    Result result;
    Player player;
}