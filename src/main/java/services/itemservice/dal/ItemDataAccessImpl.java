package services.itemservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.RequestQueryRequest;
import services.dataoperatorservice.RequestQueryResponse;
import services.dataoperatorservice.SendRequestQuery;
import services.itemservice.dal.dao.ItemDao;
import services.itemservice.dal.dao.ItemAndVisibilityDao;

public class ItemDataAccessImpl implements ItemDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private ItemDataAccessConverter itemDataAccessConverter;

    public ItemAndVisibilityDao getItemAndVisibilityDao(ItemDao itemDao) {
        String itemJson = itemDao.getItemJson();
        requestQueryRequest = constructQueryRequest(QueryType.PULL, itemJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String itemAndVisibilityJson = requestQueryResponse.getResponseJson();
        return itemDataAccessConverter.getItemAndVisibilityDaoFromItemAndVisibilityJson(itemAndVisibilityJson);
    }

    public ItemAndVisibilityDao getItemAndVisibilityDao(ItemAndVisibilityDao itemAndVisibilityDao) {
        String itemAndVisibilityJson = itemAndVisibilityDao.getItemAndVisibilityJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, itemAndVisibilityJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        itemAndVisibilityJson = requestQueryResponse.getResponseJson();
        return itemDataAccessConverter.getItemAndVisibilityDaoFromItemAndVisibilityJson(itemAndVisibilityJson);
    }

    private RequestQueryRequest constructQueryRequest(QueryType queryType, String requestJson) {
        Campaign campaign = requestQueryRequest.getCampaign();
        Player senderPlayer = requestQueryRequest.getSenderPlayer();
        Object api = requestQueryRequest.getApi();
        return RequestQueryRequest
                .builder()
                .campaign(campaign)
                .senderPlayer(senderPlayer)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }
}