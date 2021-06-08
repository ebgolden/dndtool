package services.campaignservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.campaignservice.dal.dao.CampaignAndPlayerDao;
import services.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignAndVisibilityDao;
import services.campaignservice.dal.dao.CampaignDao;
import services.dataoperatorservice.RequestQueryRequest;
import services.dataoperatorservice.RequestQueryResponse;
import services.dataoperatorservice.SendRequestQuery;

public class CampaignDataAccessImpl implements CampaignDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private CampaignDataAccessConverter campaignDataAccessConverter;

    public CampaignDao getCampaignDao(CampaignAndVisibilityAndDungeonMasterDao campaignAndVisibilityAndDungeonMasterDao) {
        String campaignAndDungeonMasterJson = campaignAndVisibilityAndDungeonMasterDao.getCampaignAndVisibilityAndDungeonMasterJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, campaignAndDungeonMasterJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String campaignJson = requestQueryResponse.getResponseJson();
        return campaignDataAccessConverter.getCampaignDaoFromCampaignJson(campaignJson);
    }

    public CampaignAndVisibilityDao getCampaignAndVisibilityDao(CampaignDao campaignDao) {
        String campaignJson = campaignDao.getCampaignJson();
        requestQueryRequest = constructQueryRequest(QueryType.PULL, campaignJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String campaignAndVisibilityJson = requestQueryResponse.getResponseJson();
        return campaignDataAccessConverter.getCampaignAndVisibilityDaoFromCampaignAndVisibilityJson(campaignAndVisibilityJson);
    }

    public CampaignAndVisibilityDao getCampaignAndVisibilityDao(CampaignAndVisibilityDao campaignAndVisibilityDao) {
        String campaignAndVisibilityJson = campaignAndVisibilityDao.getCampaignAndVisibilityJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, campaignAndVisibilityJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        campaignAndVisibilityJson = requestQueryResponse.getResponseJson();
        return campaignDataAccessConverter.getCampaignAndVisibilityDaoFromCampaignAndVisibilityJson(campaignAndVisibilityJson);
    }

    public CampaignDao getCampaignDao(CampaignAndPlayerDao campaignAndPlayerDao) {
        String campaignAndPlayerJson = campaignAndPlayerDao.getCampaignAndPlayerJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, campaignAndPlayerJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String campaignJson = requestQueryResponse.getResponseJson();
        return campaignDataAccessConverter.getCampaignDaoFromCampaignJson(campaignJson);
    }

    private RequestQueryRequest constructQueryRequest(QueryType queryType, String requestJson) {
        Campaign campaign = requestQueryRequest.getCampaign();
        Player player = requestQueryRequest.getPlayer();
        Object api = requestQueryRequest.getApi();
        return RequestQueryRequest
                .builder()
                .campaign(campaign)
                .player(player)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }
}