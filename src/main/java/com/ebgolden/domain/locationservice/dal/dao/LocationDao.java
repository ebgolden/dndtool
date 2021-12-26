package domain.locationservice.dal.dao;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocationDao {
    String locationJson;
}