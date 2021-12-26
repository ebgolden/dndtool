package application.characterclassreaderservice;

import common.CharacterClass;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CharacterClassFromResourceResponse {
    CharacterClass characterClass;
}