package services.partyservice.dal;

import services.partyservice.bll.bo.PartyAndCharacterAndPlayerAndAcceptedByPartyBo;
import services.partyservice.bll.bo.PartyBo;
import services.partyservice.bll.bo.PartyAndCharacterAndPlayerBo;
import services.partyservice.dal.dao.PartyAndCharacterDao;
import services.partyservice.dal.dao.PartyDao;

public interface PartyDataAccessConverter {
    PartyAndCharacterDao getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo);

    PartyAndCharacterDao getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerAndAcceptedByPartyBo(PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo);

    PartyBo getPartyBoFromPartyDao(PartyDao partyDao);

    PartyDao getPartyDaoFromUpdatedJsonObject(String updatedJsonObject);
}