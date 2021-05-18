package services.partyservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.partyservice.dal.dao.PartyAndCharacterDao;
import services.partyservice.dal.dao.PartyDao;

public class PartyDataAccessImpl implements PartyDataAccess {
    @Inject
    private PartyDataAccessConverter partyDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public PartyDao getPartyDao(PartyAndCharacterDao partyAndCharacterDao) {
        String partyAndCharacterJson = partyAndCharacterDao.getPartyAndCharacterJson();
        dataOperator.sendRequestJson(partyAndCharacterJson);
        String updatedJsonObject = dataOperator.getResponseJson();
        return partyDataAccessConverter.getPartyDaoFromUpdatedJsonObject(updatedJsonObject);
    }
}