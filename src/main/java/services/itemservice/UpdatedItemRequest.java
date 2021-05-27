package services.itemservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.Item;
import commonobjects.Player;

@Builder
@Data
public class UpdatedItemRequest {
    Item item;
    Player player;
}