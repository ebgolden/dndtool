package objects;

import lombok.*;

@Builder
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Party {
    Encounter currentEncounter;
    Character[] characters;
    Event[] history;
}