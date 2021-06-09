package common;

import lombok.*;

@Builder
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Spell {
    String id;
    String playerId;
}