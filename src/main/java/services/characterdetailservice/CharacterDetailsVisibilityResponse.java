package services.characterdetailservice;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CharacterDetailsVisibilityResponse {
    String visibilityJson;
}