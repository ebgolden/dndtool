package com.ebgolden.domain.locationservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfLocationDetailsResponse {
    Map<String, Visibility> visibilityMap;
}