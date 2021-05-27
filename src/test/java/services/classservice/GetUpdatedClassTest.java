package services.classservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import commonobjects.Class;
import commonobjects.DataOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.classservice.module.ClassModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedClassTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedClass getUpdatedClass;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ClassModule(GetUpdatedClass.class));
        getUpdatedClass = injector.getInstance(GetUpdatedClass.class);
    }

    @Test
    public void shouldReturnClass() {
        String responseJson = createMockResponseJson();
        UpdatedClassResponse updatedClassResponse = mockJsonResponseAndReturnClassDetailsResponse(responseJson);
        Assertions.assertNotNull(updatedClassResponse.getCClass(), "Class null.");
    }

    @Test
    public void shouldReturnNoClassWhenEmptyJson() {
        String responseJson = "{}";
        UpdatedClassResponse updatedClassResponse = mockJsonResponseAndReturnClassDetailsResponse(responseJson);
        Assertions.assertNull(updatedClassResponse.getCClass(), "Class not null.");
    }

    @Test
    public void shouldReturnNoClassWhenNullJson() {
        UpdatedClassResponse updatedClassResponse = mockJsonResponseAndReturnClassDetailsResponse(null);
        Assertions.assertNull(updatedClassResponse.getCClass(), "Class not null.");
    }

    private String createMockResponseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String classJson;
        try {
            classJson = objectMapper.writeValueAsString(Class
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            classJson = "{}";
        }
        return classJson;
    }

    private UpdatedClassResponse mockJsonResponseAndReturnClassDetailsResponse(String responseJson) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        UpdatedClassRequest updatedClassRequest = UpdatedClassRequest
                .builder()
                .cClass(Class
                        .builder()
                        .build())
                .build();
        return getUpdatedClass.getUpdatedClassResponse(updatedClassRequest);
    }
}