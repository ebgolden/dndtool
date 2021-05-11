package diceservice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import diceservice.module.DiceModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutoRollDiceTest {
    private AutoRollDice autoRollDice;

    public AutoRollDiceTest() {
        Injector injector = Guice.createInjector(new DiceModule());
        autoRollDice = injector.getInstance(AutoRollDice.class);
    }

    @Test
    public void shouldReturnPositiveNonZeroDieRollWithinSize() {
        String[] encodedDice = new String[] {"1d6"};
        int dieSize = 6;
        AutoRollDiceRequest autoRollDiceRequest = AutoRollDiceRequest
                .builder()
                .encodedDice(encodedDice)
                .build();
        int[] diceRolls = autoRollDice.getDiceResponse(autoRollDiceRequest).getDiceRolls();
        Assertions.assertTrue(((diceRolls[0] <= dieSize) && (diceRolls[0] > 0)), "Dice rolls out of bounds.");
    }

    @Test
    public void shouldReturnTwoDiceRollsOfSameSize() {
        String[] encodedDice = new String[] {"2d6"};
        int amountOfDice = 2;
        AutoRollDiceRequest autoRollDiceRequest = AutoRollDiceRequest
                .builder()
                .encodedDice(encodedDice)
                .build();
        int[] diceRolls = autoRollDice.getDiceResponse(autoRollDiceRequest).getDiceRolls();
        Assertions.assertEquals(diceRolls.length, amountOfDice, "Unequal amount of dice rolls returned.");
    }

    @Test
    public void shouldReturnTwoDiceRollsOfDifferentSize() {
        String[] encodedDice = new String[] {"1d4", "1d6"};
        int amountOfDice = 2;
        AutoRollDiceRequest autoRollDiceRequest = AutoRollDiceRequest
                .builder()
                .encodedDice(encodedDice)
                .build();
        int[] diceRolls = autoRollDice.getDiceResponse(autoRollDiceRequest).getDiceRolls();
        Assertions.assertEquals(diceRolls.length, amountOfDice, "Unequal amount of dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyArrayForZeroDiceOfOneSize() {
        String[] encodedDice = new String[] {"0d4"};
        int amountOfDice = 0;
        AutoRollDiceRequest autoRollDiceRequest = AutoRollDiceRequest
                .builder()
                .encodedDice(encodedDice)
                .build();
        int[] diceRolls = autoRollDice.getDiceResponse(autoRollDiceRequest).getDiceRolls();
        Assertions.assertEquals(diceRolls.length, amountOfDice, "Unequal amount of dice rolls returned.");
    }

    @Test
    public void shouldReturnZeroDiceRollsOfOneSizeAndTwoOfOther() {
        String[] encodedDice = new String[] {"0d4", "2d6"};
        int amountOfDice = 2;
        AutoRollDiceRequest autoRollDiceRequest = AutoRollDiceRequest
                .builder()
                .encodedDice(encodedDice)
                .build();
        int[] diceRolls = autoRollDice.getDiceResponse(autoRollDiceRequest).getDiceRolls();
        Assertions.assertEquals(diceRolls.length, amountOfDice, "Unequal amount of dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyArrayOfDiceRolls() {
        String[] encodedDice = new String[] {};
        int amountOfDice = 0;
        AutoRollDiceRequest autoRollDiceRequest = AutoRollDiceRequest
                .builder()
                .encodedDice(encodedDice)
                .build();
        int[] diceRolls = autoRollDice.getDiceResponse(autoRollDiceRequest).getDiceRolls();
        Assertions.assertEquals(diceRolls.length, amountOfDice, "Unequal amount of dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyArrayOfDiceRollsForNullEncodedDice() {
        String[] encodedDice = null;
        int amountOfDice = 0;
        AutoRollDiceRequest autoRollDiceRequest = AutoRollDiceRequest
                .builder()
                .encodedDice(encodedDice)
                .build();
        int[] diceRolls = autoRollDice.getDiceResponse(autoRollDiceRequest).getDiceRolls();
        Assertions.assertEquals(diceRolls.length, amountOfDice, "Unequal amount of dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyArrayOfDiceRollsForNullRequest() {
        int amountOfDice = 0;
        AutoRollDiceRequest autoRollDiceRequest = null;
        int[] diceRolls = autoRollDice.getDiceResponse(autoRollDiceRequest).getDiceRolls();
        Assertions.assertEquals(diceRolls.length, amountOfDice, "Unequal amount of dice rolls returned.");
    }
}
