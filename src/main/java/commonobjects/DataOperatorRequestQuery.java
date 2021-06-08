package commonobjects;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class DataOperatorRequestQuery implements DataOperatorQuery {
    String campaignId;
    String playerId;
    String apiName;
    String queryType;
    String requestJson;
}