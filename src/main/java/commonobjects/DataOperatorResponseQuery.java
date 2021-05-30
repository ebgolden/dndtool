package commonobjects;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataOperatorResponseQuery implements DataOperatorQuery {
    String queryId;
    String responseJson;
}