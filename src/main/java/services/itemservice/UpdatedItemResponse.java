package services.itemservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.Item;

@Builder
@Data
public class UpdatedItemResponse {
    Item item;
}