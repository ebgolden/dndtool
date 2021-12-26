package com.ebgolden.domain.locationservice;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Location;
import com.ebgolden.common.Player;

@Builder
@Data
public class UpdatedLocationRequest {
    Location location;
    Player player;
}