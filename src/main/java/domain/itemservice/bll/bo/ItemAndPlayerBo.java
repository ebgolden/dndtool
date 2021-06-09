package domain.itemservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import common.Item;
import common.Player;

@Builder
@Data
public class ItemAndPlayerBo {
    Item item;
    Player player;
}