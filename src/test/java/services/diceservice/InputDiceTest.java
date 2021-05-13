package services.diceservice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import services.diceservice.module.DiceModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class InputDiceTest {
    private InputDice inputDice;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new DiceModule());
        inputDice = injector.getInstance(InputDice.class);
    }

    @Test
    public void shouldReturnSameDiceRolls() {
        int[] diceRolls = new int[] {1, 2, 3};
        InputDiceRequest inputDiceRequest = createInputDiceRequest(diceRolls);
        DiceResponse expectedDiceResponse = createDiceResponse(diceRolls);
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).getDiceRolls(), expectedDiceResponse.getDiceRolls()), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyDiceRolls() {
        int[] diceRolls = new int[] {};
        InputDiceRequest inputDiceRequest = createInputDiceRequest(diceRolls);
        DiceResponse expectedDiceResponse = createDiceResponse(diceRolls);
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).getDiceRolls(), expectedDiceResponse.getDiceRolls()), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnPositiveDiceRolls() {
        int[] diceRolls = new int[] {-1, -2, -3}, expectedDiceRolls = new int[] {1, 2, 3};
        InputDiceRequest inputDiceRequest = createInputDiceRequest(diceRolls);
        DiceResponse expectedDiceResponse = createDiceResponse(expectedDiceRolls);
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).getDiceRolls(), expectedDiceResponse.getDiceRolls()), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnNonZeroDiceRolls() {
        int[] diceRolls = new int[] {0, 0, 0}, expectedDiceRolls = new int[] {1, 1, 1};
        InputDiceRequest inputDiceRequest = createInputDiceRequest(diceRolls);
        DiceResponse expectedDiceResponse = createDiceResponse(expectedDiceRolls);
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).getDiceRolls(), expectedDiceResponse.getDiceRolls()), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnNonNullDiceRolls() {
        int[] expectedDiceRolls = new int[] {};
        InputDiceRequest inputDiceRequest = createInputDiceRequest(null);
        DiceResponse expectedDiceResponse = createDiceResponse(expectedDiceRolls);
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).getDiceRolls(), expectedDiceResponse.getDiceRolls()), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyDiceRollsForNullRequest() {
        int[] expectedDiceRolls = new int[] {};
        DiceResponse expectedDiceResponse = createDiceResponse(expectedDiceRolls);
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(null).getDiceRolls(), expectedDiceResponse.getDiceRolls()), "Unequal dice rolls returned.");
    }

    private InputDiceRequest createInputDiceRequest(int[] diceRolls) {
        return InputDiceRequest
                .builder()
                .diceRolls(diceRolls)
                .build();
    }

    private DiceResponse createDiceResponse(int[] expectedDiceRolls) {
        return DiceResponse
                .builder()
                .diceRolls(expectedDiceRolls)
                .build();
    }
}