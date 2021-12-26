package application.characterclassreaderservice.bll.bo;

import common.CharacterClass;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CharacterClassBo {
    CharacterClass characterClass;
}