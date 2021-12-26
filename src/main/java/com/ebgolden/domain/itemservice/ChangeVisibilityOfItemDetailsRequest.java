package domain.itemservice;

import lombok.Builder;
import lombok.Data;
import common.Item;
import common.Player;
import common.Visibility;
import java.util.Map;

@Builder
@Data
public class ChangeVisibilityOfItemDetailsRequest {
    Item item;
    Map<String, Visibility> visibilityMap;
    Player player;
}