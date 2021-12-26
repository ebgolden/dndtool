package domain.diceservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DiceDao {
    String diceJson;
}