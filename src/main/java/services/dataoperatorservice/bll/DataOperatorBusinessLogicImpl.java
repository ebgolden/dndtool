package services.dataoperatorservice.bll;

import com.google.inject.Inject;
import commonobjects.Campaign;
import commonobjects.CampaignStatus;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.bll.bo.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo;
import services.dataoperatorservice.bll.bo.PlayerBo;
import services.dataoperatorservice.bll.bo.QueryIdAndResponseJsonBo;
import services.dataoperatorservice.bll.bo.ServerSocketCampaignMapBo;
import services.dataoperatorservice.dal.DataOperatorDataAccess;
import services.dataoperatorservice.dal.DataOperatorDataAccessConverter;
import services.dataoperatorservice.dal.dao.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao;
import services.dataoperatorservice.dal.dao.QueryIdAndResponseJsonDao;
import services.dataoperatorservice.dal.dao.ServerSocketCampaignMapDao;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DataOperatorBusinessLogicImpl implements DataOperatorBusinessLogic {
    @Inject
    private DataOperatorDataAccessConverter dataOperatorDataAccessConverter;
    @Inject
    private DataOperatorDataAccess dataOperatorDataAccess;

    public QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBo(CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo) {
        CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo filteredCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo = filterCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);
        CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao = dataOperatorDataAccessConverter.getCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDaoFromCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(filteredCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);
        QueryIdAndResponseJsonDao queryIdAndResponseJsonDao = dataOperatorDataAccess.getQueryIdAndResponseJsonDao(campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao);
        return dataOperatorDataAccessConverter.getQueryIdAndResponseJsonBoFromQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
    }

    public QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBo(QueryIdAndResponseJsonBo queryIdAndResponseJsonBo) {
        QueryIdAndResponseJsonDao queryIdAndResponseJsonDao = dataOperatorDataAccessConverter.getQueryIdAndResponseJsonDaoFromQueryIdAndResponseJsonBo(queryIdAndResponseJsonBo);
        queryIdAndResponseJsonDao = dataOperatorDataAccess.getQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
        return dataOperatorDataAccessConverter.getQueryIdAndResponseJsonBoFromQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
    }

    public ServerSocketCampaignMapBo getServerSocketCampaignMapBo(PlayerBo playerBo) {
        Player player = playerBo.getPlayer();
        ServerSocketCampaignMapDao serverSocketCampaignMapDao = dataOperatorDataAccess.getServerSocketCampaignMapDao();
        ServerSocketCampaignMapBo serverSocketCampaignMapBo = dataOperatorDataAccessConverter.getServerSocketCampaignMapBoFromServerSocketCampaignMapDao(serverSocketCampaignMapDao);
        return filterServerSocketCampaignMapBo(serverSocketCampaignMapBo, player);
    }

    private CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo filterCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo) {
        String campaignId = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getCampaignId();
        String senderPlayerId = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getSenderPlayerId();
        String apiName = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getApiName();
        QueryType queryType = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getQueryType();
        String requestJson = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getRequestJson();
        String[] apiNameParts = apiName.split("\\.");
        int indexOfLastApiNamePart = apiNameParts.length - 1;
        String filteredApiName = apiNameParts[indexOfLastApiNamePart];
        return CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo
                .builder()
                .campaignId(campaignId)
                .senderPlayerId(senderPlayerId)
                .apiName(filteredApiName)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }

    private ServerSocketCampaignMapBo filterServerSocketCampaignMapBo(ServerSocketCampaignMapBo serverSocketCampaignMapBo, Player player) {
        String playerId = player.getId();
        Map<ServerSocket, Campaign> serverSocketCampaignMap = serverSocketCampaignMapBo.getServerSocketCampaignMap();
        Map<ServerSocket, Campaign> filteredServerSocketCampaignMap = serverSocketCampaignMap
                .entrySet()
                .stream()
                .filter(map -> ((map.getValue().getCampaignStatus() == CampaignStatus.SETUP) ||
                        Arrays.stream(map.getValue().getPlayers())
                                .anyMatch(p -> (p.getId().equals(playerId)))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return ServerSocketCampaignMapBo
                .builder()
                .serverSocketCampaignMap(filteredServerSocketCampaignMap)
                .build();
    }
}