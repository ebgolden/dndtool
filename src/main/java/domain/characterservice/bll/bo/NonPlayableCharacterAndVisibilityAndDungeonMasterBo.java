package domain.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.DungeonMaster;
import common.NonPlayableCharacter;
import common.Visibility;
import java.util.Map;

@Builder
@Value
public class NonPlayableCharacterAndVisibilityAndDungeonMasterBo {
    NonPlayableCharacter nonPlayableCharacter;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}