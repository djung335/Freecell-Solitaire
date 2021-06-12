import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.SuitType;
import cs3500.freecell.model.hw02.ValueType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for SimpleFreecellController; unit tests to ensure that SimpleFreecellController
 * methods work as intended.
 */
public class SimpleFreecellControllerTest {

  Appendable out;
  Readable in;
  List<Card> badDeck = new ArrayList<Card>();
  SimpleFreecellModel model = new SimpleFreecellModel();
  ByteArrayOutputStream bytes = new ByteArrayOutputStream();


  // Tests an illegal model given to the controller constructor.
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIllegalModel() {
    FreecellController controller = new SimpleFreecellController(null,
        new InputStreamReader(System.in), System.out);

  }

  // Tests an illegal readable given to the controller constructor.
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIllegalReadable() {
    FreecellController controller = new SimpleFreecellController(model,
        null, System.out);

  }

  // Tests an illegal appendable given to the controller constructor.
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIllegalAppendable() {
    FreecellController controller = new SimpleFreecellController(model,
        new InputStreamReader(System.in), null);

  }

  // Tests starting the game with an invalid number of cascade piles.
  @Test
  public void testInvalidStartingGameInvalidNumCascades() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C3 12".getBytes()));
    FreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(model.getDeck(), 2, 4, false);
    assertEquals("Could not start game.", bytes.toString());

  }

  // Tests starting the game with an invalid number of open piles.
  @Test
  public void testInvalidStartingGameInvalidNumOpens() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C3 12".getBytes()));
    FreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(model.getDeck(), 8, -2, false);
    assertEquals("Could not start game.", bytes.toString());

  }

  // Tests starting the game with a null deck
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartingGameNullDeck() {

    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C3 12".getBytes()));
    FreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(null, 8, 4, false);


  }


  // Tests starting the game with a bad deck
  @Test
  public void testInvalidStartingGameBadDeck() {
    for (int i = 0; i < 52; i++) {
      this.badDeck.add(new Card(ValueType.TWO, SuitType.HEARTS));
    }

    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C3 12".getBytes()));
    FreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(badDeck, 8, 4, false);
    assertEquals("Could not start game.", bytes.toString());

  }

  // Tests playGame with a shuffled deck.
  @Test
  public void testShuffle() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, true);
    assertNotEquals("F1:\n"
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
        + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
        + "Game quit prematurely.", bytes.toString());

  }


  // Tests the command to quit the game as a lowercase q.
  @Test
  public void testQuitGameLowercase() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
        + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
        + "Game quit prematurely.", bytes.toString());

  }

  // Tests the command to quit the game as a uppercase Q.
  @Test
  public void testQuitGameCapitalized() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("Q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
        + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
        + "Game quit prematurely.", bytes.toString());

  }


  // Test a single input command.
  @Test
  public void testSingleInput() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C3 q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Game quit prematurely."
        , bytes.toString());

  }


  // Test two input commands for playGame.
  @Test
  public void testTwoInputs() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C3 7 q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Game quit prematurely."
        , bytes.toString());

  }

  // Tests the output of playGame when a valid move is made.
  @Test
  public void testSuccessfulThreeInputs() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C3 7 O1 q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: K♠\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥, 10♥, 5♣, K♣, 8♦, 3♠, J♠\n"
            + "C2: 3♥, J♥, 6♣, A♣, 9♦, 4♠, Q♠\n"
            + "C3: 4♥, Q♥, 7♣, 2♦, 10♦, 5♠\n"
            + "C4: 5♥, K♥, 8♣, 3♦, J♦, 6♠, A♠\n"
            + "C5: 6♥, A♥, 9♣, 4♦, Q♦, 7♠\n"
            + "C6: 7♥, 2♣, 10♣, 5♦, K♦, 8♠\n"
            + "C7: 8♥, 3♣, J♣, 6♦, A♦, 9♠\n"
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Game quit prematurely."
        , bytes.toString());

  }

  // Tests playGame when you move from Cascade to Open.
  @Test
  public void testCascadeToOpen() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C3 7 O1 q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: K♠\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥, 10♥, 5♣, K♣, 8♦, 3♠, J♠\n"
            + "C2: 3♥, J♥, 6♣, A♣, 9♦, 4♠, Q♠\n"
            + "C3: 4♥, Q♥, 7♣, 2♦, 10♦, 5♠\n"
            + "C4: 5♥, K♥, 8♣, 3♦, J♦, 6♠, A♠\n"
            + "C5: 6♥, A♥, 9♣, 4♦, Q♦, 7♠\n"
            + "C6: 7♥, 2♣, 10♣, 5♦, K♦, 8♠\n"
            + "C7: 8♥, 3♣, J♣, 6♦, A♦, 9♠\n"
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Game quit prematurely."
        , bytes.toString());

  }

  // Tests playGame when you move from Cascade to Foundation.
  @Test
  public void testCascadeToFoundation() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C4 7 F1 q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "F1: A♠\n"
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
            + "C4: 5♥, K♥, 8♣, 3♦, J♦, 6♠\n"
            + "C5: 6♥, A♥, 9♣, 4♦, Q♦, 7♠\n"
            + "C6: 7♥, 2♣, 10♣, 5♦, K♦, 8♠\n"
            + "C7: 8♥, 3♣, J♣, 6♦, A♦, 9♠\n"
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Game quit prematurely."
        , bytes.toString());

  }

  // Tests playGame when you move from Cascade to Cascade.
  @Test
  public void testCascadeToCascade() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C3 1 C17 q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 52, 4, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥\n"
            + "C2: 3♥\n"
            + "C3: 4♥\n"
            + "C4: 5♥\n"
            + "C5: 6♥\n"
            + "C6: 7♥\n"
            + "C7: 8♥\n"
            + "C8: 9♥\n"
            + "C9: 10♥\n"
            + "C10: J♥\n"
            + "C11: Q♥\n"
            + "C12: K♥\n"
            + "C13: A♥\n"
            + "C14: 2♣\n"
            + "C15: 3♣\n"
            + "C16: 4♣\n"
            + "C17: 5♣\n"
            + "C18: 6♣\n"
            + "C19: 7♣\n"
            + "C20: 8♣\n"
            + "C21: 9♣\n"
            + "C22: 10♣\n"
            + "C23: J♣\n"
            + "C24: Q♣\n"
            + "C25: K♣\n"
            + "C26: A♣\n"
            + "C27: 2♦\n"
            + "C28: 3♦\n"
            + "C29: 4♦\n"
            + "C30: 5♦\n"
            + "C31: 6♦\n"
            + "C32: 7♦\n"
            + "C33: 8♦\n"
            + "C34: 9♦\n"
            + "C35: 10♦\n"
            + "C36: J♦\n"
            + "C37: Q♦\n"
            + "C38: K♦\n"
            + "C39: A♦\n"
            + "C40: 2♠\n"
            + "C41: 3♠\n"
            + "C42: 4♠\n"
            + "C43: 5♠\n"
            + "C44: 6♠\n"
            + "C45: 7♠\n"
            + "C46: 8♠\n"
            + "C47: 9♠\n"
            + "C48: 10♠\n"
            + "C49: J♠\n"
            + "C50: Q♠\n"
            + "C51: K♠\n"
            + "C52: A♠\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥\n"
            + "C2: 3♥\n"
            + "C3:\n"
            + "C4: 5♥\n"
            + "C5: 6♥\n"
            + "C6: 7♥\n"
            + "C7: 8♥\n"
            + "C8: 9♥\n"
            + "C9: 10♥\n"
            + "C10: J♥\n"
            + "C11: Q♥\n"
            + "C12: K♥\n"
            + "C13: A♥\n"
            + "C14: 2♣\n"
            + "C15: 3♣\n"
            + "C16: 4♣\n"
            + "C17: 5♣, 4♥\n"
            + "C18: 6♣\n"
            + "C19: 7♣\n"
            + "C20: 8♣\n"
            + "C21: 9♣\n"
            + "C22: 10♣\n"
            + "C23: J♣\n"
            + "C24: Q♣\n"
            + "C25: K♣\n"
            + "C26: A♣\n"
            + "C27: 2♦\n"
            + "C28: 3♦\n"
            + "C29: 4♦\n"
            + "C30: 5♦\n"
            + "C31: 6♦\n"
            + "C32: 7♦\n"
            + "C33: 8♦\n"
            + "C34: 9♦\n"
            + "C35: 10♦\n"
            + "C36: J♦\n"
            + "C37: Q♦\n"
            + "C38: K♦\n"
            + "C39: A♦\n"
            + "C40: 2♠\n"
            + "C41: 3♠\n"
            + "C42: 4♠\n"
            + "C43: 5♠\n"
            + "C44: 6♠\n"
            + "C45: 7♠\n"
            + "C46: 8♠\n"
            + "C47: 9♠\n"
            + "C48: 10♠\n"
            + "C49: J♠\n"
            + "C50: Q♠\n"
            + "C51: K♠\n"
            + "C52: A♠\n"
            + "Game quit prematurely."
        , bytes.toString());

  }

  // Tests playGame when you move from Open to Cascade.
  @Test
  public void testOpenToCascade() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C3 1 O1 O1 1 C17 q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 52, 4, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥\n"
            + "C2: 3♥\n"
            + "C3: 4♥\n"
            + "C4: 5♥\n"
            + "C5: 6♥\n"
            + "C6: 7♥\n"
            + "C7: 8♥\n"
            + "C8: 9♥\n"
            + "C9: 10♥\n"
            + "C10: J♥\n"
            + "C11: Q♥\n"
            + "C12: K♥\n"
            + "C13: A♥\n"
            + "C14: 2♣\n"
            + "C15: 3♣\n"
            + "C16: 4♣\n"
            + "C17: 5♣\n"
            + "C18: 6♣\n"
            + "C19: 7♣\n"
            + "C20: 8♣\n"
            + "C21: 9♣\n"
            + "C22: 10♣\n"
            + "C23: J♣\n"
            + "C24: Q♣\n"
            + "C25: K♣\n"
            + "C26: A♣\n"
            + "C27: 2♦\n"
            + "C28: 3♦\n"
            + "C29: 4♦\n"
            + "C30: 5♦\n"
            + "C31: 6♦\n"
            + "C32: 7♦\n"
            + "C33: 8♦\n"
            + "C34: 9♦\n"
            + "C35: 10♦\n"
            + "C36: J♦\n"
            + "C37: Q♦\n"
            + "C38: K♦\n"
            + "C39: A♦\n"
            + "C40: 2♠\n"
            + "C41: 3♠\n"
            + "C42: 4♠\n"
            + "C43: 5♠\n"
            + "C44: 6♠\n"
            + "C45: 7♠\n"
            + "C46: 8♠\n"
            + "C47: 9♠\n"
            + "C48: 10♠\n"
            + "C49: J♠\n"
            + "C50: Q♠\n"
            + "C51: K♠\n"
            + "C52: A♠\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: 4♥\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥\n"
            + "C2: 3♥\n"
            + "C3:\n"
            + "C4: 5♥\n"
            + "C5: 6♥\n"
            + "C6: 7♥\n"
            + "C7: 8♥\n"
            + "C8: 9♥\n"
            + "C9: 10♥\n"
            + "C10: J♥\n"
            + "C11: Q♥\n"
            + "C12: K♥\n"
            + "C13: A♥\n"
            + "C14: 2♣\n"
            + "C15: 3♣\n"
            + "C16: 4♣\n"
            + "C17: 5♣\n"
            + "C18: 6♣\n"
            + "C19: 7♣\n"
            + "C20: 8♣\n"
            + "C21: 9♣\n"
            + "C22: 10♣\n"
            + "C23: J♣\n"
            + "C24: Q♣\n"
            + "C25: K♣\n"
            + "C26: A♣\n"
            + "C27: 2♦\n"
            + "C28: 3♦\n"
            + "C29: 4♦\n"
            + "C30: 5♦\n"
            + "C31: 6♦\n"
            + "C32: 7♦\n"
            + "C33: 8♦\n"
            + "C34: 9♦\n"
            + "C35: 10♦\n"
            + "C36: J♦\n"
            + "C37: Q♦\n"
            + "C38: K♦\n"
            + "C39: A♦\n"
            + "C40: 2♠\n"
            + "C41: 3♠\n"
            + "C42: 4♠\n"
            + "C43: 5♠\n"
            + "C44: 6♠\n"
            + "C45: 7♠\n"
            + "C46: 8♠\n"
            + "C47: 9♠\n"
            + "C48: 10♠\n"
            + "C49: J♠\n"
            + "C50: Q♠\n"
            + "C51: K♠\n"
            + "C52: A♠\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥\n"
            + "C2: 3♥\n"
            + "C3:\n"
            + "C4: 5♥\n"
            + "C5: 6♥\n"
            + "C6: 7♥\n"
            + "C7: 8♥\n"
            + "C8: 9♥\n"
            + "C9: 10♥\n"
            + "C10: J♥\n"
            + "C11: Q♥\n"
            + "C12: K♥\n"
            + "C13: A♥\n"
            + "C14: 2♣\n"
            + "C15: 3♣\n"
            + "C16: 4♣\n"
            + "C17: 5♣, 4♥\n"
            + "C18: 6♣\n"
            + "C19: 7♣\n"
            + "C20: 8♣\n"
            + "C21: 9♣\n"
            + "C22: 10♣\n"
            + "C23: J♣\n"
            + "C24: Q♣\n"
            + "C25: K♣\n"
            + "C26: A♣\n"
            + "C27: 2♦\n"
            + "C28: 3♦\n"
            + "C29: 4♦\n"
            + "C30: 5♦\n"
            + "C31: 6♦\n"
            + "C32: 7♦\n"
            + "C33: 8♦\n"
            + "C34: 9♦\n"
            + "C35: 10♦\n"
            + "C36: J♦\n"
            + "C37: Q♦\n"
            + "C38: K♦\n"
            + "C39: A♦\n"
            + "C40: 2♠\n"
            + "C41: 3♠\n"
            + "C42: 4♠\n"
            + "C43: 5♠\n"
            + "C44: 6♠\n"
            + "C45: 7♠\n"
            + "C46: 8♠\n"
            + "C47: 9♠\n"
            + "C48: 10♠\n"
            + "C49: J♠\n"
            + "C50: Q♠\n"
            + "C51: K♠\n"
            + "C52: A♠\n"
            + "Game quit prematurely."
        , bytes.toString());

  }

  // Tests playGame when you move from Open to Open.
  @Test
  public void testOpenToOpen() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C3 1 O1 O1 1 O3 q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 52, 4, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥\n"
            + "C2: 3♥\n"
            + "C3: 4♥\n"
            + "C4: 5♥\n"
            + "C5: 6♥\n"
            + "C6: 7♥\n"
            + "C7: 8♥\n"
            + "C8: 9♥\n"
            + "C9: 10♥\n"
            + "C10: J♥\n"
            + "C11: Q♥\n"
            + "C12: K♥\n"
            + "C13: A♥\n"
            + "C14: 2♣\n"
            + "C15: 3♣\n"
            + "C16: 4♣\n"
            + "C17: 5♣\n"
            + "C18: 6♣\n"
            + "C19: 7♣\n"
            + "C20: 8♣\n"
            + "C21: 9♣\n"
            + "C22: 10♣\n"
            + "C23: J♣\n"
            + "C24: Q♣\n"
            + "C25: K♣\n"
            + "C26: A♣\n"
            + "C27: 2♦\n"
            + "C28: 3♦\n"
            + "C29: 4♦\n"
            + "C30: 5♦\n"
            + "C31: 6♦\n"
            + "C32: 7♦\n"
            + "C33: 8♦\n"
            + "C34: 9♦\n"
            + "C35: 10♦\n"
            + "C36: J♦\n"
            + "C37: Q♦\n"
            + "C38: K♦\n"
            + "C39: A♦\n"
            + "C40: 2♠\n"
            + "C41: 3♠\n"
            + "C42: 4♠\n"
            + "C43: 5♠\n"
            + "C44: 6♠\n"
            + "C45: 7♠\n"
            + "C46: 8♠\n"
            + "C47: 9♠\n"
            + "C48: 10♠\n"
            + "C49: J♠\n"
            + "C50: Q♠\n"
            + "C51: K♠\n"
            + "C52: A♠\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: 4♥\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥\n"
            + "C2: 3♥\n"
            + "C3:\n"
            + "C4: 5♥\n"
            + "C5: 6♥\n"
            + "C6: 7♥\n"
            + "C7: 8♥\n"
            + "C8: 9♥\n"
            + "C9: 10♥\n"
            + "C10: J♥\n"
            + "C11: Q♥\n"
            + "C12: K♥\n"
            + "C13: A♥\n"
            + "C14: 2♣\n"
            + "C15: 3♣\n"
            + "C16: 4♣\n"
            + "C17: 5♣\n"
            + "C18: 6♣\n"
            + "C19: 7♣\n"
            + "C20: 8♣\n"
            + "C21: 9♣\n"
            + "C22: 10♣\n"
            + "C23: J♣\n"
            + "C24: Q♣\n"
            + "C25: K♣\n"
            + "C26: A♣\n"
            + "C27: 2♦\n"
            + "C28: 3♦\n"
            + "C29: 4♦\n"
            + "C30: 5♦\n"
            + "C31: 6♦\n"
            + "C32: 7♦\n"
            + "C33: 8♦\n"
            + "C34: 9♦\n"
            + "C35: 10♦\n"
            + "C36: J♦\n"
            + "C37: Q♦\n"
            + "C38: K♦\n"
            + "C39: A♦\n"
            + "C40: 2♠\n"
            + "C41: 3♠\n"
            + "C42: 4♠\n"
            + "C43: 5♠\n"
            + "C44: 6♠\n"
            + "C45: 7♠\n"
            + "C46: 8♠\n"
            + "C47: 9♠\n"
            + "C48: 10♠\n"
            + "C49: J♠\n"
            + "C50: Q♠\n"
            + "C51: K♠\n"
            + "C52: A♠\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3: 4♥\n"
            + "O4:\n"
            + "C1: 2♥\n"
            + "C2: 3♥\n"
            + "C3:\n"
            + "C4: 5♥\n"
            + "C5: 6♥\n"
            + "C6: 7♥\n"
            + "C7: 8♥\n"
            + "C8: 9♥\n"
            + "C9: 10♥\n"
            + "C10: J♥\n"
            + "C11: Q♥\n"
            + "C12: K♥\n"
            + "C13: A♥\n"
            + "C14: 2♣\n"
            + "C15: 3♣\n"
            + "C16: 4♣\n"
            + "C17: 5♣\n"
            + "C18: 6♣\n"
            + "C19: 7♣\n"
            + "C20: 8♣\n"
            + "C21: 9♣\n"
            + "C22: 10♣\n"
            + "C23: J♣\n"
            + "C24: Q♣\n"
            + "C25: K♣\n"
            + "C26: A♣\n"
            + "C27: 2♦\n"
            + "C28: 3♦\n"
            + "C29: 4♦\n"
            + "C30: 5♦\n"
            + "C31: 6♦\n"
            + "C32: 7♦\n"
            + "C33: 8♦\n"
            + "C34: 9♦\n"
            + "C35: 10♦\n"
            + "C36: J♦\n"
            + "C37: Q♦\n"
            + "C38: K♦\n"
            + "C39: A♦\n"
            + "C40: 2♠\n"
            + "C41: 3♠\n"
            + "C42: 4♠\n"
            + "C43: 5♠\n"
            + "C44: 6♠\n"
            + "C45: 7♠\n"
            + "C46: 8♠\n"
            + "C47: 9♠\n"
            + "C48: 10♠\n"
            + "C49: J♠\n"
            + "C50: Q♠\n"
            + "C51: K♠\n"
            + "C52: A♠\n"
            + "Game quit prematurely."
        , bytes.toString());

  }

  // Tests playGame when you move from Open to Foundation.
  @Test
  public void testOpenToFoundation() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C13 1 O1 O1 1 F3 q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 52, 4, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥\n"
            + "C2: 3♥\n"
            + "C3: 4♥\n"
            + "C4: 5♥\n"
            + "C5: 6♥\n"
            + "C6: 7♥\n"
            + "C7: 8♥\n"
            + "C8: 9♥\n"
            + "C9: 10♥\n"
            + "C10: J♥\n"
            + "C11: Q♥\n"
            + "C12: K♥\n"
            + "C13: A♥\n"
            + "C14: 2♣\n"
            + "C15: 3♣\n"
            + "C16: 4♣\n"
            + "C17: 5♣\n"
            + "C18: 6♣\n"
            + "C19: 7♣\n"
            + "C20: 8♣\n"
            + "C21: 9♣\n"
            + "C22: 10♣\n"
            + "C23: J♣\n"
            + "C24: Q♣\n"
            + "C25: K♣\n"
            + "C26: A♣\n"
            + "C27: 2♦\n"
            + "C28: 3♦\n"
            + "C29: 4♦\n"
            + "C30: 5♦\n"
            + "C31: 6♦\n"
            + "C32: 7♦\n"
            + "C33: 8♦\n"
            + "C34: 9♦\n"
            + "C35: 10♦\n"
            + "C36: J♦\n"
            + "C37: Q♦\n"
            + "C38: K♦\n"
            + "C39: A♦\n"
            + "C40: 2♠\n"
            + "C41: 3♠\n"
            + "C42: 4♠\n"
            + "C43: 5♠\n"
            + "C44: 6♠\n"
            + "C45: 7♠\n"
            + "C46: 8♠\n"
            + "C47: 9♠\n"
            + "C48: 10♠\n"
            + "C49: J♠\n"
            + "C50: Q♠\n"
            + "C51: K♠\n"
            + "C52: A♠\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♥\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥\n"
            + "C2: 3♥\n"
            + "C3: 4♥\n"
            + "C4: 5♥\n"
            + "C5: 6♥\n"
            + "C6: 7♥\n"
            + "C7: 8♥\n"
            + "C8: 9♥\n"
            + "C9: 10♥\n"
            + "C10: J♥\n"
            + "C11: Q♥\n"
            + "C12: K♥\n"
            + "C13:\n"
            + "C14: 2♣\n"
            + "C15: 3♣\n"
            + "C16: 4♣\n"
            + "C17: 5♣\n"
            + "C18: 6♣\n"
            + "C19: 7♣\n"
            + "C20: 8♣\n"
            + "C21: 9♣\n"
            + "C22: 10♣\n"
            + "C23: J♣\n"
            + "C24: Q♣\n"
            + "C25: K♣\n"
            + "C26: A♣\n"
            + "C27: 2♦\n"
            + "C28: 3♦\n"
            + "C29: 4♦\n"
            + "C30: 5♦\n"
            + "C31: 6♦\n"
            + "C32: 7♦\n"
            + "C33: 8♦\n"
            + "C34: 9♦\n"
            + "C35: 10♦\n"
            + "C36: J♦\n"
            + "C37: Q♦\n"
            + "C38: K♦\n"
            + "C39: A♦\n"
            + "C40: 2♠\n"
            + "C41: 3♠\n"
            + "C42: 4♠\n"
            + "C43: 5♠\n"
            + "C44: 6♠\n"
            + "C45: 7♠\n"
            + "C46: 8♠\n"
            + "C47: 9♠\n"
            + "C48: 10♠\n"
            + "C49: J♠\n"
            + "C50: Q♠\n"
            + "C51: K♠\n"
            + "C52: A♠\n"
            + "F1:\n"
            + "F2:\n"
            + "F3: A♥\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥\n"
            + "C2: 3♥\n"
            + "C3: 4♥\n"
            + "C4: 5♥\n"
            + "C5: 6♥\n"
            + "C6: 7♥\n"
            + "C7: 8♥\n"
            + "C8: 9♥\n"
            + "C9: 10♥\n"
            + "C10: J♥\n"
            + "C11: Q♥\n"
            + "C12: K♥\n"
            + "C13:\n"
            + "C14: 2♣\n"
            + "C15: 3♣\n"
            + "C16: 4♣\n"
            + "C17: 5♣\n"
            + "C18: 6♣\n"
            + "C19: 7♣\n"
            + "C20: 8♣\n"
            + "C21: 9♣\n"
            + "C22: 10♣\n"
            + "C23: J♣\n"
            + "C24: Q♣\n"
            + "C25: K♣\n"
            + "C26: A♣\n"
            + "C27: 2♦\n"
            + "C28: 3♦\n"
            + "C29: 4♦\n"
            + "C30: 5♦\n"
            + "C31: 6♦\n"
            + "C32: 7♦\n"
            + "C33: 8♦\n"
            + "C34: 9♦\n"
            + "C35: 10♦\n"
            + "C36: J♦\n"
            + "C37: Q♦\n"
            + "C38: K♦\n"
            + "C39: A♦\n"
            + "C40: 2♠\n"
            + "C41: 3♠\n"
            + "C42: 4♠\n"
            + "C43: 5♠\n"
            + "C44: 6♠\n"
            + "C45: 7♠\n"
            + "C46: 8♠\n"
            + "C47: 9♠\n"
            + "C48: 10♠\n"
            + "C49: J♠\n"
            + "C50: Q♠\n"
            + "C51: K♠\n"
            + "C52: A♠\n"
            + "Game quit prematurely."
        , bytes.toString());

  }


  // Tests playGame when you move from Foundation
  @Test
  public void testMoveFromFoundation() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C4 7 F1 F1 1 O1 q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "F1: A♠\n"
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
            + "C4: 5♥, K♥, 8♣, 3♦, J♦, 6♠\n"
            + "C5: 6♥, A♥, 9♣, 4♦, Q♦, 7♠\n"
            + "C6: 7♥, 2♣, 10♣, 5♦, K♦, 8♠\n"
            + "C7: 8♥, 3♣, J♣, 6♦, A♦, 9♠\n"
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Invalid move. Try again.\n"
            + "Game quit prematurely."
        , bytes.toString());

  }


  // Test playGame when the first input is not a valid command.
  @Test
  public void testInvalidFirstCommand() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("gug q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Enter a valid source pile and index.\n"
            + "Game quit prematurely."
        , bytes.toString());

  }

  // Test playGame when the second input is not a valid command.
  @Test
  public void testInvalidSecondCommand() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C1 bro? q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Enter a valid card index.\n"
            + "Game quit prematurely."
        , bytes.toString());

  }

  // Test playGame when the third input is not a valid command.
  @Test
  public void testInvalidThirdCommand() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C1 7 iamhavingagreattime! Q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Enter a valid destination pile and index.\n"
            + "Game quit prematurely."
        , bytes.toString());

  }

  // Test playGame when you do 3 wrong inputs in a row
  @Test
  public void testThreeWrongInputs() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(
        new ByteArrayInputStream("B1 -13 iamhavingagreattime! Q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Enter a valid source pile and index.\n"
            + "Enter a valid source pile and index.\n"
            + "Enter a valid source pile and index.\n"
            + "Game quit prematurely."
        , bytes.toString());

  }

  // Test playGame when a invalid input is placed after a valid command.
  @Test
  public void testInvalidCommandAfterValidCommand() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C1 7 O1 bon Q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: J♠\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥, 10♥, 5♣, K♣, 8♦, 3♠\n"
            + "C2: 3♥, J♥, 6♣, A♣, 9♦, 4♠, Q♠\n"
            + "C3: 4♥, Q♥, 7♣, 2♦, 10♦, 5♠, K♠\n"
            + "C4: 5♥, K♥, 8♣, 3♦, J♦, 6♠, A♠\n"
            + "C5: 6♥, A♥, 9♣, 4♦, Q♦, 7♠\n"
            + "C6: 7♥, 2♣, 10♣, 5♦, K♦, 8♠\n"
            + "C7: 8♥, 3♣, J♣, 6♦, A♦, 9♠\n"
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Enter a valid source pile and index.\n"
            + "Game quit prematurely."
        , bytes.toString());

  }


  // Test playGame when the command works, but the move is invalid.
  @Test
  public void testInvalidMove() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C1 18 O4 Q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Invalid move. Try again.\n"
            + "Game quit prematurely."
        , bytes.toString());

  }

  // Test playGame when you get through the entire readable.
  @Test(expected = IllegalStateException.class)
  public void testReadableFailing() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("C3".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);


  }


  // Test playGame when the game isn't over.
  @Test
  public void testGameNotOver() {
    out = new PrintStream(bytes);
    in = new InputStreamReader(new ByteArrayInputStream("q".getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 8, 4, false);
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
            + "C8: 9♥, 4♣, Q♣, 7♦, 2♠, 10♠\n"
            + "Game quit prematurely."
        , bytes.toString());

  }


  // Test playGame when the game is over.
  @Test
  public void testGameIsOver() {

    StringBuilder commands = new StringBuilder();

    for (int i = 0; i < 4; i++) {

      String pileNumber = Integer.toString(13 * (i + 1));
      commands.append("C" + pileNumber + " 1 " + "F" + (i + 1) + " ");
    }

    for (int i = 0; i < 12; i++) {

      commands.append("C" + (i + 1) + " 1 " + "F1 ");
      commands.append("C" + (i + 14) + " 1 " + "F2 ");
      commands.append("C" + (i + 27) + " 1 " + "F3 ");
      commands.append("C" + (i + 40) + " 1 " + "F4 ");

    }

    out = new PrintStream(bytes);

    in = new InputStreamReader(new ByteArrayInputStream(commands.toString().getBytes()));

    FreecellController controller = new SimpleFreecellController(model, in, out);

    controller.playGame(this.model.getDeck(), 52, 4, false);

    assertTrue(bytes.toString().endsWith("Game over."));

  }


}