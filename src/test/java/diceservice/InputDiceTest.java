package diceservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class InputDiceTest {
    private InputDice inputDice;

    public InputDiceTest() {
        inputDice = new InputDice();
    }

    @Test
    public void shouldReturnSameDiceRolls() {
        int[] diceRolls = new int[] {1, 2, 3};
        InputDiceRequest inputDiceRequest = new InputDiceRequest();
        inputDiceRequest.diceRolls = diceRolls;
        DiceResponse expectedDiceResponse = new DiceResponse();
        expectedDiceResponse.diceRolls = diceRolls;
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).diceRolls, expectedDiceResponse.diceRolls), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyDiceRolls() {
        int[] diceRolls = new int[] {};
        InputDiceRequest inputDiceRequest = new InputDiceRequest();
        inputDiceRequest.diceRolls = diceRolls;
        DiceResponse expectedDiceResponse = new DiceResponse();
        expectedDiceResponse.diceRolls = diceRolls;
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).diceRolls, expectedDiceResponse.diceRolls), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnPositiveDiceRolls() {
        int[] diceRolls = new int[] {-1, -2, -3}, expectedDiceRolls = new int[] {1, 2, 3};
        InputDiceRequest inputDiceRequest = new InputDiceRequest();
        inputDiceRequest.diceRolls = diceRolls;
        DiceResponse expectedDiceResponse = new DiceResponse();
        expectedDiceResponse.diceRolls = expectedDiceRolls;
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).diceRolls, expectedDiceResponse.diceRolls), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnNonZeroDiceRolls() {
        int[] diceRolls = new int[] {0, 0, 0}, expectedDiceRolls = new int[] {1, 1, 1};
        InputDiceRequest inputDiceRequest = new InputDiceRequest();
        inputDiceRequest.diceRolls = diceRolls;
        DiceResponse expectedDiceResponse = new DiceResponse();
        expectedDiceResponse.diceRolls = expectedDiceRolls;
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).diceRolls, expectedDiceResponse.diceRolls), "Unequal dice rolls returned.");
    }

    @Test
    public void shouldReturnNonNullDiceRolls() {
        int[] diceRolls = null, expectedDiceRolls = new int[] {};
        InputDiceRequest inputDiceRequest = new InputDiceRequest();
        inputDiceRequest.diceRolls = diceRolls;
        DiceResponse expectedDiceResponse = new DiceResponse();
        expectedDiceResponse.diceRolls = expectedDiceRolls;
        Assertions.assertTrue(Arrays.equals(inputDice.getDiceResponse(inputDiceRequest).diceRolls, expectedDiceResponse.diceRolls), "Unequal dice rolls returned.");
    }
}
