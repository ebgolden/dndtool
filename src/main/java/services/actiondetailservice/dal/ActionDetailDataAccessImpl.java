package services.actiondetailservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import objects.DataOperator;
import services.actiondetailservice.dal.dao.ActionDao;
import services.actiondetailservice.dal.dao.ActionDetailsAndVisibilityDao;

public class ActionDetailDataAccessImpl implements ActionDetailDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private ActionDetailDataAccessConverter actionDetailDataAccessConverter;

    public ActionDetailsAndVisibilityDao getActionDetailsAndVisibilityDao(ActionDao actionDao) {
        String actionJson = actionDao.getActionJson();
        dataOperator.sendRequestJson(api, actionJson);
        String actionDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return actionDetailDataAccessConverter.getActionDetailsAndVisibilityDaoFromActionDetailsAndVisibilityJson(actionDetailsAndVisibilityJson);
    }

    public ActionDetailsAndVisibilityDao getActionDetailsAndVisibilityDao(ActionDetailsAndVisibilityDao actionDetailsAndVisibilityDao) {
        String actionAndVisibilityJson = actionDetailsAndVisibilityDao.getActionDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(api, actionAndVisibilityJson);
        String actionDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return actionDetailDataAccessConverter.getActionDetailsAndVisibilityDaoFromActionDetailsAndVisibilityJson(actionDetailsAndVisibilityJson);
    }
}