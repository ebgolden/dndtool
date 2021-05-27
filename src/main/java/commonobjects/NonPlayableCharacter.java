package commonobjects;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class NonPlayableCharacter extends Character {
}