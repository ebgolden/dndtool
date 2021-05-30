package services.itemservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;
import services.itemservice.dal.dao.ItemDao;
import services.itemservice.dal.dao.ItemAndVisibilityDao;

public class ItemDataAccessImpl implements ItemDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private ItemDataAccessConverter itemDataAccessConverter;

    public ItemAndVisibilityDao getItemAndVisibilityDao(ItemDao itemDao) {
        String itemJson = itemDao.getItemJson();
        queryRequest = constructQueryRequest(QueryType.PULL, itemJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String itemAndVisibilityJson = queryResponse.getResponseJson();
        return itemDataAccessConverter.getItemAndVisibilityDaoFromItemAndVisibilityJson(itemAndVisibilityJson);
    }

    public ItemAndVisibilityDao getItemAndVisibilityDao(ItemAndVisibilityDao itemAndVisibilityDao) {
        String itemAndVisibilityJson = itemAndVisibilityDao.getItemAndVisibilityJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, itemAndVisibilityJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        itemAndVisibilityJson = queryResponse.getResponseJson();
        return itemDataAccessConverter.getItemAndVisibilityDaoFromItemAndVisibilityJson(itemAndVisibilityJson);
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