package com.ebgolden.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Advantage {
    @JsonProperty("advantage")
    ADVANTAGE("advantage"),
    @JsonProperty("neutral")
    NEUTRAL("neutral"),
    @JsonProperty("disadvantage")
    DISADVANTAGE("disadvantage");

    @Getter
    private final String advantage;
}