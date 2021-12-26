package com.ebgolden.domain.itemservice;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Item;
import com.ebgolden.common.Player;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Data
public class ChangeVisibilityOfItemDetailsRequest {
    Item item;
    Map<String, Visibility> visibilityMap;
    Player player;
}