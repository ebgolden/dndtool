package services.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.DungeonMaster;
import commonobjects.NonPlayableCharacter;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Value
public class NonPlayableCharacterAndVisibilityAndDungeonMasterBo {
    NonPlayableCharacter nonPlayableCharacter;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}