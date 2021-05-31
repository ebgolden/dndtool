package services.classservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.classservice.dal.dao.ClassDao;
import services.dataoperatorservice.RequestQueryRequest;
import services.dataoperatorservice.RequestQueryResponse;
import services.dataoperatorservice.SendRequestQuery;

public class ClassDataAccessImpl implements ClassDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private ClassDataAccessConverter classDataAccessConverter;

    public ClassDao getClassDao(ClassDao classDao) {
        String classJson = classDao.getClassJson();
        requestQueryRequest = constructQueryRequest(classJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        classJson = requestQueryResponse.getResponseJson();
        return classDataAccessConverter.getClassDaoFromClassJson(classJson);
    }

    private RequestQueryRequest constructQueryRequest(String requestJson) {
        Campaign campaign = requestQueryRequest.getCampaign();
        Player senderPlayer = requestQueryRequest.getSenderPlayer();
        Object api = requestQueryRequest.getApi();
        return RequestQueryRequest
                .builder()
                .campaign(campaign)
                .senderPlayer(senderPlayer)
                .api(api)
                .queryType(QueryType.PULL)
                .requestJson(requestJson)
                .build();
    }
}