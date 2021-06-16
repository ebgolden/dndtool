package common;

import lombok.*;
import org.json.simple.JSONArray;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CharacterClass {
    String id;
    Die hitDie;
    int baseHitPoints;
    int averageHitPointsPerLevel;
    String[] proficientArmor;
    String[] proficientWeapons;
    String[] proficientTools;
    String[] savingThrows;
    String[] skills;
    String[] equipment;
    int[] proficiencyBonusPerLevel;
    String[] featuresPerLevel;
    Map<String, Object[]> featuresPerLevelMap;
    Map<String, JSONArray> featureMap;
}