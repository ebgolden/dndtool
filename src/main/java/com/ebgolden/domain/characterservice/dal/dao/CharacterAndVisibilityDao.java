package domain.characterservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CharacterAndVisibilityDao {
    String characterAndVisibilityJson;
}