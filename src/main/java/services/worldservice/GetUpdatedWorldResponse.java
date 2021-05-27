package services.worldservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.World;

@Builder
@Value
public class GetUpdatedWorldResponse {
    World world;
}