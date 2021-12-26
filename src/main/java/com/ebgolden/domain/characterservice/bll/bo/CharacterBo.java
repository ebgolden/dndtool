package domain.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Character;

@Builder
@Value
public class CharacterBo {
    Character character;
}