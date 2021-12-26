package persistence.operatorservice.bll.bo;

import common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PlayerBo {
    Player player;
}