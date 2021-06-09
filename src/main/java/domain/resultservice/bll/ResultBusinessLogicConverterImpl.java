package domain.resultservice.bll;

import common.Player;
import common.Result;
import common.Visibility;
import domain.resultservice.ChangeVisibilityOfResultDetailsRequest;
import domain.resultservice.UpdatedResultRequest;
import domain.resultservice.UpdatedResultResponse;
import domain.resultservice.ChangeVisibilityOfResultDetailsResponse;
import domain.resultservice.bll.bo.ResultAndPlayerBo;
import domain.resultservice.bll.bo.ResultAndVisibilityAndPlayerBo;
import domain.resultservice.bll.bo.ResultAndVisibilityBo;
import java.util.Map;

public class ResultBusinessLogicConverterImpl implements ResultBusinessLogicConverter {
    public ResultAndPlayerBo getResultAndPlayerBoFromUpdatedResultRequest(UpdatedResultRequest updatedResultRequest) {
        Result result = updatedResultRequest.getResult();
        Player player = updatedResultRequest.getPlayer();
        return ResultAndPlayerBo
                .builder()
                .result(result)
                .player(player)
                .build();
    }

    public ResultAndVisibilityAndPlayerBo getResultAndVisibilityAndPlayerBoFromChangeVisibilityOfResultDetailsRequest(ChangeVisibilityOfResultDetailsRequest changeVisibilityOfUpdatedResultRequest) {
        Result result = changeVisibilityOfUpdatedResultRequest.getResult();
        Map<String, Visibility> visibilityMap = changeVisibilityOfUpdatedResultRequest.getVisibilityMap();
        Player player = changeVisibilityOfUpdatedResultRequest.getPlayer();
        return ResultAndVisibilityAndPlayerBo
                .builder()
                .result(result)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public UpdatedResultResponse getUpdatedResultResponseFromResultAndVisibilityBo(ResultAndVisibilityBo resultAndVisibilityBo) {
        Result result = resultAndVisibilityBo.getResult();
        return UpdatedResultResponse
                .builder()
                .result(result)
                .build();
    }

    public ChangeVisibilityOfResultDetailsResponse getChangeVisibilityOfResultDetailsResponseFromResultAndVisibilityBo(ResultAndVisibilityBo resultAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = resultAndVisibilityBo.getVisibilityMap();
        return ChangeVisibilityOfResultDetailsResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public ResultAndVisibilityBo getResultAndVisibilityBoFromResultAndVisibilityAndPlayerBo(ResultAndVisibilityAndPlayerBo resultAndVisibilityAndPlayerBo) {
        Result result = resultAndVisibilityAndPlayerBo.getResult();
        Map<String, Visibility> visibilityMap = resultAndVisibilityAndPlayerBo.getVisibilityMap();
        return ResultAndVisibilityBo
                .builder()
                .result(result)
                .visibilityMap(visibilityMap)
                .build();
    }
}