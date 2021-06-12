import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import org.junit.Test;


/**
 * Test class for SimpleFreecellModel; unit tests to ensure that SimpleFreecellModel methods work as
 * intended.
 */
public class SimpleFreecellModelTest extends FreecellGameTest {


  @Override
  protected FreecellModel generate() {
    return new SimpleFreecellModel();
  }


  // Test moving from cascade when you are not trying to move the last card
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveFromCascade() {

    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.move(PileType.CASCADE, 3, 5, PileType.OPEN, 2);

  }

}