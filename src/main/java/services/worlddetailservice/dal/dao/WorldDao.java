package services.worlddetailservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class WorldDao {
    String worldJson;
}