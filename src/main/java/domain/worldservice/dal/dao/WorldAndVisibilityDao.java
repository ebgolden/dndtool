package domain.worldservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class WorldAndVisibilityDao {
    String worldAndVisibilityJson;
}