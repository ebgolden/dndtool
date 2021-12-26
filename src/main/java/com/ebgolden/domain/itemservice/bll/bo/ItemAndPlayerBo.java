package com.ebgolden.domain.itemservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Item;
import com.ebgolden.common.Player;

@Builder
@Data
public class ItemAndPlayerBo {
    Item item;
    Player player;
}