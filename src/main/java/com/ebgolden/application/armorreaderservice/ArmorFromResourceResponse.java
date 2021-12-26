package com.ebgolden.application.armorreaderservice;

import com.ebgolden.common.Armor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ArmorFromResourceResponse {
    Armor[] armor;
}