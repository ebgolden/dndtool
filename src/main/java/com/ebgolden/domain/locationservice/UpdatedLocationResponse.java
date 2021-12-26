package com.ebgolden.domain.locationservice;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Location;

@Builder
@Data
public class UpdatedLocationResponse {
    Location location;
}