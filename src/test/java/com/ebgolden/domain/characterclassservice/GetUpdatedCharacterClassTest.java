package domain.characterclassservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import common.*;
import common.CharacterClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.characterclassservice.module.CharacterClassModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedCharacterClassTest {
    @Mock
    Operator mockOperator;
    private GetUpdatedCharacterClass getUpdatedCharacterClass;

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
        Injector injector = Guice.createInjector(new CharacterClassModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        GetUpdatedCharacterClass.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        getUpdatedCharacterClass = injector.getInstance(GetUpdatedCharacterClass.class);
    }

    @Test
    public void shouldReturnCharacterClass() {
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacterClass();
        UpdatedCharacterClassResponse updatedCharacterClassResponse = mockResponseAndReturnUpdatedCharacterClassResponse(operatorResponseQuery);
        Assertions.assertNotNull(updatedCharacterClassResponse.getCharacterClass(), "CharacterClass null.");
    }

    @Test
    public void shouldReturnEmptyCharacterClassWhenEmptyResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        UpdatedCharacterClassResponse updatedCharacterClassResponse = mockResponseAndReturnUpdatedCharacterClassResponse(operatorResponseQuery);
        Assertions.assertNull(updatedCharacterClassResponse.getCharacterClass(), "CharacterClass not null.");
    }

    @Test
    public void shouldReturnEmptyCharacterClassWhenNullResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        UpdatedCharacterClassResponse updatedCharacterClassResponse = mockResponseAndReturnUpdatedCharacterClassResponse(operatorResponseQuery);
        Assertions.assertNull(updatedCharacterClassResponse.getCharacterClass(), "CharacterClass not null.");
    }

    private OperatorResponseQuery createMockResponseWithCharacterClass() {
        String queryId = "123";
        ObjectMapper objectMapper = new ObjectMapper();
        String characterClassJson;
        try {
            characterClassJson = objectMapper.writeValueAsString(CharacterClass
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            characterClassJson = "{}";
        }
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(characterClassJson)
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

    private UpdatedCharacterClassResponse mockResponseAndReturnUpdatedCharacterClassResponse(OperatorResponseQuery operatorResponseQuery) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
        UpdatedCharacterClassRequest updatedCharacterClassRequest = UpdatedCharacterClassRequest
                .builder()
                .characterClass(CharacterClass
                        .builder()
                        .build())
                .build();
        return getUpdatedCharacterClass.getUpdatedCharacterClassResponse(updatedCharacterClassRequest);
    }
}