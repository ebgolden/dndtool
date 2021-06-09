package domain.campaignservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import common.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import domain.campaignservice.bll.bo.*;
import domain.campaignservice.dal.CampaignDataAccess;
import domain.campaignservice.dal.CampaignDataAccessConverter;
import domain.campaignservice.dal.dao.CampaignAndPlayerDao;
import domain.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import domain.campaignservice.dal.dao.CampaignAndVisibilityDao;
import domain.campaignservice.dal.dao.CampaignDao;
import java.util.Map;

public class CampaignBusinessLogicImpl implements CampaignBusinessLogic {
    @Inject
    private CampaignDataAccessConverter campaignDataAccessConverter;
    @Inject
    private CampaignDataAccess campaignDataAccess;
    @Inject
    private CampaignBusinessLogicConverter campaignBusinessLogicConverter;

    public CampaignBo getCampaignBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo) {
        CampaignAndVisibilityAndDungeonMasterBo filteredCampaignAndVisibilityAndDungeonMasterBo = filterCampaignAndDungeonMasterBo(campaignAndVisibilityAndDungeonMasterBo);
        CampaignAndVisibilityAndDungeonMasterDao campaignAndVisibilityAndDungeonMasterDao = campaignDataAccessConverter.getCampaignAndVisibilityAndDungeonMasterDaoFromCampaignAndVisibilityAndDungeonMasterBo(filteredCampaignAndVisibilityAndDungeonMasterBo);
        CampaignDao campaignDao = campaignDataAccess.getCampaignDao(campaignAndVisibilityAndDungeonMasterDao);
        return campaignDataAccessConverter.getCampaignBoFromCampaignDao(campaignDao);
    }

    public CampaignAndVisibilityBo getCampaignAndVisibilityBo(CampaignAndPlayerBo campaignAndPlayerBo) {
        CampaignDao campaignDao = campaignDataAccessConverter.getCampaignDaoFromCampaignAndPlayerBo(campaignAndPlayerBo);
        CampaignAndVisibilityDao campaignAndVisibilityDao = campaignDataAccess.getCampaignAndVisibilityDao(campaignDao);
        CampaignAndVisibilityBo campaignAndVisibilityBo = campaignDataAccessConverter.getCampaignAndVisibilityBoFromCampaignAndVisibilityDao(campaignAndVisibilityDao);
        Player player = campaignAndPlayerBo.getPlayer();
        if (campaignAndVisibilityBo.getCampaign() == null)
            return campaignAndVisibilityBo;
        return filterCampaignAndVisibilityBo(campaignAndVisibilityBo, player);
    }

    public CampaignAndVisibilityBo getCampaignAndVisibilityBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo) {
        CampaignAndVisibilityAndDungeonMasterBo filteredCampaignAndVisibilityAndDungeonMasterBo = filterCampaignAndVisibilityAndDungeonMasterBo(campaignAndVisibilityAndDungeonMasterBo);
        CampaignAndVisibilityBo campaignAndVisibilityBo = campaignBusinessLogicConverter.getCampaignAndVisibilityBoFromCampaignAndVisibilityAndDungeonMasterBo(filteredCampaignAndVisibilityAndDungeonMasterBo);
        CampaignAndVisibilityDao campaignAndVisibilityDao = campaignDataAccessConverter.getCampaignAndVisibilityDaoFromCampaignAndVisibilityBo(campaignAndVisibilityBo);
        campaignAndVisibilityDao = campaignDataAccess.getCampaignAndVisibilityDao(campaignAndVisibilityDao);
        return campaignDataAccessConverter.getCampaignAndVisibilityBoFromCampaignAndVisibilityDao(campaignAndVisibilityDao);
    }

    public CampaignBo getCampaignBo(CampaignAndPlayerAndDungeonMasterBo campaignAndPlayerAndDungeonMasterBo) {
        CampaignAndPlayerAndDungeonMasterBo filteredCampaignAndPlayerAndDungeonMasterBo = filterCampaignAndPlayerAndDungeonMasterBo(campaignAndPlayerAndDungeonMasterBo);
        CampaignAndPlayerDao campaignAndPlayerDao = campaignDataAccessConverter.getCampaignAndPlayerDaoFromCampaignAndPlayerAndDungeonMasterBo(filteredCampaignAndPlayerAndDungeonMasterBo);
        CampaignDao campaignDao = campaignDataAccess.getCampaignDao(campaignAndPlayerDao);
        return campaignDataAccessConverter.getCampaignBoFromCampaignDao(campaignDao);
    }

    private CampaignAndVisibilityAndDungeonMasterBo filterCampaignAndDungeonMasterBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo) {
        Campaign campaign = campaignAndVisibilityAndDungeonMasterBo.getCampaign();
        Map<String, Visibility> visibilityMap = campaignAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        DungeonMaster dungeonMaster = campaignAndVisibilityAndDungeonMasterBo.getDungeonMaster();
        String dungeonMasterId = dungeonMaster.getId();
        String campaignDungeonMasterId = campaign.getDungeonMasterId();
        if (!dungeonMasterId.equals(campaignDungeonMasterId)) {
            campaign = null;
            visibilityMap = null;
        }
        return CampaignAndVisibilityAndDungeonMasterBo
                .builder()
                .campaign(campaign)
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    private CampaignAndVisibilityBo filterCampaignAndVisibilityBo(CampaignAndVisibilityBo campaignAndVisibilityBo, Player player) {
        Campaign campaign = campaignAndVisibilityBo.getCampaign();
        Map<String, Visibility> visibilityMap = campaignAndVisibilityBo.getVisibilityMap();
        Campaign filteredCampaign = campaign;
        if (player.getClass() != DungeonMaster.class) {
            ObjectMapper objectMapper = new ObjectMapper();
            String campaignJson = "{}";
            String visibilityJson = "{}";
            try {
                campaignJson = objectMapper.writeValueAsString(campaign);
                visibilityJson = objectMapper.writeValueAsString(visibilityMap);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject campaignJsonObject = new JSONObject();
            JSONObject visibilityJsonObject = new JSONObject();
            try {
                campaignJsonObject = (JSONObject)jsonParser.parse(campaignJson);
                visibilityJsonObject = (JSONObject)jsonParser.parse(visibilityJson);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String[] visibilityKeys = new String[] {};
            visibilityKeys = (String[])visibilityJsonObject
                    .keySet()
                    .toArray(visibilityKeys);
            for (String visibilityKey : visibilityKeys) {
                Visibility visibility = Visibility.valueOf(visibilityJsonObject.get(visibilityKey).toString().toUpperCase());
                if (campaignJsonObject.containsKey(visibilityKey) && (visibility != Visibility.EVERYONE))
                    campaignJsonObject.remove(visibilityKey);
            }
            JSONObject filteredCampaignJsonObject = campaignJsonObject;
            String filteredCampaignJson = filteredCampaignJsonObject.toJSONString();
            try {
                filteredCampaign = objectMapper.readValue(filteredCampaignJson, Campaign.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return CampaignAndVisibilityBo
                .builder()
                .campaign(filteredCampaign)
                .visibilityMap(visibilityMap)
                .build();
    }

    private CampaignAndVisibilityAndDungeonMasterBo filterCampaignAndVisibilityAndDungeonMasterBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo) {
        Campaign campaign = campaignAndVisibilityAndDungeonMasterBo.getCampaign();
        Map<String, Visibility> visibilityMap = campaignAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        DungeonMaster dungeonMaster = campaignAndVisibilityAndDungeonMasterBo.getDungeonMaster();
        String dungeonMasterId = dungeonMaster.getId();
        String campaignDungeonMasterId = campaign.getDungeonMasterId();
        if (!dungeonMasterId.equals(campaignDungeonMasterId)) {
            campaign = null;
            visibilityMap = null;
        }
        Map<String, Visibility> filteredVisibilityMap = visibilityMap;
        if (filteredVisibilityMap != null)
            visibilityMap.keySet().forEach(key -> correctVisibility(filteredVisibilityMap, key));
        return CampaignAndVisibilityAndDungeonMasterBo
                .builder()
                .campaign(campaign)
                .visibilityMap(filteredVisibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    private void correctVisibility(Map<String, Visibility> visibilityMap, String key) {
        if (visibilityMap.get(key) == Visibility.PLAYER)
            visibilityMap.replace(key, Visibility.DUNGEON_MASTER);
    }

    private CampaignAndPlayerAndDungeonMasterBo filterCampaignAndPlayerAndDungeonMasterBo(CampaignAndPlayerAndDungeonMasterBo campaignAndPlayerAndDungeonMasterBo) {
        Campaign campaign = campaignAndPlayerAndDungeonMasterBo.getCampaign();
        Player player = campaignAndPlayerAndDungeonMasterBo.getPlayer();
        DungeonMaster dungeonMaster = campaignAndPlayerAndDungeonMasterBo.getDungeonMaster();
        String dungeonMasterId = dungeonMaster.getId();
        String campaignDungeonMasterId = campaign.getDungeonMasterId();
        if (!dungeonMasterId.equals(campaignDungeonMasterId)) {
            campaign = null;
            player = null;
        }
        return CampaignAndPlayerAndDungeonMasterBo
                .builder()
                .campaign(campaign)
                .player(player)
                .dungeonMaster(dungeonMaster)
                .build();
    }
}