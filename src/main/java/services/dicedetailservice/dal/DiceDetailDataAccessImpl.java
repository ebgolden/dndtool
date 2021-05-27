package services.dicedetailservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import objects.DataOperator;
import services.dicedetailservice.dal.dao.DiceAndPlayerDao;
import services.dicedetailservice.dal.dao.DiceDetailsDao;

public class DiceDetailDataAccessImpl implements DiceDetailDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private DiceDetailDataAccessConverter diceDetailDataAccessConverter;

    public DiceDetailsDao getDiceDetailsDao(DiceAndPlayerDao diceAndPlayerDao) {
        String diceAndPlayerJson = diceAndPlayerDao.getDiceAndPlayerJson();
        dataOperator.sendRequestJson(api, diceAndPlayerJson);
        String diceDetailsJson = dataOperator.getResponseJson();
        return diceDetailDataAccessConverter.getDiceDetailsDaoFromDiceDetailsJson(diceDetailsJson);
    }
}