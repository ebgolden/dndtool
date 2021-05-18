package services.partyservice.module;

import com.google.inject.AbstractModule;
import services.partyservice.LeaveParty;
import services.partyservice.LeavePartyImpl;
import services.partyservice.bll.PartyBusinessLogic;
import services.partyservice.bll.PartyBusinessLogicConverter;
import services.partyservice.bll.PartyBusinessLogicConverterImpl;
import services.partyservice.bll.PartyBusinessLogicImpl;
import services.partyservice.dal.PartyDataAccess;
import services.partyservice.dal.PartyDataAccessConverter;
import services.partyservice.dal.PartyDataAccessConverterImpl;
import services.partyservice.dal.PartyDataAccessImpl;

public class PartyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LeaveParty.class).to(LeavePartyImpl.class);
        bind(PartyBusinessLogicConverter.class).to(PartyBusinessLogicConverterImpl.class);
        bind(PartyBusinessLogic.class).to(PartyBusinessLogicImpl.class);
        bind(PartyDataAccessConverter.class).to(PartyDataAccessConverterImpl.class);
        bind(PartyDataAccess.class).to(PartyDataAccessImpl.class);
    }
}