package com.ebgolden.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Visibility {
    @JsonProperty("dungeon_master")
    DUNGEON_MASTER("dungeon_master"),
    @JsonProperty("player")
    PLAYER("player"),
    @JsonProperty("everyone")
    EVERYONE("everyone");

    @Getter
    private final String visibilityLevel;
}