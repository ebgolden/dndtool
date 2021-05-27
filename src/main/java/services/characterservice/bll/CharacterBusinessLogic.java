package services.characterservice.bll;

import services.characterservice.bll.bo.*;

public interface CharacterBusinessLogic {
    CharacterBo getCharacterBo(CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo);

    NonPlayableCharacterBo getNonPlayableCharacterBo(NonPlayableCharacterAndVisibilityAndDungeonMasterBo nonPlayableCharacterAndVisibilityAndDungeonMasterBo);

    NonPlayableCharacterBo getNonPlayableCharacterBo(CharacterAndDungeonMasterBo characterAndDungeonMasterBo);

    CharacterBo getCharacterBo(NonPlayableCharacterAndDungeonMasterBo nonPlayableCharacterAndDungeonMasterBo);

    CharacterAndVisibilityBo getCharacterAndVisibilityBo(CharacterAndPlayerBo characterAndPlayerBo);

    CharacterAndVisibilityBo getCharacterAndVisibilityBo(CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo);
}