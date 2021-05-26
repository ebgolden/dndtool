package services.partyservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.partyservice.dal.dao.PartyAndCharacterDao;
import services.partyservice.dal.dao.PartyDao;

public class PartyDataAccessImpl implements PartyDataAccess {
    @Inject
    private DataOperator dataOperator;
    @Inject
    private PartyDataAccessConverter partyDataAccessConverter;

    public PartyDao getPartyDao(PartyAndCharacterDao partyAndCharacterDao) {
        String partyAndCharacterJson = partyAndCharacterDao.getPartyAndCharacterJson();
        dataOperator.sendRequestJson(partyAndCharacterJson);
        String partyJson = dataOperator.getResponseJson();
        return partyDataAccessConverter.getPartyDaoFromPartyJson(partyJson);
    }
}