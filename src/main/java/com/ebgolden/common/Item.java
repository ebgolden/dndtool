package com.ebgolden.common;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.tuple.Pair;

@SuperBuilder
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Item {
    String id;
    String playerId;
    Pair<Integer, Coin> cost;
    int weight;
}