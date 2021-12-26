package com.ebgolden.domain.resultservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Result;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Value
public class ResultAndVisibilityBo {
    Result result;
    Map<String, Visibility> visibilityMap;
}