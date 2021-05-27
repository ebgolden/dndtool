package services.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.DungeonMaster;
import commonobjects.NonPlayableCharacter;

@Builder
@Value
public class NonPlayableCharacterAndDungeonMasterBo {
    NonPlayableCharacter nonPlayableCharacter;
    DungeonMaster dungeonMaster;
}