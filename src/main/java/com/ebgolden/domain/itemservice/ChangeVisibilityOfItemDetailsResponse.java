package com.ebgolden.domain.itemservice;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Data
public class ChangeVisibilityOfItemDetailsResponse {
    Map<String, Visibility> visibilityMap;
}