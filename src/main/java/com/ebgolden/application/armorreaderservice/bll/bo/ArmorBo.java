package application.armorreaderservice.bll.bo;

import common.Armor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ArmorBo {
    Armor[] armor;
}