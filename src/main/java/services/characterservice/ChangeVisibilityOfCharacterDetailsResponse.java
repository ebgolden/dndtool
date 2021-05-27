package services.characterservice;

import commonobjects.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfCharacterDetailsResponse {
    Map<String, Visibility> visibilityMap;
}