package domain.itemservice;

import lombok.Builder;
import lombok.Data;
import common.Item;
import common.Player;

@Builder
@Data
public class UpdatedItemRequest {
    Item item;
    Player player;
}