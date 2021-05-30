package services.dataoperatorservice.bll;

import com.google.inject.Inject;
import commonobjects.QueryType;
import services.dataoperatorservice.bll.bo.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo;
import services.dataoperatorservice.bll.bo.QueryIdAndResponseJsonBo;
import services.dataoperatorservice.dal.DataOperatorDataAccess;
import services.dataoperatorservice.dal.DataOperatorDataAccessConverter;
import services.dataoperatorservice.dal.dao.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao;
import services.dataoperatorservice.dal.dao.QueryIdAndResponseJsonDao;

public class DataOperatorBusinessLogicImpl implements DataOperatorBusinessLogic {
    @Inject
    private DataOperatorDataAccessConverter dataOperatorDataAccessConverter;
    @Inject
    private DataOperatorDataAccess dataOperatorDataAccess;

    public QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBo(CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo) {
        CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo filteredCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo = filterCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);
        CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao = dataOperatorDataAccessConverter.getCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDaoFromCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(filteredCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);
        QueryIdAndResponseJsonDao queryIdAndResponseJsonDao = dataOperatorDataAccess.getQueryIdAndResponseJsonDao(campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao);
        return dataOperatorDataAccessConverter.getQueryIdAndResponseJsonBoFromQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
    }

    private CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo filterCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo) {
        String campaignId = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getCampaignId();
        String senderPlayerId = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getSenderPlayerId();
        String apiName = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getApiName();
        QueryType queryType = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getQueryType();
        String requestJson = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getRequestJson();
        String[] apiNameParts = apiName.split("\\.");
        int indexOfLastApiNamePart = apiNameParts.length - 1;
        String filteredApiName = apiNameParts[indexOfLastApiNamePart];
        return CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo
                .builder()
                .campaignId(campaignId)
                .senderPlayerId(senderPlayerId)
                .apiName(filteredApiName)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }
}