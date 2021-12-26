package com.ebgolden.domain.worldservice;

import com.google.inject.Inject;
import com.ebgolden.domain.worldservice.bll.WorldBusinessLogic;
import com.ebgolden.domain.worldservice.bll.WorldBusinessLogicConverter;
import com.ebgolden.domain.worldservice.bll.bo.WorldAndPlayerBo;
import com.ebgolden.domain.worldservice.bll.bo.WorldAndVisibilityBo;

public class GetUpdatedWorldDetailsImpl implements GetUpdatedWorldDetails {
    @Inject
    private WorldBusinessLogicConverter worldBusinessLogicConverter;
    @Inject
    private WorldBusinessLogic worldBusinessLogic;

    public GetUpdatedWorldResponse getGetUpdatedWorldResponse(GetUpdatedWorldRequest getUpdatedWorldRequest) {
        WorldAndPlayerBo worldAndPlayerBo = worldBusinessLogicConverter.getWorldAndPlayerBoFromGetUpdatedWorldRequest(getUpdatedWorldRequest);
        WorldAndVisibilityBo worldAndVisibilityBo = worldBusinessLogic.getWorldAndVisibilityBo(worldAndPlayerBo);
        return worldBusinessLogicConverter.getGetUpdatedWorldResponseFromWorldAndVisibilityBo(worldAndVisibilityBo);
    }
}