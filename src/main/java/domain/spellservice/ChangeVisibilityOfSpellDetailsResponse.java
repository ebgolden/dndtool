package domain.spellservice;

import lombok.Builder;
import lombok.Data;
import common.Visibility;
import java.util.Map;

@Builder
@Data
public class ChangeVisibilityOfSpellDetailsResponse {
    Map<String, Visibility> visibilityMap;
}