package services.actiondetailservice.bll;

import objects.Action;
import objects.Player;
import objects.Visibility;
import services.actiondetailservice.ActionDetailsRequest;
import services.actiondetailservice.ActionDetailsResponse;
import services.actiondetailservice.ActionDetailsVisibilityRequest;
import services.actiondetailservice.ActionDetailsVisibilityResponse;
import services.actiondetailservice.bll.bo.ActionAndPlayerBo;
import services.actiondetailservice.bll.bo.ActionDetailsAndVisibilityAndPlayerBo;
import services.actiondetailservice.bll.bo.ActionDetailsAndVisibilityBo;
import java.util.Map;

public class ActionDetailBusinessLogicConverterImpl implements ActionDetailBusinessLogicConverter {
    public ActionAndPlayerBo getActionAndPlayerBoFromActionDetailsRequest(ActionDetailsRequest actionDetailsRequest) {
        Action action = actionDetailsRequest.getAction();
        Player player = actionDetailsRequest.getPlayer();
        return ActionAndPlayerBo
                .builder()
                .action(action)
                .player(player)
                .build();
    }

    public ActionDetailsAndVisibilityAndPlayerBo getActionDetailsAndVisibilityAndPlayerBoFromActionDetailsVisibilityRequest(ActionDetailsVisibilityRequest actionDetailsVisibilityRequest) {
        Action action = actionDetailsVisibilityRequest.getAction();
        Map<String, Visibility> visibilityMap = actionDetailsVisibilityRequest.getVisibilityMap();
        Player player = actionDetailsVisibilityRequest.getPlayer();
        return ActionDetailsAndVisibilityAndPlayerBo
                .builder()
                .action(action)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public ActionDetailsResponse getActionDetailsResponseFromActionDetailsAndVisibilityBo(ActionDetailsAndVisibilityBo actionDetailsAndVisibilityBo) {
        Action action = actionDetailsAndVisibilityBo.getAction();
        return ActionDetailsResponse
                .builder()
                .action(action)
                .build();
    }

    public ActionDetailsVisibilityResponse getActionDetailsVisibilityResponseFromActionDetailsAndVisibilityBo(ActionDetailsAndVisibilityBo actionDetailsAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = actionDetailsAndVisibilityBo.getVisibilityMap();
        return ActionDetailsVisibilityResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public ActionDetailsAndVisibilityBo getActionDetailsAndVisibilityBoFromActionDetailsAndVisibilityAndPlayerBo(ActionDetailsAndVisibilityAndPlayerBo actionDetailsAndVisibilityAndPlayerBo) {
        Action action = actionDetailsAndVisibilityAndPlayerBo.getAction();
        Map<String, Visibility> visibilityMap = actionDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        return ActionDetailsAndVisibilityBo
                .builder()
                .action(action)
                .visibilityMap(visibilityMap)
                .build();
    }
}