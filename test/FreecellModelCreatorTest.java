import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.hw02.MultiFreecellModel;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import org.junit.Test;

/**
 * Test class for FreecellModelCreator; unit tests to ensure that FreecellModelCreator methods work
 * as intended.
 */
public class FreecellModelCreatorTest {

  FreecellModelCreator creator = new FreecellModelCreator();

  // Tests to see if the FreecellModelCreator correctly creates a SimpleFreecellModel.
  @Test
  public void testCreateSimple() {
    assertEquals(SimpleFreecellModel.class, this.creator.create(GameType.SINGLEMOVE).getClass());
  }

  // Tests to see if the FreecellModelCreator correctly creates a MultiFreecellModel.
  @Test
  public void testCreateMulti() {
    assertEquals(MultiFreecellModel.class, this.creator.create(GameType.MULTIMOVE).getClass());
  }

  // Tests to see if the FreecellModelCreator create method throws an IllegalArgumentException
  // when given a null gameType.
  @Test(expected = IllegalArgumentException.class)
  public void testNullGameType() {
    this.creator.create(null);
  }
}