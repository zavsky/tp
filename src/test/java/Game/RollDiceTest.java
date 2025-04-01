package Game;

import static org.junit.jupiter.api.Assertions.*;

import Exceptions.RolladieException;
import org.junit.jupiter.api.Test;

class RollDiceTest {

    @Test
    public void diceOutcome_ValidDiceValue_ReturnZero() throws RolladieException {
        int diceValue = 2;
        assertEquals(0, RollDice.diceOutcome(diceValue));
    }

    @Test
    public void diceOutcome_InvalidDiceValue_ExceptionThrown()throws RolladieException{
        int diceValue = 0;
        assertThrows(RolladieException.class,()->RollDice.diceOutcome(diceValue));
    }

}
