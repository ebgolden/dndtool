package domain.itemservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import common.Item;
import common.Visibility;
import java.util.Map;

@Builder
@Data
public class ItemAndVisibilityBo {
    Item item;
    Map<String, Visibility> visibilityMap;
}