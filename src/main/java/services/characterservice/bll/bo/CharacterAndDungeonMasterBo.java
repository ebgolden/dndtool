package services.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Character;
import objects.DungeonMaster;

@Builder
@Value
public class CharacterAndDungeonMasterBo {
    Character character;
    DungeonMaster dungeonMaster;
}