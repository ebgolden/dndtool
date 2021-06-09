package domain.characterservice.module;

import com.google.inject.AbstractModule;
import domain.characterservice.*;
import domain.characterservice.bll.CharacterBusinessLogic;
import domain.characterservice.bll.CharacterBusinessLogicConverter;
import domain.characterservice.bll.CharacterBusinessLogicConverterImpl;
import domain.characterservice.bll.CharacterBusinessLogicImpl;
import domain.characterservice.dal.CharacterDataAccess;
import domain.characterservice.dal.CharacterDataAccessConverter;
import domain.characterservice.dal.CharacterDataAccessConverterImpl;
import domain.characterservice.dal.CharacterDataAccessImpl;

public class CharacterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CreateCharacter.class).to(CreateCharacterImpl.class);
        bind(CreateNonPlayableCharacter.class).to(CreateNonPlayableCharacterImpl.class);
        bind(ChangeCharacterToNonPlayableCharacter.class).to(ChangeCharacterToNonPlayableCharacterImpl.class);
        bind(ChangeNonPlayableCharacterToCharacter.class).to(ChangeNonPlayableCharacterToCharacterImpl.class);
        bind(GetUpdatedCharacter.class).to(GetUpdatedCharacterImpl.class);
        bind(ChangeVisibilityOfCharacterDetails.class).to(ChangeVisibilityOfCharacterDetailsImpl.class);
        bind(CharacterBusinessLogicConverter.class).to(CharacterBusinessLogicConverterImpl.class);
        bind(CharacterBusinessLogic.class).to(CharacterBusinessLogicImpl.class);
        bind(CharacterDataAccessConverter.class).to(CharacterDataAccessConverterImpl.class);
        bind(CharacterDataAccess.class).to(CharacterDataAccessImpl.class);
    }
}