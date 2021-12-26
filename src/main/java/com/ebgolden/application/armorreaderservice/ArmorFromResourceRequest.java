package application.armorreaderservice;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ArmorFromResourceRequest {
    String armorType;
}