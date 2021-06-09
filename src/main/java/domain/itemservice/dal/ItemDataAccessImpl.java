package domain.itemservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import common.Campaign;
import common.Player;
import common.QueryType;
import persistence.operatorservice.RequestQueryRequest;
import persistence.operatorservice.RequestQueryResponse;
import persistence.operatorservice.SendRequestQuery;
import domain.itemservice.dal.dao.ItemDao;
import domain.itemservice.dal.dao.ItemAndVisibilityDao;

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