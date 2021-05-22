package services.actiondetailservice.dal;

import services.actiondetailservice.dal.dao.ActionDao;
import services.actiondetailservice.dal.dao.ActionDetailsAndVisibilityDao;

public interface ActionDetailDataAccess {
    ActionDetailsAndVisibilityDao getActionDetailsAndVisibilityDao(ActionDao actionDao);

    ActionDetailsAndVisibilityDao getActionDetailsAndVisibilityDao(ActionDetailsAndVisibilityDao actionDetailsAndVisibilityDao);
}