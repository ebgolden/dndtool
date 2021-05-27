package services.diceservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.DataOperator;
import services.diceservice.dal.dao.DiceAndPlayerDao;
import services.diceservice.dal.dao.DiceDao;

public class DiceDataAccessImpl implements DiceDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private DiceDataAccessConverter diceDataAccessConverter;

    public DiceDao getDiceDao(DiceAndPlayerDao diceAndPlayerDao) {
        String diceAndPlayerJson = diceAndPlayerDao.getDiceAndPlayerJson();
        dataOperator.sendRequestJson(api, diceAndPlayerJson);
        String diceJson = dataOperator.getResponseJson();
        return diceDataAccessConverter.getDiceDaoFromDiceJson(diceJson);
    }
}