package services.campaignservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Campaign;
import objects.DungeonMaster;
import objects.Visibility;
import services.campaignservice.bll.bo.CampaignAndVisibilityAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;
import services.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignDao;
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

    public CampaignDao getCampaignDaoFromCampaignJson(String campaignJson) {
        return CampaignDao
                .builder()
                .campaignJson(campaignJson)
                .build();
    }
}