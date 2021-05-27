package services.spellservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import services.spellservice.GetUpdatedSpell;
import services.spellservice.GetUpdatedSpellImpl;
import services.spellservice.ChangeVisibilityOfSpellDetails;
import services.spellservice.ChangeVisibilityOfSpellDetailsImpl;
import services.spellservice.bll.SpellBusinessLogic;
import services.spellservice.bll.SpellBusinessLogicConverter;
import services.spellservice.bll.SpellBusinessLogicConverterImpl;
import services.spellservice.bll.SpellBusinessLogicImpl;
import services.spellservice.dal.SpellDataAccess;
import services.spellservice.dal.SpellDataAccessConverter;
import services.spellservice.dal.SpellDataAccessConverterImpl;
import services.spellservice.dal.SpellDataAccessImpl;

public class SpellModule extends AbstractModule {
    private final Object API;

    public SpellModule(Object api) {
        API = api;
    }

    @Override
    protected void configure() {
        bind(GetUpdatedSpell.class).to(GetUpdatedSpellImpl.class);
        bind(ChangeVisibilityOfSpellDetails.class).to(ChangeVisibilityOfSpellDetailsImpl.class);
        bind(SpellBusinessLogicConverter.class).to(SpellBusinessLogicConverterImpl.class);
        bind(SpellBusinessLogic.class).to(SpellBusinessLogicImpl.class);
        bind(SpellDataAccessConverter.class).to(SpellDataAccessConverterImpl.class);
        bind(SpellDataAccess.class).to(SpellDataAccessImpl.class);
    }

    @Provides
    @Named("api")
    public Object provideAPIClass() {
        return API;
    }
}