package services.dataoperatorservice.bll;

import com.google.inject.Inject;
import commonobjects.Campaign;
import commonobjects.CampaignStatus;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.bll.bo.*;
import services.dataoperatorservice.dal.DataOperatorDataAccess;
import services.dataoperatorservice.dal.DataOperatorDataAccessConverter;
import services.dataoperatorservice.dal.dao.*;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DataOperatorBusinessLogicImpl implements DataOperatorBusinessLogic {
    @Inject
    private DataOperatorDataAccessConverter dataOperatorDataAccessConverter;
    @Inject
    private DataOperatorDataAccess dataOperatorDataAccess;

    public QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBo(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo) {
        CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo filteredCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo = filterCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);
        CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao = dataOperatorDataAccessConverter.getCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDaoFromCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(filteredCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);
        QueryIdAndResponseJsonDao queryIdAndResponseJsonDao = dataOperatorDataAccess.getQueryIdAndResponseJsonDao(campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao);
        return dataOperatorDataAccessConverter.getQueryIdAndResponseJsonBoFromQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
    }

    public QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBo(QueryIdAndResponseJsonBo queryIdAndResponseJsonBo) {
        QueryIdAndResponseJsonDao queryIdAndResponseJsonDao = dataOperatorDataAccessConverter.getQueryIdAndResponseJsonDaoFromQueryIdAndResponseJsonBo(queryIdAndResponseJsonBo);
        queryIdAndResponseJsonDao = dataOperatorDataAccess.getQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
        return dataOperatorDataAccessConverter.getQueryIdAndResponseJsonBoFromQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
    }

    public PortCampaignMapBo getPortCampaignMapBo(PlayerBo playerBo) {
        Player player = playerBo.getPlayer();
        PlayerIdDao playerIdDao = dataOperatorDataAccessConverter.getPlayerIdDaoFromPlayerBo(playerBo);
        PortCampaignMapDao portCampaignMapDao = dataOperatorDataAccess.getPortCampaignMapDao(playerIdDao);
        PortCampaignMapBo portCampaignMapBo = dataOperatorDataAccessConverter.getPortCampaignMapBoFromPortCampaignMapDao(portCampaignMapDao);
        return filterPortCampaignMapBo(portCampaignMapBo, player);
    }

    public CampaignBo getCampaignBo(PlayerBo playerBo) {
        PlayerIdDao playerIdDao = dataOperatorDataAccessConverter.getPlayerIdDaoFromPlayerBo(playerBo);
        CampaignDao campaignDao = dataOperatorDataAccess.getCampaignDao(playerIdDao);
        return dataOperatorDataAccessConverter.getCampaignBoFromCampaignDao(campaignDao);
    }

    public PortBo getPortBo(DungeonMasterBo dungeonMasterBo) {
        DungeonMasterIdDao dungeonMasterIdDao = dataOperatorDataAccessConverter.getDungeonMasterIdDaoFromDungeonMasterBo(dungeonMasterBo);
        PortDao portDao = dataOperatorDataAccess.getPortDao(dungeonMasterIdDao);
        return dataOperatorDataAccessConverter.getPortBoFromPortDao(portDao);
    }

    private CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo filterCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo) {
        String campaignId = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getCampaignId();
        String playerId = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getPlayerId();
        String apiName = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getApiName();
        QueryType queryType = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getQueryType();
        String requestJson = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getRequestJson();
        String[] apiNameParts = apiName.split("\\.");
        int indexOfLastApiNamePart = apiNameParts.length - 1;
        String filteredApiName = apiNameParts[indexOfLastApiNamePart];
        return CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo
                .builder()
                .campaignId(campaignId)
                .playerId(playerId)
                .apiName(filteredApiName)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }

    private PortCampaignMapBo filterPortCampaignMapBo(PortCampaignMapBo portCampaignMapBo, Player player) {
        String playerId = player.getId();
        Map<Integer, Campaign> portCampaignMap = portCampaignMapBo.getPortCampaignMap();
        Map<Integer, Campaign> filteredPortCampaignMap = portCampaignMap
                .entrySet()
                .stream()
                .filter(map -> ((map.getValue().getCampaignStatus() == CampaignStatus.SETUP) ||
                        Arrays.stream(map.getValue().getPlayers())
                                .anyMatch(p -> (p.getId().equals(playerId)))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return PortCampaignMapBo
                .builder()
                .portCampaignMap(filteredPortCampaignMap)
                .build();
    }
}