package services.dataoperatorservice.bll.bo;

import commonobjects.Player;
import commonobjects.QueryType;
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