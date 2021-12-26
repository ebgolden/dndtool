package com.ebgolden.domain.spellservice.module;

import com.google.inject.AbstractModule;
import com.ebgolden.domain.spellservice.GetUpdatedSpell;
import com.ebgolden.domain.spellservice.GetUpdatedSpellImpl;
import com.ebgolden.domain.spellservice.ChangeVisibilityOfSpellDetails;
import com.ebgolden.domain.spellservice.ChangeVisibilityOfSpellDetailsImpl;
import com.ebgolden.domain.spellservice.bll.SpellBusinessLogic;
import com.ebgolden.domain.spellservice.bll.SpellBusinessLogicConverter;
import com.ebgolden.domain.spellservice.bll.SpellBusinessLogicConverterImpl;
import com.ebgolden.domain.spellservice.bll.SpellBusinessLogicImpl;
import com.ebgolden.domain.spellservice.dal.SpellDataAccess;
import com.ebgolden.domain.spellservice.dal.SpellDataAccessConverter;
import com.ebgolden.domain.spellservice.dal.SpellDataAccessConverterImpl;
import com.ebgolden.domain.spellservice.dal.SpellDataAccessImpl;

public class SpellModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetUpdatedSpell.class).to(GetUpdatedSpellImpl.class);
        bind(ChangeVisibilityOfSpellDetails.class).to(ChangeVisibilityOfSpellDetailsImpl.class);
        bind(SpellBusinessLogicConverter.class).to(SpellBusinessLogicConverterImpl.class);
        bind(SpellBusinessLogic.class).to(SpellBusinessLogicImpl.class);
        bind(SpellDataAccessConverter.class).to(SpellDataAccessConverterImpl.class);
        bind(SpellDataAccess.class).to(SpellDataAccessImpl.class);
    }
}