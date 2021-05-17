package services.characterdetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Character;

@Builder
@Value
public class CharacterDetailsAndVisibilityBo {
    Character character;
    String visibilityJson;
}