package com.ebgolden.domain.spellservice;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Data
public class ChangeVisibilityOfSpellDetailsResponse {
    Map<String, Visibility> visibilityMap;
}