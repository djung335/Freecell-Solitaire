import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.hw02.SuitType;
import org.junit.Test;

/**
 * Test class for SuitType; unit tests to ensure that SuitType methods work as intended.
 */
public class SuitTypeTest {

  SuitType diamonds = SuitType.DIAMONDS;
  SuitType clubs = SuitType.CLUBS;
  SuitType hearts = SuitType.HEARTS;
  SuitType spades = SuitType.SPADES;

  // Tests the toString method of the SuitType class.
  @Test
  public void testToString() {
    assertEquals("♦", this.diamonds.toString());
    assertEquals("♣", this.clubs.toString());
    assertEquals("♥", this.hearts.toString());
    assertEquals("♠", this.spades.toString());

  }


}