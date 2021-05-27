package services.characterservice.bll.bo;

import commonobjects.Character;
import commonobjects.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class CharacterAndVisibilityBo {
    Character character;
    Map<String, Visibility> visibilityMap;
}