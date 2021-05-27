package services.classdetailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.Class;
import objects.DataOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.classdetailservice.module.ClassDetailModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetClassDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private GetClassDetails getClassDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ClassDetailModule(GetClassDetails.class));
        getClassDetails = injector.getInstance(GetClassDetails.class);
    }

    @Test
    public void shouldReturnClass() {
        String responseJson = createMockResponseJson();
        ClassDetailsResponse classDetailsResponse = mockJsonResponseAndReturnClassDetailsResponse(responseJson);
        Assertions.assertNotNull(classDetailsResponse.getCClass(), "Class null.");
    }

    @Test
    public void shouldReturnNoClassWhenEmptyJson() {
        String responseJson = "{}";
        ClassDetailsResponse classDetailsResponse = mockJsonResponseAndReturnClassDetailsResponse(responseJson);
        Assertions.assertNull(classDetailsResponse.getCClass(), "Class not null.");
    }

    @Test
    public void shouldReturnNoClassWhenNullJson() {
        ClassDetailsResponse classDetailsResponse = mockJsonResponseAndReturnClassDetailsResponse(null);
        Assertions.assertNull(classDetailsResponse.getCClass(), "Class not null.");
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

    private ClassDetailsResponse mockJsonResponseAndReturnClassDetailsResponse(String responseJson) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        ClassDetailsRequest classDetailsRequest = ClassDetailsRequest
                .builder()
                .cClass(Class
                        .builder()
                        .build())
                .build();
        return getClassDetails.getClassDetailsResponse(classDetailsRequest);
    }
}