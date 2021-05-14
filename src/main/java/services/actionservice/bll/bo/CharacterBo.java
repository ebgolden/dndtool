package services.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.CharacterObject;

@Builder
@Value
public class CharacterBo {
    CharacterObject character;
}