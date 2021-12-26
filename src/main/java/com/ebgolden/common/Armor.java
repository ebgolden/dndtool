package common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.tuple.Pair;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class Armor extends Item {
    String armorType;
    int baseArmorClass;
    int strength;
    Advantage stealth;
    Pair<Integer, Time> don;
    Pair<Integer, Time> doff;
}