package commonobjects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class DataOperatorRequestWithQueryIdQuery extends DataOperatorRequestQuery {
    String queryId;
}
