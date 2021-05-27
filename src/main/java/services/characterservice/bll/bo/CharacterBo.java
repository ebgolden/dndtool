package services.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Character;

@Builder
@Value
public class CharacterBo {
    Character character;
}