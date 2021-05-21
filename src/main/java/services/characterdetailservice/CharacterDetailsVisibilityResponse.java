package services.characterdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class CharacterDetailsVisibilityResponse {
    Map<String, Visibility> visibilityMap;
}