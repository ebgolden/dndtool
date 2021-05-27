package services.spellservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Data
public class ChangeVisibilityOfSpellDetailsResponse {
    Map<String, Visibility> visibilityMap;
}