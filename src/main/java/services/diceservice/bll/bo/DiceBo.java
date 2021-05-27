package services.diceservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Die;

@Builder
@Value
public class DiceBo {
    Die[] dice;
}