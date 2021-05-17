package services.worlddetailservice.bll;

import objects.Player;
import objects.World;
import services.worlddetailservice.WorldDetailsRequest;
import services.worlddetailservice.WorldDetailsResponse;
import services.worlddetailservice.WorldDetailsVisibilityRequest;
import services.worlddetailservice.WorldDetailsVisibilityResponse;
import services.worlddetailservice.bll.bo.WorldAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityAndPlayerBo;
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

    public WorldDetailsVisibilityResponse getWorldDetailsVisibilityResponseFromWorldDetailsAndVisibilityBo(WorldDetailsAndVisibilityBo worldDetailsAndVisibilityBo) {
        String visibilityJson = worldDetailsAndVisibilityBo.getVisibilityJson();
        return WorldDetailsVisibilityResponse
                .builder()
                .visibilityJson(visibilityJson)
                .build();
    }

    public WorldDetailsAndVisibilityAndPlayerBo getWorldDetailsAndVisibilityAndPlayerBoFromWorldDetailsVisibilityRequest(WorldDetailsVisibilityRequest worldDetailsVisibilityRequest) {
        World world = worldDetailsVisibilityRequest.getWorld();
        String visibilityJson = worldDetailsVisibilityRequest.getVisibilityJson();
        Player player = worldDetailsVisibilityRequest.getPlayer();
        return WorldDetailsAndVisibilityAndPlayerBo
                .builder()
                .world(world)
                .visibilityJson(visibilityJson)
                .player(player)
                .build();
    }

    public WorldDetailsAndVisibilityBo getWorldDetailsAndVisibilityBoFromWorldDetailsAndVisibilityAndPlayerBo(WorldDetailsAndVisibilityAndPlayerBo worldDetailsAndVisibilityAndPlayerBo) {
        World world = worldDetailsAndVisibilityAndPlayerBo.getWorld();
        String visibilityJson = worldDetailsAndVisibilityAndPlayerBo.getVisibilityJson();
        return WorldDetailsAndVisibilityBo
                .builder()
                .world(world)
                .visibilityJson(visibilityJson)
                .build();
    }
}