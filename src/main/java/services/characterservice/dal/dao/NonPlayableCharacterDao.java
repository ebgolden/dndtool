package services.characterservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class NonPlayableCharacterDao {
    String nonPlayableCharacterJson;
}