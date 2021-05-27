package services.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Character;
import commonobjects.DungeonMaster;

@Builder
@Value
public class CharacterAndDungeonMasterBo {
    Character character;
    DungeonMaster dungeonMaster;
}