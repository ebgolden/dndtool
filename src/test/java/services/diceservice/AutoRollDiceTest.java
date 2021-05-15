package services.diceservice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import services.diceservice.module.DiceModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class AutoRollDiceTest {
    private AutoRollDice autoRollDice;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new DiceModule());
        autoRollDice = injector.getInstance(AutoRollDice.class);
    }

    @Test
    public void shouldReturnPositiveNonZeroDieRollWithinSize() {
        String[] encodedDice = new String[] {"1d6"};
        int dieSize = 6;
        AutoRollDiceRequest autoRollDiceRequest = createAutoRollDiceRequest(encodedDice);
        int[] diceRolls = getDiceRollsResponse(autoRollDiceRequest);
        Assertions.assertTrue(((diceRolls[0] <= dieSize) && (diceRolls[0] > 0)), "Dice rolls out of bounds.");
    }

    @Test
    public void shouldReturnTwoDiceRollsOfSameSize() {
        String[] encodedDice = new String[] {"2d6"};
        int amountOfDice = 2;
        AutoRollDiceRequest autoRollDiceRequest = createAutoRollDiceRequest(encodedDice);
        int[] diceRolls = getDiceRollsResponse(autoRollDiceRequest);
        Assertions.assertEquals(amountOfDice, diceRolls.length, "Unequal amount of dice rolls returned.");
    }

    @Test
    public void shouldReturnTwoDiceRollsOfDifferentSize() {
        String[] encodedDice = new String[] {"1d4", "1d6"};
        int amountOfDice = 2;
        AutoRollDiceRequest autoRollDiceRequest = createAutoRollDiceRequest(encodedDice);
        int[] diceRolls = getDiceRollsResponse(autoRollDiceRequest);
        Assertions.assertEquals(amountOfDice, diceRolls.length, "Unequal amount of dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyArrayForZeroDiceOfOneSize() {
        String[] encodedDice = new String[] {"0d4"};
        int amountOfDice = 0;
        AutoRollDiceRequest autoRollDiceRequest = createAutoRollDiceRequest(encodedDice);
        int[] diceRolls = getDiceRollsResponse(autoRollDiceRequest);
        Assertions.assertEquals(amountOfDice, diceRolls.length, "Unequal amount of dice rolls returned.");
    }

    @Test
    public void shouldReturnZeroDiceRollsOfOneSizeAndTwoOfOther() {
        String[] encodedDice = new String[] {"0d4", "2d6"};
        int amountOfDice = 2;
        AutoRollDiceRequest autoRollDiceRequest = createAutoRollDiceRequest(encodedDice);
        int[] diceRolls = getDiceRollsResponse(autoRollDiceRequest);
        Assertions.assertEquals(amountOfDice, diceRolls.length, "Unequal amount of dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyArrayOfDiceRolls() {
        String[] encodedDice = new String[] {};
        int amountOfDice = 0;
        AutoRollDiceRequest autoRollDiceRequest = createAutoRollDiceRequest(encodedDice);
        int[] diceRolls = getDiceRollsResponse(autoRollDiceRequest);
        Assertions.assertEquals(amountOfDice, diceRolls.length, "Unequal amount of dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyArrayOfDiceRollsForNullEncodedDice() {
        int amountOfDice = 0;
        AutoRollDiceRequest autoRollDiceRequest = createAutoRollDiceRequest(null);
        int[] diceRolls = getDiceRollsResponse(autoRollDiceRequest);
        Assertions.assertEquals(amountOfDice, diceRolls.length, "Unequal amount of dice rolls returned.");
    }

    @Test
    public void shouldReturnEmptyArrayOfDiceRollsForNullRequest() {
        int amountOfDice = 0;
        int[] diceRolls = getDiceRollsResponse(null);
        Assertions.assertEquals(amountOfDice, diceRolls.length, "Unequal amount of dice rolls returned.");
    }

    private AutoRollDiceRequest createAutoRollDiceRequest(String[] encodedDice) {
        return AutoRollDiceRequest
                .builder()
                .encodedDice(encodedDice)
                .build();
    }

    private int[] getDiceRollsResponse(AutoRollDiceRequest autoRollDiceRequest) {
        return autoRollDice.getDiceResponse(autoRollDiceRequest).getDiceRolls();
    }
}
