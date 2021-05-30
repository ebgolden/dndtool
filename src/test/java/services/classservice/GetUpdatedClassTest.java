package services.classservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import commonobjects.*;
import commonobjects.Class;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.classservice.module.ClassModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedClassTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedClass getUpdatedClass;

    @BeforeEach
    public void setup() {
        Campaign campaign = Campaign
                .builder()
                .id("1")
                .build();
        Player senderPlayer = Player
                .builder()
                .id("1")
                .build();
        Injector injector = Guice.createInjector(new ClassModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        senderPlayer,
                        GetUpdatedClass.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        getUpdatedClass = injector.getInstance(GetUpdatedClass.class);
    }

    @Test
    public void shouldReturnClass() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithClass();
        UpdatedClassResponse updatedClassResponse = mockResponseAndReturnClassDetailsResponse(dataOperatorResponseQuery);
        Assertions.assertNotNull(updatedClassResponse.getCClass(), "Class null.");
    }

    @Test
    public void shouldReturnNoClassWhenEmptyResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        UpdatedClassResponse updatedClassResponse = mockResponseAndReturnClassDetailsResponse(dataOperatorResponseQuery);
        Assertions.assertNull(updatedClassResponse.getCClass(), "Class not null.");
    }

    @Test
    public void shouldReturnNoClassWhenNullResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        UpdatedClassResponse updatedClassResponse = mockResponseAndReturnClassDetailsResponse(dataOperatorResponseQuery);
        Assertions.assertNull(updatedClassResponse.getCClass(), "Class not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithClass() {
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
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(classJson)
                .build();
    }

    private DataOperatorResponseQuery createMockResponse(String responseJson) {
        String queryId = "123";
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    private UpdatedClassResponse mockResponseAndReturnClassDetailsResponse(DataOperatorResponseQuery dataOperatorResponseQuery) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
        UpdatedClassRequest updatedClassRequest = UpdatedClassRequest
                .builder()
                .cClass(Class
                        .builder()
                        .build())
                .build();
        return getUpdatedClass.getUpdatedClassResponse(updatedClassRequest);
    }
}