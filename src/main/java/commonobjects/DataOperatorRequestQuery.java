package commonobjects;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataOperatorRequestQuery implements DataOperatorQuery {
    String campaignId;
    String senderPlayerId;
    String apiName;
    String queryType;
    String requestJson;
}