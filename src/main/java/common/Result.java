package common;

import lombok.*;

@Builder
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Result {
    String id;
    String playerId;
}