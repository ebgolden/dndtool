package domain.characterservice.bll.bo;

import common.Character;
import common.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class CharacterAndVisibilityBo {
    Character character;
    Map<String, Visibility> visibilityMap;
}