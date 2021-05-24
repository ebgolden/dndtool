package services.spelldetailservice;

import lombok.Builder;
import lombok.Data;
import objects.Visibility;
import java.util.Map;

@Builder
@Data
public class SpellDetailsVisibilityResponse {
    Map<String, Visibility> visibilityMap;
}