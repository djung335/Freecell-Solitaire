import org.junit.Test;
import java.io.IOException;

/**
 * Test class for MockAppendable; unit tests to ensure that MockAppendable methods work as
 * intended.
 */
public class MockAppendableTest {

  Appendable mockAppendable = new MockAppendable();

  // Test the append method with a String
  @Test(expected = IllegalArgumentException.class)
  public void testAppendString() {

    try {
      this.mockAppendable.append("hello!");
    } catch (IOException io) {
      throw new IllegalArgumentException("IO Exception has occurred.");
    }

  }

  // Test the append method with a char
  @Test(expected = IllegalArgumentException.class)
  public void testAppendChar() {

    try {
      this.mockAppendable.append('a');
    } catch (IOException io) {
      throw new IllegalArgumentException("IO Exception has occurred.");
    }

  }


}