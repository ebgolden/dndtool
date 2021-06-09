package domain.resultservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Player;
import common.Result;
import common.Visibility;
import java.util.Map;

@Builder
@Value
public class ResultAndVisibilityAndPlayerBo {
    Result result;
    Map<String, Visibility> visibilityMap;
    Player player;
}