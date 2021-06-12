import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.MultiFreecellModel;
import org.junit.Test;


/**
 * Test class for MultiFreecellModel; unit tests to ensure that MultiFreecellModel methods work as
 * intended.
 */
public class MultiFreecellModelTest extends FreecellGameTest {


  @Override
  protected FreecellModel generate() {
    return new MultiFreecellModel();
  }

  // Tests moving from a Cascade to a Cascade when the build is not valid.
  @Test(expected = IllegalArgumentException.class)
  public void testCascadeToCascadeWhenNotValidBuild() {
    this.standardModel.startGame(this.standardDeck, 4, 4, false);
    this.standardModel.move(PileType.OPEN, 4, 1, PileType.CASCADE, 3);
  }


  // Tests moving a single card from Cascade to Cascade
  @Test
  public void testCascadeToCascadeSingleCard() {
    this.standardModel.startGame(this.standardDeck, 52, 4, false);
    this.standardModel.move(PileType.CASCADE, 27, 0, PileType.CASCADE, 41);
    assertEquals(2, this.standardModel.getNumCardsInCascadePile(41));
  }


  // Tests moving a valid build to a cascade pile that is empty
  @Test
  public void testValidBuildToEmpty() {
    this.standardModel.startGame(this.standardDeck, 52, 4, false);
    this.standardModel.move(PileType.CASCADE, 27, 0, PileType.CASCADE, 41);
    this.standardModel.move(PileType.CASCADE, 41, 0, PileType.CASCADE, 27);
    assertEquals(2, this.standardModel.getNumCardsInCascadePile(27));

  }

  // Tests moving a valid build to a cascade pile that is not empty
  @Test
  public void testValidBuildToNotEmpty() {
    // moves to a 3
    this.standardModel.startGame(this.standardDeck, 52, 4, false);
    this.standardModel.move(PileType.CASCADE, 27, 0, PileType.CASCADE, 41);
    this.standardModel.move(PileType.CASCADE, 41, 0, PileType.CASCADE, 3);
    assertEquals(3, this.standardModel.getNumCardsInCascadePile(3));

  }

  // Tests moving a valid build to a cascade card that it can't be moved to.
  @Test(expected = IllegalArgumentException.class)
  public void testValidBuildToInvalidCard() {

    this.standardModel.startGame(this.standardDeck, 52, 4, false);
    this.standardModel.move(PileType.CASCADE, 27, 0, PileType.CASCADE, 41);
    this.standardModel.move(PileType.CASCADE, 41, 0, PileType.CASCADE, 15);


  }

  // Tests moving a valid build to the open pile.
  @Test(expected = IllegalArgumentException.class)
  public void testValidBuildToOpen() {

    this.standardModel.startGame(this.standardDeck, 52, 4, false);
    this.standardModel.move(PileType.CASCADE, 27, 0, PileType.CASCADE, 41);
    this.standardModel.move(PileType.CASCADE, 41, 0, PileType.OPEN, 0);


  }

  // Tests moving a valid build to the foundation pile.
  @Test(expected = IllegalArgumentException.class)
  public void testValidBuildToFoundation() {

    this.standardModel.startGame(this.standardDeck, 52, 4, false);
    this.standardModel.move(PileType.CASCADE, 27, 0, PileType.CASCADE, 41);
    this.standardModel.move(PileType.CASCADE, 41, 0, PileType.FOUNDATION, 0);


  }

  @Test(expected = IllegalArgumentException.class)
  // Tests moving a valid build when amount of cards moved is equal to or over max
  public void testValidBuildWhenEqualToOrOverMax() {

    this.standardModel.startGame(this.standardDeck, 26, 1, false);
    this.standardModel.move(PileType.CASCADE, 6, 1, PileType.CASCADE, 20);
    this.standardModel.move(PileType.CASCADE, 1, 1, PileType.OPEN, 0);
    this.standardModel.move(PileType.CASCADE, 20, 1, PileType.CASCADE, 8);
  }

}