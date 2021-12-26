package com.ebgolden.domain.resultservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Player;
import com.ebgolden.common.Result;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Value
public class ResultAndVisibilityAndPlayerBo {
    Result result;
    Map<String, Visibility> visibilityMap;
    Player player;
}