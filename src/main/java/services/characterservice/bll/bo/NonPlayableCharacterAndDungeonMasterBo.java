package services.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.DungeonMaster;
import objects.NonPlayableCharacter;

@Builder
@Value
public class NonPlayableCharacterAndDungeonMasterBo {
    NonPlayableCharacter nonPlayableCharacter;
    DungeonMaster dungeonMaster;
}