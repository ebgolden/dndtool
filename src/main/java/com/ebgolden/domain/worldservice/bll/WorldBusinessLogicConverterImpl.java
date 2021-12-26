package com.ebgolden.domain.worldservice.bll;

import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.Player;
import com.ebgolden.common.Visibility;
import com.ebgolden.common.World;
import com.ebgolden.domain.worldservice.ChangeVisibilityOfWorldDetailsRequest;
import com.ebgolden.domain.worldservice.ChangeVisibilityOfWorldDetailsResponse;
import com.ebgolden.domain.worldservice.GetUpdatedWorldRequest;
import com.ebgolden.domain.worldservice.GetUpdatedWorldResponse;
import com.ebgolden.domain.worldservice.bll.bo.WorldAndPlayerBo;
import com.ebgolden.domain.worldservice.bll.bo.WorldAndVisibilityAndDungeonMasterBo;
import com.ebgolden.domain.worldservice.bll.bo.WorldAndVisibilityBo;
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