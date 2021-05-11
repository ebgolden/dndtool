package diceservice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import diceservice.module.DiceModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class InputDiceTest {
    private InputDice inputDice;

    public InputDiceTest() {
        Injector injector = Guice.createInjector(new DiceModule());
        inputDice = injector.getInstance(InputDice.class);
    }

    @Test
    public void shouldReturnSameDiceRolls() {
        int[] diceRolls = new int[] {1, 2, 3};
        InputDiceRequest inputDiceRequest = InputDiceRequest
                .builder()
                .diceRolls(diceRolls)
                .build();
        DiceResponse expectedDiceResponse = DiceResponse
                .builder()
                .diceRolls(diceRolls)
                .build();
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).getDiceRolls(), expectedDiceResponse.getDiceRolls()), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyDiceRolls() {
        int[] diceRolls = new int[] {};
        InputDiceRequest inputDiceRequest = InputDiceRequest
                .builder()
                .diceRolls(diceRolls)
                .build();
        DiceResponse expectedDiceResponse = DiceResponse
                .builder()
                .diceRolls(diceRolls)
                .build();
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).getDiceRolls(), expectedDiceResponse.getDiceRolls()), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnPositiveDiceRolls() {
        int[] diceRolls = new int[] {-1, -2, -3}, expectedDiceRolls = new int[] {1, 2, 3};
        InputDiceRequest inputDiceRequest = InputDiceRequest
                .builder()
                .diceRolls(diceRolls)
                .build();
        DiceResponse expectedDiceResponse = DiceResponse
                .builder()
                .diceRolls(expectedDiceRolls)
                .build();
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).getDiceRolls(), expectedDiceResponse.getDiceRolls()), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnNonZeroDiceRolls() {
        int[] diceRolls = new int[] {0, 0, 0}, expectedDiceRolls = new int[] {1, 1, 1};
        InputDiceRequest inputDiceRequest = InputDiceRequest
                .builder()
                .diceRolls(diceRolls)
                .build();
        DiceResponse expectedDiceResponse = DiceResponse
                .builder()
                .diceRolls(expectedDiceRolls)
                .build();
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).getDiceRolls(), expectedDiceResponse.getDiceRolls()), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnNonNullDiceRolls() {
        int[] diceRolls = null, expectedDiceRolls = new int[] {};
        InputDiceRequest inputDiceRequest = InputDiceRequest
                .builder()
                .diceRolls(diceRolls)
                .build();
        DiceResponse expectedDiceResponse = DiceResponse
                .builder()
                .diceRolls(expectedDiceRolls)
                .build();
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).getDiceRolls(), expectedDiceResponse.getDiceRolls()), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyDiceRollsForNullRequest() {
        int[] expectedDiceRolls = new int[] {};
        InputDiceRequest inputDiceRequest = null;
        DiceResponse expectedDiceResponse = DiceResponse
                .builder()
                .diceRolls(expectedDiceRolls)
                .build();
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).getDiceRolls(), expectedDiceResponse.getDiceRolls()), "Unequal dice rolls returned.");
    }
}