package services;

import commonobjects.DataOperator;
import commonobjects.GlobalNetworkOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.partyservice.JoinParty;

@ExtendWith(MockitoExtension.class)
public class Test {
    @Mock
    private DataOperator dataOperator;

    @BeforeEach
    public void setup() {
        dataOperator = new GlobalNetworkOperator();
    }

    @org.junit.jupiter.api.Test
    public void shouldReturnSuccessfulMessage() {
        dataOperator.sendRequestJson(JoinParty.class, "I'm a code ninja");
        String responseJson = dataOperator.getResponseJson();
        Assertions.assertNotNull(responseJson, "Response json null.");
    }
}
