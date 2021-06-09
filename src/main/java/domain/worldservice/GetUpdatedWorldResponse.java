package domain.worldservice;

import lombok.Builder;
import lombok.Value;
import common.World;

@Builder
@Value
public class GetUpdatedWorldResponse {
    World world;
}