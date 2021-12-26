package com.ebgolden.domain.resultservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Player;
import com.ebgolden.common.Result;

@Builder
@Value
public class UpdatedResultRequest {
    Result result;
    Player player;
}