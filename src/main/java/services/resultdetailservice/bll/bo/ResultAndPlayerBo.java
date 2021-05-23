package services.resultdetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Player;
import objects.Result;

@Builder
@Value
public class ResultAndPlayerBo {
    Result result;
    Player player;
}