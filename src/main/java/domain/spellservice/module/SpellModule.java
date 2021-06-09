package domain.spellservice.module;

import com.google.inject.AbstractModule;
import domain.spellservice.GetUpdatedSpell;
import domain.spellservice.GetUpdatedSpellImpl;
import domain.spellservice.ChangeVisibilityOfSpellDetails;
import domain.spellservice.ChangeVisibilityOfSpellDetailsImpl;
import domain.spellservice.bll.SpellBusinessLogic;
import domain.spellservice.bll.SpellBusinessLogicConverter;
import domain.spellservice.bll.SpellBusinessLogicConverterImpl;
import domain.spellservice.bll.SpellBusinessLogicImpl;
import domain.spellservice.dal.SpellDataAccess;
import domain.spellservice.dal.SpellDataAccessConverter;
import domain.spellservice.dal.SpellDataAccessConverterImpl;
import domain.spellservice.dal.SpellDataAccessImpl;

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