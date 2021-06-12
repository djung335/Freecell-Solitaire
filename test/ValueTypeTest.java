import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.hw02.ValueType;
import org.junit.Test;

/**
 * Test class for ValueType; unit tests to ensure that ValueType methods work as intended.
 */
public class ValueTypeTest {

  ValueType two = ValueType.TWO;
  ValueType three = ValueType.THREE;
  ValueType four = ValueType.FOUR;
  ValueType five = ValueType.FIVE;
  ValueType six = ValueType.SIX;
  ValueType seven = ValueType.SEVEN;
  ValueType eight = ValueType.EIGHT;
  ValueType nine = ValueType.NINE;
  ValueType ten = ValueType.TEN;
  ValueType jack = ValueType.JACK;
  ValueType queen = ValueType.QUEEN;
  ValueType king = ValueType.KING;
  ValueType ace = ValueType.ACE;


  // Tests the toString method, which returns the value of the card as a String.
  @Test
  public void testToString() {
    assertEquals("2", two.toString());
    assertEquals("3", three.toString());
    assertEquals("4", four.toString());
    assertEquals("5", five.toString());
    assertEquals("6", six.toString());
    assertEquals("7", seven.toString());
    assertEquals("8", eight.toString());
    assertEquals("9", nine.toString());
    assertEquals("10", ten.toString());
    assertEquals("J", jack.toString());
    assertEquals("Q", queen.toString());
    assertEquals("K", king.toString());
    assertEquals("A", ace.toString());


  }

  // Tests the getValue method, which returns the numerical value as an integer.
  @Test
  public void testGetValue() {
    assertEquals(2, two.getValue());
    assertEquals(3, three.getValue());
    assertEquals(4, four.getValue());
    assertEquals(5, five.getValue());
    assertEquals(6, six.getValue());
    assertEquals(7, seven.getValue());
    assertEquals(8, eight.getValue());
    assertEquals(9, nine.getValue());
    assertEquals(10, ten.getValue());
    assertEquals(11, jack.getValue());
    assertEquals(12, queen.getValue());
    assertEquals(13, king.getValue());
    assertEquals(1, ace.getValue());


  }
}