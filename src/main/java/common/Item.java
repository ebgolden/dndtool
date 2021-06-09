package common;

import lombok.*;

@Builder
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Item {
    String id;
    String playerId;
}