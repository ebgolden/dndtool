package objects;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Party {
    Encounter currentEncounter;
}