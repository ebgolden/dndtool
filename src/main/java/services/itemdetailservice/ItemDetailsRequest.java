package services.itemdetailservice;

import lombok.Builder;
import lombok.Data;
import objects.Item;
import objects.Player;

@Builder
@Data
public class ItemDetailsRequest {
    Item item;
    Player player;
}