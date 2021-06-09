package domain.characterservice;

import common.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfCharacterDetailsResponse {
    Map<String, Visibility> visibilityMap;
}