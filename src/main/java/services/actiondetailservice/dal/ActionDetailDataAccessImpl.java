package services.actiondetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.actiondetailservice.dal.dao.ActionDao;
import services.actiondetailservice.dal.dao.ActionDetailsAndVisibilityDao;

public class ActionDetailDataAccessImpl implements ActionDetailDataAccess {
    @Inject
    private DataOperator dataOperator;
    @Inject
    private ActionDetailDataAccessConverter actionDetailDataAccessConverter;

    public ActionDetailsAndVisibilityDao getActionDetailsAndVisibilityDao(ActionDao actionDao) {
        String actionJson = actionDao.getActionJson();
        dataOperator.sendRequestJson(actionJson);
        String actionDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return actionDetailDataAccessConverter.getActionDetailsAndVisibilityDaoFromActionDetailsAndVisibilityJson(actionDetailsAndVisibilityJson);
    }

    public ActionDetailsAndVisibilityDao getActionDetailsAndVisibilityDao(ActionDetailsAndVisibilityDao actionDetailsAndVisibilityDao) {
        String actionAndVisibilityJson = actionDetailsAndVisibilityDao.getActionDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(actionAndVisibilityJson);
        String actionDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return actionDetailDataAccessConverter.getActionDetailsAndVisibilityDaoFromActionDetailsAndVisibilityJson(actionDetailsAndVisibilityJson);
    }
}