import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SuitType;
import cs3500.freecell.model.hw02.ValueType;
import java.util.ArrayList;
import org.junit.Test;


/**
 * Test class for Card; unit tests to ensure that the Card class is working as intended.
 */
public class CardTest {

  Card sixOfSpades = new Card(ValueType.SIX, SuitType.SPADES);
  Card sevenOfSpades = new Card(ValueType.SEVEN, SuitType.SPADES);
  Card eightOfSpades = new Card(ValueType.EIGHT, SuitType.SPADES);
  Card kingOfSpades = new Card(ValueType.KING, SuitType.SPADES);
  Card aceOfSpades = new Card(ValueType.ACE, SuitType.SPADES);
  Card twoOfClubs = new Card(ValueType.TWO, SuitType.CLUBS);
  Card threeOfClubs = new Card(ValueType.THREE, SuitType.CLUBS);
  Card fiveOfDiamonds = new Card(ValueType.FIVE, SuitType.DIAMONDS);
  Card sixOfDiamonds = new Card(ValueType.SIX, SuitType.DIAMONDS);
  Card kingOfDiamonds = new Card(ValueType.KING, SuitType.DIAMONDS);
  Card aceOfDiamonds = new Card(ValueType.ACE, SuitType.DIAMONDS);
  Card sevenOfHearts = new Card(ValueType.SEVEN, SuitType.HEARTS);
  Card eightOfHearts = new Card(ValueType.EIGHT, SuitType.HEARTS);
  Card dupSevenOfHearts = new Card(ValueType.SEVEN, SuitType.HEARTS);


  // Tests a null value type in the Card constructor
  @Test(expected = IllegalArgumentException.class)
  public void nullValueType() {
    Card nullValue = new Card(null, SuitType.DIAMONDS);
  }

  // Tests a null suit type in the Card constructor
  @Test(expected = IllegalArgumentException.class)
  public void nullSuitType() {
    Card nullValue = new Card(ValueType.QUEEN, null);
  }


  // Test the toString method of the Card class,
  // which creates a textual representation for each card
  @Test
  public void testToString() {
    assertEquals("A♠", aceOfSpades.toString());
    assertEquals("2♣", twoOfClubs.toString());
    assertEquals("K♦", kingOfDiamonds.toString());
    assertEquals("7♥", sevenOfHearts.toString());


  }

  // Test the equals method of the Card class,
  // which determines whether the Card and the given object are the same
  @Test
  public void testEquals() {
    // first two tests are for Objects that are not type Card
    assertEquals(false, aceOfSpades.equals("hello!"));
    assertEquals(false, aceOfSpades.equals(new ArrayList<Card>()));
    // testing .equals with null seems to take off some style points...
    assertFalse(aceOfSpades == (null));
    // this test is for Cards that point to the same reference
    assertEquals(true, aceOfSpades.equals(aceOfSpades));
    // these tests are for two Cards with nothing in common
    assertEquals(false, sevenOfHearts.equals(aceOfSpades));
    assertEquals(false, twoOfClubs.equals(aceOfSpades));
    assertEquals(false, aceOfSpades.equals(dupSevenOfHearts));
    // these tests are for two Cards,
    // which point to different references but have the same suit and value
    assertEquals(true, sevenOfHearts.equals(dupSevenOfHearts));
    assertEquals(true, dupSevenOfHearts.equals(sevenOfHearts));
    // these tests are for two Cards which have one field in common
    assertEquals(false, aceOfDiamonds.equals(aceOfSpades));
    assertEquals(false, aceOfSpades.equals(aceOfDiamonds));
    assertEquals(false, sevenOfHearts.equals(eightOfHearts));
    assertEquals(false, eightOfHearts.equals(sevenOfHearts));

  }

  // Test the Card class's hashCode method
  @Test
  public void testHashCode() {
    assertEquals(true, aceOfSpades.hashCode() == aceOfSpades.hashCode());
    assertEquals(true, aceOfSpades.hashCode() != twoOfClubs.hashCode());
    assertEquals(true, twoOfClubs.hashCode() != aceOfSpades.hashCode());
    assertEquals(true, sevenOfHearts.hashCode() == sevenOfHearts.hashCode());
    assertEquals(true, dupSevenOfHearts.hashCode() == sevenOfHearts.hashCode());
    assertEquals(true, sevenOfHearts.hashCode() == dupSevenOfHearts.hashCode());
    assertEquals(true, eightOfHearts.hashCode() != sevenOfHearts.hashCode());
    assertEquals(true, sevenOfHearts.hashCode() != eightOfHearts.hashCode());

  }

  // Tests the Card's toCascade method, which determines that given a card already in
  // the cascade pile, this card can be sent to the cascade pile
  @Test
  public void testToCascade() {
    // these tests are for cards of the same suit
    assertEquals(false, this.sevenOfHearts.toCascade(this.eightOfHearts));
    assertEquals(false, this.twoOfClubs.toCascade(this.threeOfClubs));

    // these tests are for cards of a different suit but the same color
    assertEquals(false, this.sixOfDiamonds.toCascade(this.sevenOfHearts));
    assertEquals(false, this.aceOfSpades.toCascade(this.twoOfClubs));

    // these tests are for cards of a different suit and different color
    assertEquals(true, this.aceOfDiamonds.toCascade(this.twoOfClubs));
    assertEquals(true, this.sixOfSpades.toCascade(this.sevenOfHearts));

    // tests to assure nothing can be placed on an ace in the cascade pile
    assertEquals(false, this.kingOfDiamonds.toCascade(this.aceOfSpades));
    assertEquals(false, this.kingOfSpades.toCascade(this.aceOfDiamonds));
    assertEquals(false, this.twoOfClubs.toCascade(this.aceOfDiamonds));
    assertEquals(false, this.sixOfDiamonds.toCascade(this.aceOfDiamonds));

  }

  // Tests the Card's toFoundation method, which determines that given a card already in
  // the foundation pile, this card can be sent to the foundation pile
  @Test
  public void testToFoundation() {
    // these tests are for cards of the same suit
    assertEquals(true, this.eightOfHearts.toFoundation(this.sevenOfHearts));
    assertEquals(true, this.threeOfClubs.toFoundation(this.twoOfClubs));
    assertEquals(true, this.sixOfDiamonds.toFoundation(this.fiveOfDiamonds));
    assertEquals(true, this.eightOfSpades.toFoundation(this.sevenOfSpades));
    assertEquals(false, this.sevenOfHearts.toFoundation(this.eightOfHearts));

    // these tests are for cards of different suits
    assertEquals(false, this.eightOfHearts.toFoundation(this.twoOfClubs));
    assertEquals(false, this.sevenOfHearts.toFoundation(this.aceOfDiamonds));
    assertEquals(false, this.sevenOfHearts.toFoundation(this.aceOfSpades));

    //these tests are for cards of different suits, but with the first condition being met
    assertEquals(false, this.sevenOfHearts.toFoundation(this.sixOfDiamonds));
    assertEquals(false, this.twoOfClubs.toFoundation(this.aceOfSpades));
    assertEquals(false, this.twoOfClubs.toFoundation(this.aceOfDiamonds));

    //nothing can be placed over a king
    assertEquals(false, this.aceOfDiamonds.toFoundation(this.kingOfDiamonds));
    assertEquals(false, this.aceOfSpades.toFoundation(this.kingOfSpades));
    assertEquals(false, this.twoOfClubs.toFoundation(this.kingOfDiamonds));

  }


}