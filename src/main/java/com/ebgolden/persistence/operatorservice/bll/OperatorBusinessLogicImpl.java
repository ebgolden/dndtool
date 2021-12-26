package com.ebgolden.persistence.operatorservice.bll;

import com.google.inject.Inject;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.CampaignStatus;
import com.ebgolden.common.Player;
import com.ebgolden.common.QueryType;
import com.ebgolden.persistence.operatorservice.bll.bo.*;
import com.ebgolden.persistence.operatorservice.dal.dao.*;
import com.ebgolden.persistence.operatorservice.dal.OperatorDataAccess;
import com.ebgolden.persistence.operatorservice.dal.OperatorDataAccessConverter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class OperatorBusinessLogicImpl implements OperatorBusinessLogic {
    @Inject
    private OperatorDataAccessConverter operatorDataAccessConverter;
    @Inject
    private OperatorDataAccess operatorDataAccess;

    public QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBo(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo) {
        CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo filteredCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo = filterCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);
        CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao = operatorDataAccessConverter.getCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDaoFromCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(filteredCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);
        QueryIdAndResponseJsonDao queryIdAndResponseJsonDao = operatorDataAccess.getQueryIdAndResponseJsonDao(campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao);
        return operatorDataAccessConverter.getQueryIdAndResponseJsonBoFromQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
    }

    public QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBo(QueryIdAndResponseJsonBo queryIdAndResponseJsonBo) {
        QueryIdAndResponseJsonDao queryIdAndResponseJsonDao = operatorDataAccessConverter.getQueryIdAndResponseJsonDaoFromQueryIdAndResponseJsonBo(queryIdAndResponseJsonBo);
        queryIdAndResponseJsonDao = operatorDataAccess.getQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
        return operatorDataAccessConverter.getQueryIdAndResponseJsonBoFromQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
    }

    public PortCampaignMapBo getPortCampaignMapBo(PlayerBo playerBo) {
        Player player = playerBo.getPlayer();
        PlayerIdDao playerIdDao = operatorDataAccessConverter.getPlayerIdDaoFromPlayerBo(playerBo);
        PortCampaignMapDao portCampaignMapDao = operatorDataAccess.getPortCampaignMapDao(playerIdDao);
        PortCampaignMapBo portCampaignMapBo = operatorDataAccessConverter.getPortCampaignMapBoFromPortCampaignMapDao(portCampaignMapDao);
        return filterPortCampaignMapBo(portCampaignMapBo, player);
    }

    public CampaignBo getCampaignBo(PlayerBo playerBo) {
        PlayerIdDao playerIdDao = operatorDataAccessConverter.getPlayerIdDaoFromPlayerBo(playerBo);
        CampaignDao campaignDao = operatorDataAccess.getCampaignDao(playerIdDao);
        return operatorDataAccessConverter.getCampaignBoFromCampaignDao(campaignDao);
    }

    public PortBo getPortBo(DungeonMasterBo dungeonMasterBo) {
        DungeonMasterIdDao dungeonMasterIdDao = operatorDataAccessConverter.getDungeonMasterIdDaoFromDungeonMasterBo(dungeonMasterBo);
        PortDao portDao = operatorDataAccess.getPortDao(dungeonMasterIdDao);
        return operatorDataAccessConverter.getPortBoFromPortDao(portDao);
    }

    public IPAddressBo getIPAddressBo(PlayerBo playerBo) {
        PlayerIdDao playerIdDao = operatorDataAccessConverter.getPlayerIdDaoFromPlayerBo(playerBo);
        IPAddressDao ipAddressDao = operatorDataAccess.getIPAddressDao(playerIdDao);
        return operatorDataAccessConverter.getIPAddressBoFromIPAddressDao(ipAddressDao);
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