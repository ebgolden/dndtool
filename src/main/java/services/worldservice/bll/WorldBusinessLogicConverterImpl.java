package services.worldservice.bll;

import commonobjects.DungeonMaster;
import commonobjects.Player;
import commonobjects.Visibility;
import commonobjects.World;
import services.worldservice.ChangeVisibilityOfWorldDetailsRequest;
import services.worldservice.ChangeVisibilityOfWorldDetailsResponse;
import services.worldservice.GetUpdatedWorldRequest;
import services.worldservice.GetUpdatedWorldResponse;
import services.worldservice.bll.bo.WorldAndPlayerBo;
import services.worldservice.bll.bo.WorldAndVisibilityAndDungeonMasterBo;
import services.worldservice.bll.bo.WorldAndVisibilityBo;
import java.util.Map;

public class WorldBusinessLogicConverterImpl implements WorldBusinessLogicConverter {
    public WorldAndPlayerBo getWorldAndPlayerBoFromGetUpdatedWorldRequest(GetUpdatedWorldRequest getUpdatedWorldRequest) {
        World world = getUpdatedWorldRequest.getWorld();
        Player player = getUpdatedWorldRequest.getPlayer();
        return WorldAndPlayerBo
                .builder()
                .world(world)
                .player(player)
                .build();
    }

    public GetUpdatedWorldResponse getGetUpdatedWorldResponseFromWorldAndVisibilityBo(WorldAndVisibilityBo worldAndVisibilityBo) {
        World world = worldAndVisibilityBo.getWorld();
        return GetUpdatedWorldResponse
                .builder()
                .world(world)
                .build();
    }

    public ChangeVisibilityOfWorldDetailsResponse getChangeVisibilityOfWorldDetailsResponseFromWorldAndVisibilityBo(WorldAndVisibilityBo worldAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = worldAndVisibilityBo.getVisibilityMap();
        return ChangeVisibilityOfWorldDetailsResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public WorldAndVisibilityAndDungeonMasterBo getWorldAndVisibilityAndDungeonMasterBoFromChangeVisibilityOfWorldDetailsRequest(ChangeVisibilityOfWorldDetailsRequest changeVisibilityOfGetUpdatedWorldRequest) {
        World world = changeVisibilityOfGetUpdatedWorldRequest.getWorld();
        Map<String, Visibility> visibilityMap = changeVisibilityOfGetUpdatedWorldRequest.getVisibilityMap();
        DungeonMaster dungeonMaster = changeVisibilityOfGetUpdatedWorldRequest.getDungeonMaster();
        return WorldAndVisibilityAndDungeonMasterBo
                .builder()
                .world(world)
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    public WorldAndVisibilityBo getWorldAndVisibilityBoFromWorldAndVisibilityAndDungeonMasterBo(WorldAndVisibilityAndDungeonMasterBo worldAndVisibilityAndDungeonMasterBo) {
        World world = worldAndVisibilityAndDungeonMasterBo.getWorld();
        Map<String, Visibility> visibilityMap = worldAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        return WorldAndVisibilityBo
                .builder()
                .world(world)
                .visibilityMap(visibilityMap)
                .build();
    }
}