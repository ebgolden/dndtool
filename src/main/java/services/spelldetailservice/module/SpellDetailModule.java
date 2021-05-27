package services.spelldetailservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import services.spelldetailservice.GetSpellDetails;
import services.spelldetailservice.GetSpellDetailsImpl;
import services.spelldetailservice.UpdateSpellDetailsVisibility;
import services.spelldetailservice.UpdateSpellDetailsVisibilityImpl;
import services.spelldetailservice.bll.SpellDetailBusinessLogic;
import services.spelldetailservice.bll.SpellDetailBusinessLogicConverter;
import services.spelldetailservice.bll.SpellDetailBusinessLogicConverterImpl;
import services.spelldetailservice.bll.SpellDetailBusinessLogicImpl;
import services.spelldetailservice.dal.SpellDetailDataAccess;
import services.spelldetailservice.dal.SpellDetailDataAccessConverter;
import services.spelldetailservice.dal.SpellDetailDataAccessConverterImpl;
import services.spelldetailservice.dal.SpellDetailDataAccessImpl;

public class SpellDetailModule extends AbstractModule {
    private final Object API;

    public SpellDetailModule(Object api) {
        API = api;
    }

    @Override
    protected void configure() {
        bind(GetSpellDetails.class).to(GetSpellDetailsImpl.class);
        bind(UpdateSpellDetailsVisibility.class).to(UpdateSpellDetailsVisibilityImpl.class);
        bind(SpellDetailBusinessLogicConverter.class).to(SpellDetailBusinessLogicConverterImpl.class);
        bind(SpellDetailBusinessLogic.class).to(SpellDetailBusinessLogicImpl.class);
        bind(SpellDetailDataAccessConverter.class).to(SpellDetailDataAccessConverterImpl.class);
        bind(SpellDetailDataAccess.class).to(SpellDetailDataAccessImpl.class);
    }

    @Provides
    @Named("api")
    public Object provideAPIClass() {
        return API;
    }
}