package domain.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.DungeonMaster;
import common.NonPlayableCharacter;

@Builder
@Value
public class NonPlayableCharacterAndDungeonMasterBo {
    NonPlayableCharacter nonPlayableCharacter;
    DungeonMaster dungeonMaster;
}