package services.campaigndetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Campaign;
import objects.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.campaigndetailservice.bll.bo.CampaignAndPlayerBo;
import services.campaigndetailservice.bll.bo.CampaignDetailsAndVisibilityBo;
import services.campaigndetailservice.dal.dao.CampaignDao;
import services.campaigndetailservice.dal.dao.CampaignDetailsAndVisibilityDao;
import java.util.HashMap;
import java.util.Map;

public class CampaignDetailDataAccessConverterImpl implements CampaignDetailDataAccessConverter {
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

    public CampaignDetailsAndVisibilityDao getCampaignDetailsAndVisibilityDaoFromCampaignDetailsAndVisibilityBo(CampaignDetailsAndVisibilityBo campaignDetailsAndVisibilityBo) {
        Campaign campaign = campaignDetailsAndVisibilityBo.getCampaign();
        Map<String, Visibility> visibilityMap = campaignDetailsAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignJson = "{}";
        String visibilityJson = "{}";
        try {
            campaignJson = objectMapper.writeValueAsString(campaign);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String campaignDetailsAndVisibilityJson = "{}";
        if ((!campaignJson.equals("{}") && (!campaignJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            campaignDetailsAndVisibilityJson = "{" +
                    "campaignDetails:" +
                    campaignJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return CampaignDetailsAndVisibilityDao
                .builder()
                .campaignDetailsAndVisibilityJson(campaignDetailsAndVisibilityJson)
                .build();
    }

    public CampaignDetailsAndVisibilityBo getCampaignDetailsAndVisibilityBoFromCampaignDetailsAndVisibilityDao(CampaignDetailsAndVisibilityDao campaignDetailsAndVisibilityDao) {
        String campaignDetailsAndVisibilityJson = campaignDetailsAndVisibilityDao.getCampaignDetailsAndVisibilityJson();
        if (campaignDetailsAndVisibilityJson == null)
            campaignDetailsAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(campaignDetailsAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignDetailsJson;
        Campaign campaign = null;
        if (jsonObject.get("campaignDetails") != null) {
            campaignDetailsJson = ((JSONObject) jsonObject.get("campaignDetails")).toJSONString();
            campaign = Campaign
                    .builder()
                    .build();
            try {
                campaign = objectMapper.readValue(campaignDetailsJson, Campaign.class);
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
                visibilityMap = objectMapper.readValue(visibilityJson, Map.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return CampaignDetailsAndVisibilityBo
                .builder()
                .campaign(campaign)
                .visibilityMap(visibilityMap)
                .build();
    }

    public CampaignDetailsAndVisibilityDao getCampaignDetailsAndVisibilityDaoFromCampaignDetailsAndVisibilityJson(String campaignDetailsAndVisibilityJson) {
        return CampaignDetailsAndVisibilityDao
                .builder()
                .campaignDetailsAndVisibilityJson(campaignDetailsAndVisibilityJson)
                .build();
    }
}