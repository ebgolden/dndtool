package com.ebgolden.domain.resultservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Player;
import com.ebgolden.common.Result;

@Builder
@Value
public class ResultAndPlayerBo {
    Result result;
    Player player;
}