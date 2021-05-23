package services.resultdetailservice.bll;

import objects.Player;
import objects.Result;
import objects.Visibility;
import services.resultdetailservice.ResultDetailsRequest;
import services.resultdetailservice.ResultDetailsResponse;
import services.resultdetailservice.ResultDetailsVisibilityRequest;
import services.resultdetailservice.ResultDetailsVisibilityResponse;
import services.resultdetailservice.bll.bo.ResultAndPlayerBo;
import services.resultdetailservice.bll.bo.ResultDetailsAndVisibilityAndPlayerBo;
import services.resultdetailservice.bll.bo.ResultDetailsAndVisibilityBo;
import java.util.Map;

public class ResultDetailBusinessLogicConverterImpl implements ResultDetailBusinessLogicConverter {
    public ResultAndPlayerBo getResultAndPlayerBoFromResultDetailsRequest(ResultDetailsRequest resultDetailsRequest) {
        Result result = resultDetailsRequest.getResult();
        Player player = resultDetailsRequest.getPlayer();
        return ResultAndPlayerBo
                .builder()
                .result(result)
                .player(player)
                .build();
    }

    public ResultDetailsAndVisibilityAndPlayerBo getResultDetailsAndVisibilityAndPlayerBoFromResultDetailsVisibilityRequest(ResultDetailsVisibilityRequest resultDetailsVisibilityRequest) {
        Result result = resultDetailsVisibilityRequest.getResult();
        Map<String, Visibility> visibilityMap = resultDetailsVisibilityRequest.getVisibilityMap();
        Player player = resultDetailsVisibilityRequest.getPlayer();
        return ResultDetailsAndVisibilityAndPlayerBo
                .builder()
                .result(result)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public ResultDetailsResponse getResultDetailsResponseFromResultDetailsAndVisibilityBo(ResultDetailsAndVisibilityBo resultDetailsAndVisibilityBo) {
        Result result = resultDetailsAndVisibilityBo.getResult();
        return ResultDetailsResponse
                .builder()
                .result(result)
                .build();
    }

    public ResultDetailsVisibilityResponse getResultDetailsVisibilityResponseFromResultDetailsAndVisibilityBo(ResultDetailsAndVisibilityBo resultDetailsAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = resultDetailsAndVisibilityBo.getVisibilityMap();
        return ResultDetailsVisibilityResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public ResultDetailsAndVisibilityBo getResultDetailsAndVisibilityBoFromResultDetailsAndVisibilityAndPlayerBo(ResultDetailsAndVisibilityAndPlayerBo resultDetailsAndVisibilityAndPlayerBo) {
        Result result = resultDetailsAndVisibilityAndPlayerBo.getResult();
        Map<String, Visibility> visibilityMap = resultDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        return ResultDetailsAndVisibilityBo
                .builder()
                .result(result)
                .visibilityMap(visibilityMap)
                .build();
    }
}