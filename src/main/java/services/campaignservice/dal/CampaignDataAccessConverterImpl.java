package services.campaignservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Campaign;
import objects.DungeonMaster;
import services.campaignservice.bll.bo.CampaignAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;
import services.campaignservice.dal.dao.CampaignAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignDao;

public class CampaignDataAccessConverterImpl implements CampaignDataAccessConverter {
    public CampaignAndDungeonMasterDao getCampaignAndDungeonMasterDaoFromCampaignAndDungeonMasterBo(CampaignAndDungeonMasterBo campaignAndDungeonMasterBo) {
        Campaign campaign = campaignAndDungeonMasterBo.getCampaign();
        DungeonMaster dungeonMaster = campaignAndDungeonMasterBo.getDungeonMaster();
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignJson = "{}";
        String dungeonMasterJson = "{}";
        try {
            campaignJson = objectMapper.writeValueAsString(campaign);
            dungeonMasterJson = objectMapper.writeValueAsString(dungeonMaster);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String campaignAndDungeonMasterJson = "{}";
        if ((!campaignJson.equals("{}") && (!campaignJson.equals("null"))) || (!dungeonMasterJson.equals("{}") && (!dungeonMasterJson.equals("null"))))
            campaignAndDungeonMasterJson = "{" +
                    "campaign:" +
                    campaignJson +
                    ",dungeonMaster:" +
                    dungeonMasterJson +
                    "}";
        return CampaignAndDungeonMasterDao
                .builder()
                .campaignAndDungeonMasterJson(campaignAndDungeonMasterJson)
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

    public CampaignDao getCampaignDaoFromCampaignJsonObject(String campaignJsonObject) {
        return CampaignDao
                .builder()
                .campaignJson(campaignJsonObject)
                .build();
    }
}