package services.worlddetailservice.bll;

import objects.Player;
import objects.World;
import services.worlddetailservice.WorldDetailsRequest;
import services.worlddetailservice.WorldDetailsResponse;
import services.worlddetailservice.bll.bo.WorldAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityBo;

public class WorldDetailBusinessLogicConverterImpl implements WorldDetailBusinessLogicConverter {
    public WorldAndPlayerBo getWorldAndPlayerBoFromWorldDetailsRequest(WorldDetailsRequest worldDetailsRequest) {
        World world = worldDetailsRequest.getWorld();
        Player player = worldDetailsRequest.getPlayer();
        return WorldAndPlayerBo
                .builder()
                .world(world)
                .player(player)
                .build();
    }

    public WorldDetailsResponse getWorldDetailsResponseFromWorldDetailsAndVisibilityBo(WorldDetailsAndVisibilityBo worldDetailsAndVisibilityBo) {
        World world = worldDetailsAndVisibilityBo.getWorld();
        return WorldDetailsResponse
                .builder()
                .world(world)
                .build();
    }
}