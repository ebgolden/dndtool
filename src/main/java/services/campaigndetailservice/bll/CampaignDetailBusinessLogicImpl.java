package services.campaigndetailservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import objects.Campaign;
import objects.DungeonMaster;
import objects.Player;
import objects.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.campaigndetailservice.bll.bo.*;
import services.campaigndetailservice.dal.CampaignDetailDataAccess;
import services.campaigndetailservice.dal.CampaignDetailDataAccessConverter;
import services.campaigndetailservice.dal.dao.CampaignDao;
import services.campaigndetailservice.dal.dao.CampaignAndPlayerDao;
import services.campaigndetailservice.dal.dao.CampaignDetailsAndVisibilityDao;
import services.campaigndetailservice.dal.dao.CampaignDetailsDao;
import java.util.Map;

public class CampaignDetailBusinessLogicImpl implements CampaignDetailBusinessLogic {
    @Inject
    private CampaignDetailDataAccessConverter campaignDetailDataAccessConverter;
    @Inject
    private CampaignDetailDataAccess campaignDetailDataAccess;
    @Inject
    private CampaignDetailBusinessLogicConverter campaignDetailBusinessLogicConverter;

    public CampaignDetailsAndVisibilityBo getCampaignDetailsAndVisibilityBo(CampaignAndPlayerBo campaignAndPlayerBo) {
        CampaignDao campaignDao = campaignDetailDataAccessConverter.getCampaignDaoFromCampaignAndPlayerBo(campaignAndPlayerBo);
        CampaignDetailsAndVisibilityDao campaignDetailsAndVisibilityDao = campaignDetailDataAccess.getCampaignDetailsAndVisibilityDao(campaignDao);
        CampaignDetailsAndVisibilityBo campaignDetailsAndVisibilityBo = campaignDetailDataAccessConverter.getCampaignDetailsAndVisibilityBoFromCampaignDetailsAndVisibilityDao(campaignDetailsAndVisibilityDao);
        Player player = campaignAndPlayerBo.getPlayer();
        if (campaignDetailsAndVisibilityBo.getCampaign() == null)
            return campaignDetailsAndVisibilityBo;
        return filterCampaignDetailsAndVisibilityBo(campaignDetailsAndVisibilityBo, player);
    }

    public CampaignDetailsAndVisibilityBo getCampaignDetailsAndVisibilityBo(CampaignDetailsAndVisibilityAndDungeonMasterBo campaignDetailsAndVisibilityAndDungeonMasterBo) {
        CampaignDetailsAndVisibilityAndDungeonMasterBo filteredCampaignDetailsAndVisibilityAndDungeonMasterBo = filterCampaignDetailsAndVisibilityAndDungeonMasterBo(campaignDetailsAndVisibilityAndDungeonMasterBo);
        CampaignDetailsAndVisibilityBo campaignDetailsAndVisibilityBo = campaignDetailBusinessLogicConverter.getCampaignDetailsAndVisibilityBoFromCampaignDetailsAndVisibilityAndDungeonMasterBo(filteredCampaignDetailsAndVisibilityAndDungeonMasterBo);
        CampaignDetailsAndVisibilityDao campaignDetailsAndVisibilityDao = campaignDetailDataAccessConverter.getCampaignDetailsAndVisibilityDaoFromCampaignDetailsAndVisibilityBo(campaignDetailsAndVisibilityBo);
        campaignDetailsAndVisibilityDao = campaignDetailDataAccess.getCampaignDetailsAndVisibilityDao(campaignDetailsAndVisibilityDao);
        return campaignDetailDataAccessConverter.getCampaignDetailsAndVisibilityBoFromCampaignDetailsAndVisibilityDao(campaignDetailsAndVisibilityDao);
    }

    public CampaignDetailsBo getCampaignDetailsBo(CampaignAndPlayerAndDungeonMasterBo campaignAndPlayerAndDungeonMasterBo) {
        CampaignAndPlayerAndDungeonMasterBo filteredCampaignAndPlayerAndDungeonMasterBo = filterCampaignAndPlayerAndDungeonMasterBo(campaignAndPlayerAndDungeonMasterBo);
        CampaignAndPlayerDao campaignAndPlayerDao = campaignDetailDataAccessConverter.getCampaignAndPlayerDaoFromCampaignAndPlayerAndDungeonMasterBo(filteredCampaignAndPlayerAndDungeonMasterBo);
        CampaignDetailsDao campaignDetailsDao = campaignDetailDataAccess.getCampaignDetailsDao(campaignAndPlayerDao);
        return campaignDetailDataAccessConverter.getCampaignDetailsBoFromCampaignDetailsDao(campaignDetailsDao);
    }

    private CampaignDetailsAndVisibilityBo filterCampaignDetailsAndVisibilityBo(CampaignDetailsAndVisibilityBo campaignDetailsAndVisibilityBo, Player player) {
        Campaign campaign = campaignDetailsAndVisibilityBo.getCampaign();
        Map<String, Visibility> visibilityMap = campaignDetailsAndVisibilityBo.getVisibilityMap();
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
        return CampaignDetailsAndVisibilityBo
                .builder()
                .campaign(filteredCampaign)
                .visibilityMap(visibilityMap)
                .build();
    }

    private CampaignDetailsAndVisibilityAndDungeonMasterBo filterCampaignDetailsAndVisibilityAndDungeonMasterBo(CampaignDetailsAndVisibilityAndDungeonMasterBo campaignDetailsAndVisibilityAndDungeonMasterBo) {
        Campaign campaign = campaignDetailsAndVisibilityAndDungeonMasterBo.getCampaign();
        Map<String, Visibility> visibilityMap = campaignDetailsAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        DungeonMaster dungeonMaster = campaignDetailsAndVisibilityAndDungeonMasterBo.getDungeonMaster();
        String dungeonMasterId = dungeonMaster.getId();
        String campaignDungeonMasterId = campaign.getDungeonMasterId();
        if (!dungeonMasterId.equals(campaignDungeonMasterId)) {
            campaign = null;
            visibilityMap = null;
        }
        Map<String, Visibility> filteredVisibilityMap = visibilityMap;
        if (filteredVisibilityMap != null)
            visibilityMap.keySet().forEach(key -> correctVisibility(filteredVisibilityMap, key));
        return CampaignDetailsAndVisibilityAndDungeonMasterBo
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