package domain.resultservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Player;
import common.Result;

@Builder
@Value
public class ResultAndPlayerBo {
    Result result;
    Player player;
}