package com.ebgolden.domain.worldservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.World;

@Builder
@Value
public class GetUpdatedWorldResponse {
    World world;
}