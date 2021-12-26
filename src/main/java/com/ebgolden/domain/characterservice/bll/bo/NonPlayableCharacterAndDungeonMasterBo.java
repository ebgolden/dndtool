package com.ebgolden.domain.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.NonPlayableCharacter;

@Builder
@Value
public class NonPlayableCharacterAndDungeonMasterBo {
    NonPlayableCharacter nonPlayableCharacter;
    DungeonMaster dungeonMaster;
}