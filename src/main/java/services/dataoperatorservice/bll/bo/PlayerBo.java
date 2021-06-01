package services.dataoperatorservice.bll.bo;

import commonobjects.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PlayerBo {
    Player player;
}