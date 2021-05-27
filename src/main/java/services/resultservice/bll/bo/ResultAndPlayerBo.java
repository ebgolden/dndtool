package services.resultservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Player;
import commonobjects.Result;

@Builder
@Value
public class ResultAndPlayerBo {
    Result result;
    Player player;
}