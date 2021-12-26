package com.ebgolden.domain.itemservice;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Item;

@Builder
@Data
public class UpdatedItemResponse {
    Item item;
}