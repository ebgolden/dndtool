package domain.classservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import common.*;
import common.Class;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.classservice.module.ClassModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedClassTest {
    @Mock
    Operator mockOperator;
    private GetUpdatedClass getUpdatedClass;

    @BeforeEach
    public void setup() {
        Campaign campaign = Campaign
                .builder()
                .id("1")
                .build();
        Player player = Player
                .builder()
                .id("1")
                .build();
        Injector injector = Guice.createInjector(new ClassModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        GetUpdatedClass.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        getUpdatedClass = injector.getInstance(GetUpdatedClass.class);
    }

    @Test
    public void shouldReturnClass() {
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithClass();
        UpdatedClassResponse updatedClassResponse = mockResponseAndReturnClassDetailsResponse(operatorResponseQuery);
        Assertions.assertNotNull(updatedClassResponse.getCClass(), "Class null.");
    }

    @Test
    public void shouldReturnNoClassWhenEmptyResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        UpdatedClassResponse updatedClassResponse = mockResponseAndReturnClassDetailsResponse(operatorResponseQuery);
        Assertions.assertNull(updatedClassResponse.getCClass(), "Class not null.");
    }

    @Test
    public void shouldReturnNoClassWhenNullResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        UpdatedClassResponse updatedClassResponse = mockResponseAndReturnClassDetailsResponse(operatorResponseQuery);
        Assertions.assertNull(updatedClassResponse.getCClass(), "Class not null.");
    }

    private OperatorResponseQuery createMockResponseWithClass() {
        String queryId = "123";
        ObjectMapper objectMapper = new ObjectMapper();
        String classJson;
        try {
            classJson = objectMapper.writeValueAsString(Class
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            classJson = "{}";
        }
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(classJson)
                .build();
    }

    private OperatorResponseQuery createMockResponse(String responseJson) {
        String queryId = "123";
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    private UpdatedClassResponse mockResponseAndReturnClassDetailsResponse(OperatorResponseQuery operatorResponseQuery) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
        UpdatedClassRequest updatedClassRequest = UpdatedClassRequest
                .builder()
                .cClass(Class
                        .builder()
                        .build())
                .build();
        return getUpdatedClass.getUpdatedClassResponse(updatedClassRequest);
    }
}