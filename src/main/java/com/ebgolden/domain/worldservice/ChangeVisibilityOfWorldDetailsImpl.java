package com.ebgolden.domain.worldservice;

import com.google.inject.Inject;
import com.ebgolden.domain.worldservice.bll.WorldBusinessLogic;
import com.ebgolden.domain.worldservice.bll.WorldBusinessLogicConverter;
import com.ebgolden.domain.worldservice.bll.bo.WorldAndVisibilityAndDungeonMasterBo;
import com.ebgolden.domain.worldservice.bll.bo.WorldAndVisibilityBo;

public class ChangeVisibilityOfWorldDetailsImpl implements ChangeVisibilityOfWorldDetails {
    @Inject
    private WorldBusinessLogicConverter worldBusinessLogicConverter;
    @Inject
    private WorldBusinessLogic worldBusinessLogic;

    public ChangeVisibilityOfWorldDetailsResponse getChangeVisibilityOfWorldDetailsResponse(ChangeVisibilityOfWorldDetailsRequest changeVisibilityOfGetUpdatedWorldRequest) {
        WorldAndVisibilityAndDungeonMasterBo worldAndVisibilityAndDungeonMasterBo = worldBusinessLogicConverter.getWorldAndVisibilityAndDungeonMasterBoFromChangeVisibilityOfWorldDetailsRequest(changeVisibilityOfGetUpdatedWorldRequest);
        WorldAndVisibilityBo worldAndVisibilityBo = worldBusinessLogic.getWorldAndVisibilityBo(worldAndVisibilityAndDungeonMasterBo);
        return worldBusinessLogicConverter.getChangeVisibilityOfWorldDetailsResponseFromWorldAndVisibilityBo(worldAndVisibilityBo);
    }
}