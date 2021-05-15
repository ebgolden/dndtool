package services.characterdetailservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CharacterDao {
    String characterJson;
}