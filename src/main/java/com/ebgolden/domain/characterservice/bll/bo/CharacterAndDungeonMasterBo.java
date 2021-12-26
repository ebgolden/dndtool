package domain.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Character;
import common.DungeonMaster;

@Builder
@Value
public class CharacterAndDungeonMasterBo {
    Character character;
    DungeonMaster dungeonMaster;
}