package com.ebgolden.domain.partyservice.module;

import com.google.inject.AbstractModule;
import com.ebgolden.domain.partyservice.*;
import com.ebgolden.domain.partyservice.bll.PartyBusinessLogic;
import com.ebgolden.domain.partyservice.bll.PartyBusinessLogicConverter;
import com.ebgolden.domain.partyservice.bll.PartyBusinessLogicConverterImpl;
import com.ebgolden.domain.partyservice.bll.PartyBusinessLogicImpl;
import com.ebgolden.domain.partyservice.dal.PartyDataAccess;
import com.ebgolden.domain.partyservice.dal.PartyDataAccessConverter;
import com.ebgolden.domain.partyservice.dal.PartyDataAccessConverterImpl;
import com.ebgolden.domain.partyservice.dal.PartyDataAccessImpl;

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