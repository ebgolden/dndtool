package objects;

import lombok.*;

@Builder
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CharacterObject {
    String id;
    String playerId;
}
