package services.partyservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.partyservice.dal.dao.*;

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

    public PartyDao getPartyDao(PartiesDao partiesDao) {
        String partiesJson = partiesDao.getPartiesJson();
        dataOperator.sendRequestJson(partiesJson);
        String partyJson = dataOperator.getResponseJson();
        return partyDataAccessConverter.getPartyDaoFromPartyJson(partyJson);
    }
}