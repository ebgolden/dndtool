package services.characterservice.module;

import com.google.inject.AbstractModule;
import services.characterservice.CreateCharacter;
import services.characterservice.CreateCharacterImpl;
import services.characterservice.CreateNonPlayableCharacter;
import services.characterservice.CreateNonPlayableCharacterImpl;
import services.characterservice.bll.CharacterBusinessLogic;
import services.characterservice.bll.CharacterBusinessLogicConverter;
import services.characterservice.bll.CharacterBusinessLogicConverterImpl;
import services.characterservice.bll.CharacterBusinessLogicImpl;
import services.characterservice.dal.CharacterDataAccess;
import services.characterservice.dal.CharacterDataAccessConverter;
import services.characterservice.dal.CharacterDataAccessConverterImpl;
import services.characterservice.dal.CharacterDataAccessImpl;

public class CharacterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CreateCharacter.class).to(CreateCharacterImpl.class);
        bind(CreateNonPlayableCharacter.class).to(CreateNonPlayableCharacterImpl.class);
        bind(CharacterBusinessLogicConverter.class).to(CharacterBusinessLogicConverterImpl.class);
        bind(CharacterBusinessLogic.class).to(CharacterBusinessLogicImpl.class);
        bind(CharacterDataAccessConverter.class).to(CharacterDataAccessConverterImpl.class);
        bind(CharacterDataAccess.class).to(CharacterDataAccessImpl.class);
    }
}