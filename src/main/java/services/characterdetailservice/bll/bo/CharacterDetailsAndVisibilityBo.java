package services.characterdetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.CharacterObject;

@Builder
@Value
public class CharacterDetailsAndVisibilityBo {
    CharacterObject character;
    String visibilityJson;
}