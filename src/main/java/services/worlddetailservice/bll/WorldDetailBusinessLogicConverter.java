package services.worlddetailservice.bll;

import services.worlddetailservice.WorldDetailsRequest;
import services.worlddetailservice.WorldDetailsResponse;
import services.worlddetailservice.bll.bo.WorldAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityBo;

public interface WorldDetailBusinessLogicConverter {
    WorldAndPlayerBo getWorldAndPlayerBoFromWorldDetailsRequest(WorldDetailsRequest worldDetailsRequest);

    WorldDetailsResponse getWorldDetailsResponseFromWorldDetailsAndVisibilityBo(WorldDetailsAndVisibilityBo worldDetailsAndVisibilityBo);
}