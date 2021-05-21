package services.worlddetailservice.bll;

import objects.Player;
import objects.Visibility;
import objects.World;
import services.worlddetailservice.WorldDetailsRequest;
import services.worlddetailservice.WorldDetailsResponse;
import services.worlddetailservice.WorldDetailsVisibilityRequest;
import services.worlddetailservice.WorldDetailsVisibilityResponse;
import services.worlddetailservice.bll.bo.WorldAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityBo;
import java.util.Map;

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
        Map<String, Visibility> visibilityMap = worldDetailsAndVisibilityBo.getVisibilityMap();
        return WorldDetailsVisibilityResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public WorldDetailsAndVisibilityAndPlayerBo getWorldDetailsAndVisibilityAndPlayerBoFromWorldDetailsVisibilityRequest(WorldDetailsVisibilityRequest worldDetailsVisibilityRequest) {
        World world = worldDetailsVisibilityRequest.getWorld();
        Map<String, Visibility> visibilityMap = worldDetailsVisibilityRequest.getVisibilityMap();
        Player player = worldDetailsVisibilityRequest.getPlayer();
        return WorldDetailsAndVisibilityAndPlayerBo
                .builder()
                .world(world)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public WorldDetailsAndVisibilityBo getWorldDetailsAndVisibilityBoFromWorldDetailsAndVisibilityAndPlayerBo(WorldDetailsAndVisibilityAndPlayerBo worldDetailsAndVisibilityAndPlayerBo) {
        World world = worldDetailsAndVisibilityAndPlayerBo.getWorld();
        Map<String, Visibility> visibilityMap = worldDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        return WorldDetailsAndVisibilityBo
                .builder()
                .world(world)
                .visibilityMap(visibilityMap)
                .build();
    }
}