package domain.diceservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Die;

@Builder
@Value
public class DiceBo {
    Die[] dice;
}