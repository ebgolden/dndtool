package domain.partyservice.module;

import com.google.inject.AbstractModule;
import domain.partyservice.*;
import domain.partyservice.bll.PartyBusinessLogic;
import domain.partyservice.bll.PartyBusinessLogicConverter;
import domain.partyservice.bll.PartyBusinessLogicConverterImpl;
import domain.partyservice.bll.PartyBusinessLogicImpl;
import domain.partyservice.dal.PartyDataAccess;
import domain.partyservice.dal.PartyDataAccessConverter;
import domain.partyservice.dal.PartyDataAccessConverterImpl;
import domain.partyservice.dal.PartyDataAccessImpl;

public class PartyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LeaveParty.class).to(LeavePartyImpl.class);
        bind(JoinParty.class).to(JoinPartyImpl.class);
        bind(SplitParty.class).to(SplitPartyImpl.class);
        bind(MergeParties.class).to(MergePartiesImpl.class);
        bind(PartyBusinessLogicConverter.class).to(PartyBusinessLogicConverterImpl.class);
        bind(PartyBusinessLogic.class).to(PartyBusinessLogicImpl.class);
        bind(PartyDataAccessConverter.class).to(PartyDataAccessConverterImpl.class);
        bind(PartyDataAccess.class).to(PartyDataAccessImpl.class);
    }
}