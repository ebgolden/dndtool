package domain.characterclassservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.CharacterClass;

@Builder
@Value
public class CharacterClassBo {
    CharacterClass characterClass;
}