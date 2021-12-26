package com.ebgolden.domain.itemservice;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Item;
import com.ebgolden.common.Player;

@Builder
@Data
public class UpdatedItemRequest {
    Item item;
    Player player;
}