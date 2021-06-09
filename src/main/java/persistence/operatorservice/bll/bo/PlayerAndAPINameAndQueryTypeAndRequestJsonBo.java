package persistence.operatorservice.bll.bo;

import common.Player;
import common.QueryType;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PlayerAndAPINameAndQueryTypeAndRequestJsonBo {
    Player player;
    String apiName;
    QueryType queryType;
    String requestJson;
}