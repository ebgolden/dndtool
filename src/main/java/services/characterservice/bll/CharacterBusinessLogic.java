package services.characterservice.bll;

import services.characterservice.bll.bo.*;

public interface CharacterBusinessLogic {
    CharacterBo getCharacterBo(CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo);

    NonPlayableCharacterBo getNonPlayableCharacterBo(NonPlayableCharacterAndVisibilityAndDungeonMasterBo nonPlayableCharacterAndVisibilityAndDungeonMasterBo);

    NonPlayableCharacterBo getNonPlayableCharacterBo(CharacterAndDungeonMasterBo characterAndDungeonMasterBo);
}