import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SuitType;
import cs3500.freecell.model.hw02.ValueType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the abstract testing class for FreecellGame; unit tests to ensure that shared tests
 * between SimpleFreecellModels and MultiFreecellModels run.
 */
public abstract class FreecellGameTest {

  FreecellModel randomModel;
  FreecellModel standardModel;
  FreecellModel singleModel;

  List<Card> randomDeck;
  List<Card> standardDeck;
  List<Card> singleDeck;
  List<Card> badDeckOne;
  List<Card> badDeckTwo;
  Set<Card> duplicateChecker;


  /**
   * Returns a FreecellModel depending on the type of tests you want to run.
   *
   * @return the correct FreecellModel.
   */
  protected abstract FreecellModel generate();

  @Before
  public void init() {

    randomModel = generate();
    standardModel = generate();
    singleModel = generate();

    randomDeck = this.randomModel.getDeck();
    standardDeck = this.standardModel.getDeck();
    singleDeck = this.singleModel.getDeck();
    duplicateChecker = new HashSet<Card>();

    badDeckOne = new ArrayList<Card>();
    for (int i = 0; i < 51; i++) {
      this.badDeckOne.add(new Card(ValueType.TWO, SuitType.HEARTS));
    }

    badDeckTwo = new ArrayList<Card>();
    for (int i = 0; i < 52; i++) {
      this.badDeckOne.add(new Card(ValueType.TWO, SuitType.HEARTS));
    }


  }

  // Test the FreecellGame's getDeck method, which creates a valid deck
  @Test
  public void testGetDeck() {

    // checks to see if the size of the deck is 52
    assertEquals(52, randomDeck.size());

    // checks to see if all of the cards are unique
    for (Card c : randomDeck) {
      assertEquals(true, duplicateChecker.add(c));
    }

    duplicateChecker.clear();
    // checks to see if the size of the deck is 52
    assertEquals(52, standardDeck.size());
    // checks to see if all of the cards are unique
    for (Card c : standardDeck) {
      assertEquals(true, duplicateChecker.add(c));
    }
  }

  // Test the FreecellGame's startGame method, which shuffles
  // and splits the deck into cascade piles
  @Test
  public void testStartGameRandomDeck() {
    this.randomModel.startGame(this.randomDeck, 8, 4, true);
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    assertEquals(52, randomDeck.size());

    for (Card c : randomDeck) {
      assertEquals(true, duplicateChecker.add(c));
    }

    // checks the first cards of a shuffled model and a non-shuffled model to check
    // that the first card in the cascade is not the same
    assertNotEquals(this.randomModel.getCascadeCardAt(0, 0),
        this.standardModel.getCascadeCardAt(0, 0));

    assertEquals(8, randomModel.getNumCascadePiles());
    assertEquals(4, randomModel.getNumOpenPiles());

    assertEquals(0, this.randomModel.getNumCardsInFoundationPile(0));
    assertEquals(0, this.randomModel.getNumCardsInFoundationPile(1));
    assertEquals(0, this.randomModel.getNumCardsInFoundationPile(2));
    assertEquals(0, this.randomModel.getNumCardsInFoundationPile(3));
    assertEquals(7, this.randomModel.getNumCardsInCascadePile(0));
    assertEquals(7, this.randomModel.getNumCardsInCascadePile(1));
    assertEquals(7, this.randomModel.getNumCardsInCascadePile(2));
    assertEquals(7, this.randomModel.getNumCardsInCascadePile(3));
    assertEquals(6, this.randomModel.getNumCardsInCascadePile(4));
    assertEquals(6, this.randomModel.getNumCardsInCascadePile(5));
    assertEquals(6, this.randomModel.getNumCardsInCascadePile(6));
    assertEquals(6, this.randomModel.getNumCardsInCascadePile(7));
    assertEquals(0, this.randomModel.getNumCardsInOpenPile(0));
    assertEquals(0, this.randomModel.getNumCardsInOpenPile(1));
    assertEquals(0, this.randomModel.getNumCardsInOpenPile(2));
    assertEquals(0, this.randomModel.getNumCardsInOpenPile(3));
  }


  // Test the FreecellGame's startGame method,
  // and splits the deck into cascade piles
  @Test
  public void testStartGameStandardDeck() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);

    assertEquals(52, this.standardDeck.size());

    for (Card c : standardDeck) {
      assertEquals(true, duplicateChecker.add(c));
    }

    // the first card in the first cascade pile should always be a 2 of hearts,
    // and the last card in the last cascade pile should always be an ace of spades
    assertEquals(new Card(ValueType.TWO, SuitType.HEARTS),
        this.standardModel.getCascadeCardAt(0, 0));
    assertEquals(new Card(ValueType.ACE, SuitType.SPADES),
        this.standardModel.getCascadeCardAt(3, 6));

    assertEquals(8, standardModel.getNumCascadePiles());
    assertEquals(4, standardModel.getNumOpenPiles());

    assertEquals(0, this.standardModel.getNumCardsInFoundationPile(0));
    assertEquals(0, this.standardModel.getNumCardsInFoundationPile(1));
    assertEquals(0, this.standardModel.getNumCardsInFoundationPile(2));
    assertEquals(0, this.standardModel.getNumCardsInFoundationPile(3));
    assertEquals(7, this.standardModel.getNumCardsInCascadePile(0));
    assertEquals(7, this.standardModel.getNumCardsInCascadePile(1));
    assertEquals(7, this.standardModel.getNumCardsInCascadePile(2));
    assertEquals(7, this.standardModel.getNumCardsInCascadePile(3));
    assertEquals(6, this.standardModel.getNumCardsInCascadePile(4));
    assertEquals(6, this.standardModel.getNumCardsInCascadePile(5));
    assertEquals(6, this.standardModel.getNumCardsInCascadePile(6));
    assertEquals(6, this.standardModel.getNumCardsInCascadePile(7));
    assertEquals(0, this.standardModel.getNumCardsInOpenPile(0));
    assertEquals(0, this.standardModel.getNumCardsInOpenPile(1));
    assertEquals(0, this.standardModel.getNumCardsInOpenPile(2));
    assertEquals(0, this.standardModel.getNumCardsInOpenPile(3));

    // Test startGame twice in a row
    this.standardModel.startGame(this.standardDeck, 4, 1, false);

    // the first card in the first cascade pile should always be a 2 of hearts,
    // and the last card in the last cascade pile should always be an ace of spades
    assertEquals(new Card(ValueType.TWO, SuitType.HEARTS),
        this.standardModel.getCascadeCardAt(0, 0));
    assertEquals(new Card(ValueType.ACE, SuitType.SPADES),
        this.standardModel.getCascadeCardAt(3, 12));

    assertEquals(4, standardModel.getNumCascadePiles());
    assertEquals(1, standardModel.getNumOpenPiles());

    assertEquals(0, this.standardModel.getNumCardsInFoundationPile(0));
    assertEquals(0, this.standardModel.getNumCardsInFoundationPile(1));
    assertEquals(0, this.standardModel.getNumCardsInFoundationPile(2));
    assertEquals(0, this.standardModel.getNumCardsInFoundationPile(3));
    assertEquals(13, this.standardModel.getNumCardsInCascadePile(0));
    assertEquals(13, this.standardModel.getNumCardsInCascadePile(1));
    assertEquals(13, this.standardModel.getNumCardsInCascadePile(2));
    assertEquals(13, this.standardModel.getNumCardsInCascadePile(3));

    assertEquals(0, this.standardModel.getNumCardsInOpenPile(0));

  }


  // Test startGame with a deck of 51 cards
  @Test(expected = IllegalArgumentException.class)
  public void testNotEnoughCards() {

    this.randomModel.startGame(this.badDeckOne, 8, 4, true);
  }


  // Test startGame with a deck of 52 card duplicates
  @Test(expected = IllegalArgumentException.class)
  public void testDuplicatesStartGame() {

    this.randomModel.startGame(this.badDeckTwo, 8, 4, true);
  }

  // Test startGame with a null deck
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNullDeck() {
    this.randomModel.startGame(null, 4, 4, true);
  }

  // Test startGame with a invalid number of cascade piles
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameInvalidNumCascadePiles() {
    this.randomModel.startGame(this.randomDeck, 2, 4, true);
  }

  // Test startGame with a invalid number of open piles
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameInvalidNumOpenPiles() {
    this.randomModel.startGame(this.randomDeck, 8, -3, true);
  }


  // Test if the game is over.
  @Test
  public void testIsGameOver() {
    this.randomModel.startGame(this.randomDeck, 8, 4, true);
    assertEquals(false, this.randomModel.isGameOver());
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    assertEquals(false, this.standardModel.isGameOver());

    this.singleModel.startGame(this.singleDeck, 52, 4, false);

    for (int i = 0; i < 4; i++) {
      this.singleModel.move(PileType.CASCADE, -1 + 13 * (i + 1), 0, PileType.FOUNDATION, i);
    }

    for (int i = 0; i < 12; i++) {
      this.singleModel.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
      this.singleModel.move(PileType.CASCADE, i + 13, 0, PileType.FOUNDATION, 1);
      this.singleModel.move(PileType.CASCADE, i + 26, 0, PileType.FOUNDATION, 2);
      this.singleModel.move(PileType.CASCADE, i + 39, 0, PileType.FOUNDATION, 3);
    }

    // after placing all cards into foundation piles
    assertEquals(true, this.singleModel.isGameOver());


  }

  // Tests a simple Freecell model's ability to move cards properly. We test with
  // a non-shuffled deck because we will know what cards can move where.
  @Test
  public void testMoveStandard() {
    this.standardModel.startGame(this.standardDeck, 52, 4, false);
    this.standardModel.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 2);
    // move works cascade -> foundation pile
    assertEquals(new Card(ValueType.ACE, SuitType.HEARTS),
        this.standardModel.getFoundationCardAt(2, 0));
    // move works cascade -> open pile
    this.standardModel.move(PileType.CASCADE, 1, 0, PileType.OPEN, 1);
    assertEquals(new Card(ValueType.THREE, SuitType.HEARTS),
        this.standardModel.getOpenCardAt(1));
    // move works cascade-> cascade pile
    this.standardModel.move(PileType.CASCADE, 10, 0, PileType.CASCADE, 50);
    assertEquals(2,
        this.standardModel.getNumCardsInCascadePile(50));
    // move works open-> foundation pile
    this.standardModel.move(PileType.CASCADE, 38, 0, PileType.OPEN, 0);
    this.standardModel.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 3);
    assertEquals(new Card(ValueType.ACE, SuitType.DIAMONDS),
        this.standardModel.getFoundationCardAt(3, 0));
    // move works open-> open pile
    this.standardModel.move(PileType.OPEN, 1, 0, PileType.OPEN, 3);
    assertEquals(new Card(ValueType.THREE, SuitType.HEARTS),
        this.standardModel.getOpenCardAt(3));
    // move works open-> cascade pile
    this.standardModel.move(PileType.OPEN, 3, 0, PileType.CASCADE, 15);
    assertEquals(new Card(ValueType.THREE, SuitType.HEARTS),
        this.standardModel.getCascadeCardAt(15, 1));
  }


  // Test move before starting the game
  @Test(expected = IllegalStateException.class)
  public void testMoveBeforeStart() {

    this.standardModel.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 2);

  }


  // Test move with an illegal source
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSource() {

    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.move(null, 3, 6, PileType.FOUNDATION, 2);

  }


  // Test move with an illegal destination
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDestination() {

    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.move(PileType.CASCADE, 3, 6, null, 2);

  }

  // Test move with another illegal source, the foundation
  @Test(expected = IllegalArgumentException.class)
  public void testFromFoundation() {

    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.move(PileType.FOUNDATION, 3, 6, PileType.OPEN, 2);

  }


  // Test moving from cascade when you are trying to move the last card but it's not allowed
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveFromCascadeToFoundation() {

    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.move(PileType.CASCADE, 2, 6, PileType.FOUNDATION, 2);

  }


  // Test moving from open when there's no card there
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveFromOpen() {

    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.move(PileType.OPEN, 3, 0, PileType.FOUNDATION, 2);

  }

  // Test moving from open to the cascade as an illegal move
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalOpenToCascade() {

    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.move(PileType.CASCADE, 2, 6, PileType.OPEN, 2);
    this.standardModel.move(PileType.OPEN, 2, 0, PileType.CASCADE, 7);
  }

  // Test moving from open to foundation as an illegal move
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalOpenToFoundation() {

    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.
        move(PileType.CASCADE, 2, 6, PileType.OPEN, 2);
    this.standardModel.
        move(PileType.OPEN, 2, 0, PileType.FOUNDATION, 7);
  }

  // Test moving from cascade to open when a card is already at the open pile
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCascadeToOpen() {
    this.standardModel.startGame(this.standardDeck, 52, 4, false);
    this.standardModel.
        move(PileType.CASCADE, 2, 0, PileType.OPEN, 2);
    this.standardModel.
        move(PileType.CASCADE, 3, 0, PileType.OPEN, 2);
  }


  // Test getNumCardsInFoundationPile method
  @Test
  public void testGetNumCardsInFoundationPile() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    assertEquals(0, this.standardModel.getNumCardsInFoundationPile(0));
    assertEquals(0, this.standardModel.getNumCardsInFoundationPile(1));
    assertEquals(0, this.standardModel.getNumCardsInFoundationPile(2));
    assertEquals(0, this.standardModel.getNumCardsInFoundationPile(3));
    // move a card to the foundation and check the number of cards in that foundation pile
    this.standardModel.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    assertEquals(1, this.standardModel.getNumCardsInFoundationPile(0));

  }

  // Test the getNumInFoundationPile method before the game starts
  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInFoundationPileBeforeStart() {

    this.randomModel.getNumCardsInFoundationPile(0);

  }


  // Test the getNumInFoundationPile method at an invalid index
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInFoundationPileInvalidIndex() {
    this.randomModel.startGame(this.randomDeck, 8, 4, true);
    this.randomModel.getNumCardsInFoundationPile(4);

  }

  // Test the getNumInFoundationPile method at a negative index
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInFoundationPileNegativeIndex() {
    this.randomModel.startGame(this.randomDeck, 8, 4, true);
    this.randomModel.getNumCardsInFoundationPile(-3);

  }


  // Tests the getNumCascadePiles method
  @Test
  public void testGetNumCascadePiles() {
    // tests the method before the game starts
    assertEquals(-1, this.randomModel.getNumCascadePiles());
    // tests the method after the game starts
    this.randomModel.startGame(this.randomDeck, 8, 4, true);
    assertEquals(8, this.randomModel.getNumCascadePiles());

    this.standardModel.startGame(this.standardDeck, 52, 4, false);
    assertEquals(52, this.standardModel.getNumCascadePiles());
  }


  // Test the getNumCardsInCascadePile method
  @Test
  public void testGetNumCardsInCascadePile() {
    // testing extra cards
    this.randomModel.startGame(this.randomDeck, 8, 4, true);
    assertEquals(7, this.randomModel.getNumCardsInCascadePile(0));
    assertEquals(7, this.randomModel.getNumCardsInCascadePile(1));
    assertEquals(7, this.randomModel.getNumCardsInCascadePile(2));
    assertEquals(7, this.randomModel.getNumCardsInCascadePile(3));
    assertEquals(6, this.randomModel.getNumCardsInCascadePile(4));
    assertEquals(6, this.randomModel.getNumCardsInCascadePile(5));
    assertEquals(6, this.randomModel.getNumCardsInCascadePile(6));
    assertEquals(6, this.randomModel.getNumCardsInCascadePile(7));

    // testing no extra cards
    this.standardModel.startGame(this.randomDeck, 4, 4, false);
    assertEquals(13, this.standardModel.getNumCardsInCascadePile(0));
    assertEquals(13, this.standardModel.getNumCardsInCascadePile(1));
    assertEquals(13, this.standardModel.getNumCardsInCascadePile(2));
    assertEquals(13, this.standardModel.getNumCardsInCascadePile(3));

  }


  // Test getNumCardsInCascadePile method before the game starts
  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInCascadePileBeforeStart() {

    this.randomModel.getNumCardsInCascadePile(0);


  }

  // Test getNumCardsInCascadePile method at an invalid index
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInCascadePileInvalidIndex() {
    this.randomModel.startGame(this.randomDeck, 8, 4, true);
    this.randomModel.getNumCardsInCascadePile(8);


  }

  // Test getNumCardsInCascadePile method at a negative index
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInCascadePileNegativeIndex() {
    this.randomModel.startGame(this.randomDeck, 8, 4, true);
    this.randomModel.getNumCardsInCascadePile(-3);


  }

  // Test the getNumCardsInOpenPile method
  @Test
  public void testGetNumCardsInOpenPile() {
    this.randomModel.startGame(this.randomDeck, 8, 4, true);
    assertEquals(0, this.randomModel.getNumCardsInOpenPile(0));
    assertEquals(0, this.randomModel.getNumCardsInOpenPile(1));
    assertEquals(0, this.randomModel.getNumCardsInOpenPile(2));
    assertEquals(0, this.randomModel.getNumCardsInOpenPile(3));

    this.standardModel.startGame(this.standardDeck, 8, 4, true);
    this.standardModel.move(PileType.CASCADE, 3, 6, PileType.OPEN, 1);
    assertEquals(1, this.standardModel.getNumCardsInOpenPile(1));
  }

  // Test the getNumCardsInOpenPile before the game starts
  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInOpenPileBeforeStart() {
    this.randomModel.getNumCardsInOpenPile(0);

  }

  // Test the getNumCardsInOpenPile at a invalid index
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInOpenPileInvalidIndex() {
    this.randomModel.startGame(this.randomDeck, 8, 4, true);
    this.randomModel.getNumCardsInOpenPile(4);

  }

  // Test the getNumCardsInOpenPile at a negative index
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInOpenPileNegativeIndex() {
    this.randomModel.startGame(this.randomDeck, 8, 4, true);
    this.randomModel.getNumCardsInOpenPile(-3);

  }


  // Tests the getNumOpenPiles method before and after the game has started.
  @Test
  public void testGetNumOpenPiles() {
    // Before the game has started
    assertEquals(-1, this.randomModel.getNumOpenPiles());
    this.randomModel.startGame(this.randomDeck, 8, 4, true);
    // After the game has started
    assertEquals(4, this.randomModel.getNumOpenPiles());

    // Before the game has started
    assertEquals(-1, this.standardModel.getNumOpenPiles());
    this.randomModel.startGame(this.standardDeck, 4, 2, false);
    // After the game has started
    assertEquals(2, this.randomModel.getNumOpenPiles());
  }


  // Test getFoundationCardAt method
  @Test
  public void testGetFoundationCardAt() {

    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 3);
    assertEquals(new Card(ValueType.ACE, SuitType.SPADES),
        this.standardModel.getFoundationCardAt(3, 0));
  }

  // Test getFoundationCardAt method before the game starts
  @Test(expected = IllegalStateException.class)
  public void testGetFoundationCardAtBeforeStart() {
    this.standardModel.getFoundationCardAt(3, 5);
  }

  // Test getFoundationCardAt method at an invalid index
  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtInvalidCardIndex() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.getFoundationCardAt(3, 9);
  }

  // Test getFoundationCardAt method at a negative index
  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtNegativeCardIndex() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.getFoundationCardAt(3, -13);
  }

  // Test getFoundationCardAt method at an invalid pile index
  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtInvalidPileIndex() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.getFoundationCardAt(5, 0);
  }

  // Test getFoundationCardAt method at a negative pile inde
  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtNegativePileIndex() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.getFoundationCardAt(-3, 0);
  }


  // Test the getCascadeCardAt method
  @Test
  public void testGetCascadeCardAt() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    assertEquals(new Card(ValueType.TWO, SuitType.HEARTS),
        standardModel.getCascadeCardAt(0, 0));
    assertEquals(new Card(ValueType.ACE, SuitType.SPADES),
        standardModel.getCascadeCardAt(3, 6));
    assertEquals(new Card(ValueType.SEVEN, SuitType.CLUBS),
        standardModel.getCascadeCardAt(2, 2));
  }

  // Test the getCascadeCardAt method before the game has started
  @Test(expected = IllegalStateException.class)
  public void testGetCascadeCardAtBeforeStart() {
    standardModel.getCascadeCardAt(0, 0);
  }

  // Test the getCascadeCardAt method at an invalid pile index
  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAtInvalidPileIndex() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    standardModel.getCascadeCardAt(8, 1);
  }

  // Test the getCascadeCardAt method at a negative pile index
  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAtNegativePileIndex() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    standardModel.getCascadeCardAt(-69, 1);
  }


  // Test the getCascadeCardAt method at an invalid card index
  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAtInvalidCardIndex() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    standardModel.getCascadeCardAt(4, 17);
  }

  // Test the getCascadeCardAt method at a negative card index
  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAtNegativeCardIndex() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    standardModel.getCascadeCardAt(4, -3);
  }

  // Test the getOpenCardAt method
  @Test
  public void testGetOpenCardAt() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.move(PileType.CASCADE, 2, 6, PileType.OPEN, 3);
    assertEquals(new Card(ValueType.KING, SuitType.SPADES),
        this.standardModel.getOpenCardAt(3));
    this.standardModel.move(PileType.CASCADE, 3, 6, PileType.OPEN, 2);
    assertEquals(new Card(ValueType.ACE, SuitType.SPADES),
        this.standardModel.getOpenCardAt(2));
  }

  // Tests the getOpenCardAt method before the game has started
  @Test(expected = IllegalStateException.class)
  public void testGetOpenCardAtBeforeStart() {
    this.standardModel.getOpenCardAt(3);
  }

  // Tests the getOpenCardAt method when there isn't a card there
  @Test
  public void testGetOpenCardWhenNoCard() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    assertTrue(this.standardModel.getOpenCardAt(3) == null);
  }

  // Tests the getOpenCardAt method at an invalid index
  @Test(expected = IllegalArgumentException.class)
  public void testGetOpenCardInvalidIndex() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.getOpenCardAt(4);

  }

  // Tests the getOpenCardAt method at an invalid index
  @Test(expected = IllegalArgumentException.class)
  public void testGetOpenCardNegativeIndex() {
    this.standardModel.startGame(this.standardDeck, 8, 4, false);
    this.standardModel.getOpenCardAt(-3);

  }
}

