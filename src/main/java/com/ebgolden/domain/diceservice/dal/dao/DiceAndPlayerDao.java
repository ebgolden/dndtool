package com.ebgolden.domain.diceservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DiceAndPlayerDao {
    String diceAndPlayerJson;
}