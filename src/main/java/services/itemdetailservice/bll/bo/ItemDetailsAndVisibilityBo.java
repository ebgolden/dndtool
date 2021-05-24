package services.itemdetailservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.Item;
import objects.Visibility;
import java.util.Map;

@Builder
@Data
public class ItemDetailsAndVisibilityBo {
    Item item;
    Map<String, Visibility> visibilityMap;
}