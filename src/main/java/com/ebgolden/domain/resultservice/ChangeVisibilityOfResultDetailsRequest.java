package com.ebgolden.domain.resultservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Player;
import com.ebgolden.common.Result;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfResultDetailsRequest {
    Result result;
    Map<String, Visibility> visibilityMap;
    Player player;
}