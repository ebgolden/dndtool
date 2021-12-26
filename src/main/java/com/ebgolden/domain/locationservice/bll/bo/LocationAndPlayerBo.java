package com.ebgolden.domain.locationservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Location;
import com.ebgolden.common.Player;

@Builder
@Data
public class LocationAndPlayerBo {
    Location location;
    Player player;
}