package services.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Character;

@Builder
@Value
public class CharacterBo {
    Character character;
}