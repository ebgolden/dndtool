package services.itemservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import commonobjects.Item;
import commonobjects.Player;

@Builder
@Data
public class ItemAndPlayerBo {
    Item item;
    Player player;
}