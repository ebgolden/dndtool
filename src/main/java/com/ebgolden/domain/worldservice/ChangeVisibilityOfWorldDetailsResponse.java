package com.ebgolden.domain.worldservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfWorldDetailsResponse {
    Map<String, Visibility> visibilityMap;
}