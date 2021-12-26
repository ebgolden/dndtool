package domain.itemservice;

import lombok.Builder;
import lombok.Data;
import common.Item;

@Builder
@Data
public class UpdatedItemResponse {
    Item item;
}