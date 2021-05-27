package services.campaignservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonobjects.Campaign;
import commonobjects.DungeonMaster;
import commonobjects.Player;
import commonobjects.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.campaignservice.bll.bo.*;
import services.campaignservice.dal.dao.CampaignAndPlayerDao;
import services.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignAndVisibilityDao;
import services.campaignservice.dal.dao.CampaignDao;
import java.util.HashMap;
import java.util.Map;

public class CampaignDataAccessConverterImpl implements CampaignDataAccessConverter {
    public CampaignAndVisibilityAndDungeonMasterDao getCampaignAndVisibilityAndDungeonMasterDaoFromCampaignAndVisibilityAndDungeonMasterBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo) {
        Campaign campaign = campaignAndVisibilityAndDungeonMasterBo.getCampaign();
        Map<String, Visibility> visibilityMap = campaignAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        DungeonMaster dungeonMaster = campaignAndVisibilityAndDungeonMasterBo.getDungeonMaster();
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignJson = "{}";
        String visibilityJson = "{}";
        String dungeonMasterJson = "{}";
        try {
            campaignJson = objectMapper.writeValueAsString(campaign);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
            dungeonMasterJson = objectMapper.writeValueAsString(dungeonMaster);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String campaignAndVisibilityAndDungeonMasterJson = "{}";
        if ((!campaignJson.equals("{}") && (!campaignJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))) || (!dungeonMasterJson.equals("{}") && (!dungeonMasterJson.equals("null"))))
            campaignAndVisibilityAndDungeonMasterJson = "{" +
                    "campaign:" +
                    campaignJson +
                    ",visibility:" +
                    visibilityJson +
                    ",dungeonMaster:" +
                    dungeonMasterJson +
                    "}";
        return CampaignAndVisibilityAndDungeonMasterDao
                .builder()
                .campaignAndVisibilityAndDungeonMasterJson(campaignAndVisibilityAndDungeonMasterJson)
                .build();
    }

    public CampaignDao getCampaignDaoFromCampaignAndPlayerBo(CampaignAndPlayerBo campaignAndPlayerBo) {
        Campaign campaign = campaignAndPlayerBo.getCampaign();
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignJson = "{}";
        try {
            campaignJson = objectMapper.writeValueAsString(campaign);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return CampaignDao
                .builder()
                .campaignJson(campaignJson)
                .build();
    }

    public CampaignAndVisibilityDao getCampaignAndVisibilityDaoFromCampaignAndVisibilityBo(CampaignAndVisibilityBo campaignAndVisibilityBo) {
        Campaign campaign = campaignAndVisibilityBo.getCampaign();
        Map<String, Visibility> visibilityMap = campaignAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignJson = "{}";
        String visibilityJson = "{}";
        try {
            campaignJson = objectMapper.writeValueAsString(campaign);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String campaignAndVisibilityJson = "{}";
        if ((!campaignJson.equals("{}") && (!campaignJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            campaignAndVisibilityJson = "{" +
                    "campaign:" +
                    campaignJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return CampaignAndVisibilityDao
                .builder()
                .campaignAndVisibilityJson(campaignAndVisibilityJson)
                .build();
    }

    public CampaignAndPlayerDao getCampaignAndPlayerDaoFromCampaignAndPlayerAndDungeonMasterBo(CampaignAndPlayerAndDungeonMasterBo campaignAndPlayerAndDungeonMasterBo) {
        Campaign campaign = campaignAndPlayerAndDungeonMasterBo.getCampaign();
        Player player = campaignAndPlayerAndDungeonMasterBo.getPlayer();
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignJson = "{}";
        String playerJson = "{}";
        try {
            campaignJson = objectMapper.writeValueAsString(campaign);
            playerJson = objectMapper.writeValueAsString(player);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String campaignAndPlayerJson = "{}";
        if ((!campaignJson.equals("{}") && (!campaignJson.equals("null"))) || (!playerJson.equals("{}") && (!playerJson.equals("null"))))
            campaignAndPlayerJson = "{" +
                    "campaign:" +
                    campaignJson +
                    ",player:" +
                    playerJson +
                    "}";
        return CampaignAndPlayerDao
                .builder()
                .campaignAndPlayerJson(campaignAndPlayerJson)
                .build();
    }

    public CampaignBo getCampaignBoFromCampaignDao(CampaignDao campaignDao) {
        String campaignJson = campaignDao.getCampaignJson();
        if (campaignJson == null)
            campaignJson = "{}";
        Campaign campaign = null;
        if (!campaignJson.equals("{}")) {
            ObjectMapper objectMapper = new ObjectMapper();
            campaign = Campaign
                    .builder()
                    .build();
            try {
                campaign = objectMapper.readValue(campaignJson, Campaign.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return CampaignBo
                .builder()
                .campaign(campaign)
                .build();
    }

    public CampaignAndVisibilityBo getCampaignAndVisibilityBoFromCampaignAndVisibilityDao(CampaignAndVisibilityDao campaignAndVisibilityDao) {
        String campaignAndVisibilityJson = campaignAndVisibilityDao.getCampaignAndVisibilityJson();
        if (campaignAndVisibilityJson == null)
            campaignAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(campaignAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignJson;
        Campaign campaign = null;
        if (jsonObject.get("campaign") != null) {
            campaignJson = ((JSONObject) jsonObject.get("campaign")).toJSONString();
            campaign = Campaign
                    .builder()
                    .build();
            try {
                campaign = objectMapper.readValue(campaignJson, Campaign.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        String visibilityJson;
        Map<String, Visibility> visibilityMap = null;
        if (jsonObject.get("visibility") != null) {
            visibilityJson = ((JSONObject)jsonObject.get("visibility")).toJSONString();
            visibilityMap = new HashMap<>();
            try {
                TypeReference<Map<String, Visibility>> visibilityMapTypeReference = new TypeReference<Map<String, Visibility>>(){};
                visibilityMap = objectMapper.readValue(visibilityJson, visibilityMapTypeReference);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return CampaignAndVisibilityBo
                .builder()
                .campaign(campaign)
                .visibilityMap(visibilityMap)
                .build();
    }

    public CampaignDao getCampaignDaoFromCampaignJson(String campaignJson) {
        return CampaignDao
                .builder()
                .campaignJson(campaignJson)
                .build();
    }

    public CampaignAndVisibilityDao getCampaignAndVisibilityDaoFromCampaignAndVisibilityJson(String campaignAndVisibilityJson) {
        return CampaignAndVisibilityDao
                .builder()
                .campaignAndVisibilityJson(campaignAndVisibilityJson)
                .build();
    }
}