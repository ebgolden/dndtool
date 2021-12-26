package com.ebgolden.application.characterclassreaderservice;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CharacterClassFromResourceRequest {
    String characterClassName;
}