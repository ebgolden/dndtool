package services.resultservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Player;
import commonobjects.Result;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Value
public class ResultAndVisibilityAndPlayerBo {
    Result result;
    Map<String, Visibility> visibilityMap;
    Player player;
}