package application.armorreaderservice;

import common.Armor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ArmorFromResourceResponse {
    Armor[] armor;
}