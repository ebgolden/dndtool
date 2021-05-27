package services.itemservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import commonobjects.Item;
import commonobjects.Player;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Data
public class ItemAndVisibilityAndPlayerBo {
    Item item;
    Map<String, Visibility> visibilityMap;
    Player player;
}