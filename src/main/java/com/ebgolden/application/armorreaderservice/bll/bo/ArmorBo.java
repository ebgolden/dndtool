package com.ebgolden.application.armorreaderservice.bll.bo;

import com.ebgolden.common.Armor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ArmorBo {
    Armor[] armor;
}