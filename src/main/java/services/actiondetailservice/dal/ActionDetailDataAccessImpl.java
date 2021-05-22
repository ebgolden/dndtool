package services.actiondetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.actiondetailservice.dal.dao.ActionDao;
import services.actiondetailservice.dal.dao.ActionDetailsAndVisibilityDao;

public class ActionDetailDataAccessImpl implements ActionDetailDataAccess {
    @Inject
    private ActionDetailDataAccessConverter actionDetailDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public ActionDetailsAndVisibilityDao getActionDetailsAndVisibilityDao(ActionDao actionDao) {
        String actionJson = actionDao.getActionJson();
        dataOperator.sendRequestJson(actionJson);
        String latestJsonObject = dataOperator.getResponseJson();
        return actionDetailDataAccessConverter.getActionDetailsAndVisibilityDaoFromLatestJsonObject(latestJsonObject);
    }

    public ActionDetailsAndVisibilityDao getActionDetailsAndVisibilityDao(ActionDetailsAndVisibilityDao actionDetailsAndVisibilityDao) {
        String actionAndVisibilityJson = actionDetailsAndVisibilityDao.getActionDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(actionAndVisibilityJson);
        String updatedJsonObject = dataOperator.getResponseJson();
        return actionDetailDataAccessConverter.getActionDetailsAndVisibilityDaoFromLatestJsonObject(updatedJsonObject);
    }
}