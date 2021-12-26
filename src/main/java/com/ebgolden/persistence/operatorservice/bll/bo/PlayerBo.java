package com.ebgolden.persistence.operatorservice.bll.bo;

import com.ebgolden.common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PlayerBo {
    Player player;
}