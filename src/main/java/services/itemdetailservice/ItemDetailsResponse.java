package services.itemdetailservice;

import lombok.Builder;
import lombok.Data;
import objects.Item;

@Builder
@Data
public class ItemDetailsResponse {
    Item item;
}