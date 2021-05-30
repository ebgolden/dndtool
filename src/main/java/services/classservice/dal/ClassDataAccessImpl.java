package services.classservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.classservice.dal.dao.ClassDao;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;

public class ClassDataAccessImpl implements ClassDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private ClassDataAccessConverter classDataAccessConverter;

    public ClassDao getClassDao(ClassDao classDao) {
        String classJson = classDao.getClassJson();
        queryRequest = constructQueryRequest(classJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        classJson = queryResponse.getResponseJson();
        return classDataAccessConverter.getClassDaoFromClassJson(classJson);
    }

    private QueryRequest constructQueryRequest(String requestJson) {
        Campaign campaign = queryRequest.getCampaign();
        Player senderPlayer = queryRequest.getSenderPlayer();
        Object api = queryRequest.getApi();
        return QueryRequest
                .builder()
                .campaign(campaign)
                .senderPlayer(senderPlayer)
                .api(api)
                .queryType(QueryType.PULL)
                .requestJson(requestJson)
                .build();
    }
}