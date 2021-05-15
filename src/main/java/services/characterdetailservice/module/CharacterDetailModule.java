package services.characterdetailservice.module;

import com.google.inject.AbstractModule;
import services.characterdetailservice.GetCharacterDetails;
import services.characterdetailservice.GetCharacterDetailsImpl;
import services.characterdetailservice.UpdateCharacterDetailsVisibility;
import services.characterdetailservice.UpdateCharacterDetailsVisibilityImpl;
import services.characterdetailservice.bll.CharacterDetailBusinessLogic;
import services.characterdetailservice.bll.CharacterDetailBusinessLogicConverter;
import services.characterdetailservice.bll.CharacterDetailBusinessLogicConverterImpl;
import services.characterdetailservice.bll.CharacterDetailBusinessLogicImpl;
import services.characterdetailservice.dal.CharacterDetailDataAccess;
import services.characterdetailservice.dal.CharacterDetailDataAccessConverter;
import services.characterdetailservice.dal.CharacterDetailDataAccessConverterImpl;
import services.characterdetailservice.dal.CharacterDetailDataAccessImpl;

public class CharacterDetailModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetCharacterDetails.class).to(GetCharacterDetailsImpl.class);
        bind(UpdateCharacterDetailsVisibility.class).to(UpdateCharacterDetailsVisibilityImpl.class);
        bind(CharacterDetailBusinessLogicConverter.class).to(CharacterDetailBusinessLogicConverterImpl.class);
        bind(CharacterDetailBusinessLogic.class).to(CharacterDetailBusinessLogicImpl.class);
        bind(CharacterDetailDataAccessConverter.class).to(CharacterDetailDataAccessConverterImpl.class);
        bind(CharacterDetailDataAccess.class).to(CharacterDetailDataAccessImpl.class);
    }
}