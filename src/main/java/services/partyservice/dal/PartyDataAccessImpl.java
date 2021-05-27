package services.partyservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.partyservice.dal.dao.PartyAndCharacterDao;
import services.partyservice.dal.dao.PartyAndSplitPartiesDao;
import services.partyservice.dal.dao.PartyDao;
import services.partyservice.dal.dao.SplitPartiesDao;

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

    public SplitPartiesDao getSplitPartiesDao(PartyAndSplitPartiesDao partyAndSplitPartiesDao) {
        String partyAndSplitPartiesJson = partyAndSplitPartiesDao.getPartyAndSplitPartiesJson();
        dataOperator.sendRequestJson(partyAndSplitPartiesJson);
        String splitPartiesJson = dataOperator.getResponseJson();
        return partyDataAccessConverter.getSplitPartiesDaoFromSplitPartiesJson(splitPartiesJson);
    }
}