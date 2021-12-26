package com.ebgolden.domain.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Character;
import com.ebgolden.common.DungeonMaster;

@Builder
@Value
public class CharacterAndDungeonMasterBo {
    Character character;
    DungeonMaster dungeonMaster;
}