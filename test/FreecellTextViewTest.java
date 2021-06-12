import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

/**
 * Test class for FreecellTextView; unit tests to ensure that FreecellTextView methods work as
 * intended.
 */
public class FreecellTextViewTest {

  ByteArrayOutputStream bytes = new ByteArrayOutputStream();
  Appendable out;
  Readable in;

  Appendable mockAppendable = new MockAppendable();

  FreecellModel model = new SimpleFreecellModel();
  List<Card> deck;
  FreecellView view = new FreecellTextView(model);
  StringBuilder s = new StringBuilder();
  FreecellView viewWithAppendable = new FreecellTextView(model, s);
  FreecellView viewWithMock = new FreecellTextView(model, mockAppendable);


  // Test the TextView's toString method,
  // which provides a visual representation of the game
  @Test
  public void testToString() {
    deck = model.getDeck();
    model.startGame(deck, 8, 4, false);
    // Test an initial deck without any moves
    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: 2♥, 10♥, 5♣, K♣, 8♦, 3♠, J♠\n"
        + "C2: 3♥, J♥, 6♣, A♣, 9♦, 4♠, Q♠\n"
        + "C3: 4♥, Q♥, 7♣, 2♦, 10♦, 5♠, K♠\n"
        + "C4: 5♥, K♥, 8♣, 3♦, J♦, 6♠, A♠\n"
        + "C5: 6♥, A♥, 9♣, 4♦, Q♦, 7♠\n"
        + "C6: 7♥, 2♣, 10♣, 5♦, K♦, 8♠\n"
        + "C7: 8♥, 3♣, J♣, 6♦, A♦, 9♠\n"
        + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠", view.toString());
    // Testing a move to the foundation pile
    model.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 1);
    assertEquals("F1:\n"
        + "F2: A♠\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: 2♥, 10♥, 5♣, K♣, 8♦, 3♠, J♠\n"
        + "C2: 3♥, J♥, 6♣, A♣, 9♦, 4♠, Q♠\n"
        + "C3: 4♥, Q♥, 7♣, 2♦, 10♦, 5♠, K♠\n"
        + "C4: 5♥, K♥, 8♣, 3♦, J♦, 6♠\n"
        + "C5: 6♥, A♥, 9♣, 4♦, Q♦, 7♠\n"
        + "C6: 7♥, 2♣, 10♣, 5♦, K♦, 8♠\n"
        + "C7: 8♥, 3♣, J♣, 6♦, A♦, 9♠\n"
        + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠", view.toString());

    // Testing a move to the open pile
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 3);
    assertEquals("F1:\n"
        + "F2: A♠\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4: 10♠\n"
        + "C1: 2♥, 10♥, 5♣, K♣, 8♦, 3♠, J♠\n"
        + "C2: 3♥, J♥, 6♣, A♣, 9♦, 4♠, Q♠\n"
        + "C3: 4♥, Q♥, 7♣, 2♦, 10♦, 5♠, K♠\n"
        + "C4: 5♥, K♥, 8♣, 3♦, J♦, 6♠\n"
        + "C5: 6♥, A♥, 9♣, 4♦, Q♦, 7♠\n"
        + "C6: 7♥, 2♣, 10♣, 5♦, K♦, 8♠\n"
        + "C7: 8♥, 3♣, J♣, 6♦, A♦, 9♠\n"
        + "C8: 9♥, 4♣, Q♣, 7♦, 2♠", view.toString());
    // Testing a move to the same foundation pile to check the commas
    model.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 1);
    assertEquals("F1:\n"
        + "F2: A♠, 2♠\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4: 10♠\n"
        + "C1: 2♥, 10♥, 5♣, K♣, 8♦, 3♠, J♠\n"
        + "C2: 3♥, J♥, 6♣, A♣, 9♦, 4♠, Q♠\n"
        + "C3: 4♥, Q♥, 7♣, 2♦, 10♦, 5♠, K♠\n"
        + "C4: 5♥, K♥, 8♣, 3♦, J♦, 6♠\n"
        + "C5: 6♥, A♥, 9♣, 4♦, Q♦, 7♠\n"
        + "C6: 7♥, 2♣, 10♣, 5♦, K♦, 8♠\n"
        + "C7: 8♥, 3♣, J♣, 6♦, A♦, 9♠\n"
        + "C8: 9♥, 4♣, Q♣, 7♦", view.toString());


  }

  // Tests a null model in the first constructor.
  @Test(expected = IllegalArgumentException.class)
  public void testNullModelFirstConstructor() {
    FreecellView nullModel = new FreecellTextView(null);
  }

  // Tests a null model in the second constructor.
  @Test(expected = IllegalArgumentException.class)
  public void testNullModelSecondConstructor() {
    FreecellView nullModel = new FreecellTextView(null, System.out);
  }

  // Tests a null appendable in the second constructor.
  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendableSecondConstructor() {
    deck = model.getDeck();
    model.startGame(deck, 8, 4, false);
    FreecellView nullModel = new FreecellTextView(model, null);
  }


  // Tests the toString method before the game has started.
  @Test
  public void testToStringBeforeGameStarts() {
    assertEquals("", view.toString());
  }

  @Test
  //Tests the renderMessage method
  public void testRenderMessage() {

    try {
      viewWithAppendable.renderMessage("Hello!");
      assertEquals("Hello!", s.toString());
      viewWithAppendable.renderMessage("wow!");
      assertEquals("Hello!wow!", s.toString());
    } catch (IOException e) {
      throw new IllegalStateException("IOException has occurred.");
    }

  }


  //Tests the renderBoard method
  @Test
  public void testRenderBoard() {
    deck = model.getDeck();
    model.startGame(deck, 8, 4, false);
    try {
      viewWithAppendable.renderBoard();
      assertEquals("F1:\n"
          + "F2:\n"
          + "F3:\n"
          + "F4:\n"
          + "O1:\n"
          + "O2:\n"
          + "O3:\n"
          + "O4:\n"
          + "C1: 2♥, 10♥, 5♣, K♣, 8♦, 3♠, J♠\n"
          + "C2: 3♥, J♥, 6♣, A♣, 9♦, 4♠, Q♠\n"
          + "C3: 4♥, Q♥, 7♣, 2♦, 10♦, 5♠, K♠\n"
          + "C4: 5♥, K♥, 8♣, 3♦, J♦, 6♠, A♠\n"
          + "C5: 6♥, A♥, 9♣, 4♦, Q♦, 7♠\n"
          + "C6: 7♥, 2♣, 10♣, 5♦, K♦, 8♠\n"
          + "C7: 8♥, 3♣, J♣, 6♦, A♦, 9♠\n"
          + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠", s.toString());

    } catch (IOException e) {
      throw new IllegalStateException("IOException has occurred.");
    }

  }


  //Tests the renderBoard method and message together.
  @Test
  public void testRenderBoardAndMessageTogether() {
    deck = model.getDeck();
    model.startGame(deck, 8, 4, false);
    try {
      viewWithAppendable.renderBoard();
      assertEquals("F1:\n"
          + "F2:\n"
          + "F3:\n"
          + "F4:\n"
          + "O1:\n"
          + "O2:\n"
          + "O3:\n"
          + "O4:\n"
          + "C1: 2♥, 10♥, 5♣, K♣, 8♦, 3♠, J♠\n"
          + "C2: 3♥, J♥, 6♣, A♣, 9♦, 4♠, Q♠\n"
          + "C3: 4♥, Q♥, 7♣, 2♦, 10♦, 5♠, K♠\n"
          + "C4: 5♥, K♥, 8♣, 3♦, J♦, 6♠, A♠\n"
          + "C5: 6♥, A♥, 9♣, 4♦, Q♦, 7♠\n"
          + "C6: 7♥, 2♣, 10♣, 5♦, K♦, 8♠\n"
          + "C7: 8♥, 3♣, J♣, 6♦, A♦, 9♠\n"
          + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠", s.toString());
      viewWithAppendable.renderMessage("do it work?");
      assertEquals("F1:\n"
          + "F2:\n"
          + "F3:\n"
          + "F4:\n"
          + "O1:\n"
          + "O2:\n"
          + "O3:\n"
          + "O4:\n"
          + "C1: 2♥, 10♥, 5♣, K♣, 8♦, 3♠, J♠\n"
          + "C2: 3♥, J♥, 6♣, A♣, 9♦, 4♠, Q♠\n"
          + "C3: 4♥, Q♥, 7♣, 2♦, 10♦, 5♠, K♠\n"
          + "C4: 5♥, K♥, 8♣, 3♦, J♦, 6♠, A♠\n"
          + "C5: 6♥, A♥, 9♣, 4♦, Q♦, 7♠\n"
          + "C6: 7♥, 2♣, 10♣, 5♦, K♦, 8♠\n"
          + "C7: 8♥, 3♣, J♣, 6♦, A♦, 9♠\n"
          + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠do it work?", s.toString());
    } catch (IOException e) {
      throw new IllegalStateException("IOException has occurred.");
    }

  }


  // Tests the renderBoard method when transmission of
  // the board to the provided data destination has failed.
  @Test(expected = IllegalStateException.class)
  public void testIOExceptionRenderBoard() {
    deck = model.getDeck();
    model.startGame(deck, 8, 4, false);
    try {
      viewWithMock.renderBoard();

    } catch (IOException e) {
      throw new IllegalStateException("IOException has occurred.");
    }

  }


  // Tests the renderMessage method when transmission of
  // the board to the provided data destination has failed.
  @Test(expected = IllegalStateException.class)
  public void testIOExceptionRenderMessage() {
    deck = model.getDeck();
    model.startGame(deck, 8, 4, false);
    try {
      viewWithMock.renderMessage("what's up, dog?");

    } catch (IOException e) {
      throw new IllegalStateException("IOException has occurred.");
    }

  }

}