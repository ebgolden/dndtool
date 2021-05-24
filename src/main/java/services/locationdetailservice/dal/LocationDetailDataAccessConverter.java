package services.locationdetailservice.dal;

import services.locationdetailservice.bll.bo.LocationAndPlayerBo;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityBo;
import services.locationdetailservice.dal.dao.LocationDao;
import services.locationdetailservice.dal.dao.LocationDetailsAndVisibilityDao;

public interface LocationDetailDataAccessConverter {
    LocationDao getLocationDaoFromLocationAndPlayerBo(LocationAndPlayerBo locationAndPlayerBo);

    LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDaoFromLocationDetailsAndVisibilityBo(LocationDetailsAndVisibilityBo locationDetailsAndVisibilityBo);

    LocationDetailsAndVisibilityBo getLocationDetailsAndVisibilityBoFromLocationDetailsAndVisibilityDao(LocationDetailsAndVisibilityDao locationDetailsAndVisibilityDao);

    LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDaoFromLatestJsonObject(String latestJsonObject);
}