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
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;

public class CampaignDataAccessImpl implements CampaignDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private CampaignDataAccessConverter campaignDataAccessConverter;

    public CampaignDao getCampaignDao(CampaignAndVisibilityAndDungeonMasterDao campaignAndVisibilityAndDungeonMasterDao) {
        String campaignAndDungeonMasterJson = campaignAndVisibilityAndDungeonMasterDao.getCampaignAndVisibilityAndDungeonMasterJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, campaignAndDungeonMasterJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String campaignJson = queryResponse.getResponseJson();
        return campaignDataAccessConverter.getCampaignDaoFromCampaignJson(campaignJson);
    }

    public CampaignAndVisibilityDao getCampaignAndVisibilityDao(CampaignDao campaignDao) {
        String campaignJson = campaignDao.getCampaignJson();
        queryRequest = constructQueryRequest(QueryType.PULL, campaignJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String campaignAndVisibilityJson = queryResponse.getResponseJson();
        return campaignDataAccessConverter.getCampaignAndVisibilityDaoFromCampaignAndVisibilityJson(campaignAndVisibilityJson);
    }

    public CampaignAndVisibilityDao getCampaignAndVisibilityDao(CampaignAndVisibilityDao campaignAndVisibilityDao) {
        String campaignAndVisibilityJson = campaignAndVisibilityDao.getCampaignAndVisibilityJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, campaignAndVisibilityJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        campaignAndVisibilityJson = queryResponse.getResponseJson();
        return campaignDataAccessConverter.getCampaignAndVisibilityDaoFromCampaignAndVisibilityJson(campaignAndVisibilityJson);
    }

    public CampaignDao getCampaignDao(CampaignAndPlayerDao campaignAndPlayerDao) {
        String campaignAndPlayerJson = campaignAndPlayerDao.getCampaignAndPlayerJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, campaignAndPlayerJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String campaignJson = queryResponse.getResponseJson();
        return campaignDataAccessConverter.getCampaignDaoFromCampaignJson(campaignJson);
    }

    private QueryRequest constructQueryRequest(QueryType queryType, String requestJson) {
        Campaign campaign = queryRequest.getCampaign();
        Player senderPlayer = queryRequest.getSenderPlayer();
        Object api = queryRequest.getApi();
        return QueryRequest
                .builder()
                .campaign(campaign)
                .senderPlayer(senderPlayer)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }
}