package services.itemdetailservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.Item;
import objects.Player;

@Builder
@Data
public class ItemAndPlayerBo {
    Item item;
    Player player;
}