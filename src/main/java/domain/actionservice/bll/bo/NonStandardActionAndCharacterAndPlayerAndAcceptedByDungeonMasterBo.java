package domain.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Character;
import common.NonStandardAction;
import common.Player;

@Builder
@Value
public class NonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo {
    NonStandardAction nonStandardAction;
    Character character;
    Player player;
    boolean acceptedByDungeonMaster;
}