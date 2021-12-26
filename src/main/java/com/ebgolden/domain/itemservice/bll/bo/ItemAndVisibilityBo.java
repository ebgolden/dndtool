package com.ebgolden.domain.itemservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Item;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Data
public class ItemAndVisibilityBo {
    Item item;
    Map<String, Visibility> visibilityMap;
}