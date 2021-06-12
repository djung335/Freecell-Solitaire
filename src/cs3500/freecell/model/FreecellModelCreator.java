package cs3500.freecell.model;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.MultiFreecellModel;
import cs3500.freecell.model.hw02.SimpleFreecellModel;

/**
 * This class represents the FreecellModelCreator, which will create different freecell models based
 * on a given game type.
 */
public class FreecellModelCreator {

  /**
   * Creates a model of Freecell based on a given desired game type.
   *
   * @param type the desired GameType
   * @return the specific type of FreecellModel
   * @throws IllegalArgumentException if the GameType is null or not singlemove/multimove.
   */
  public static FreecellModel<Card> create(GameType type) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("GameType cannot be null.");
    } else if (type == GameType.SINGLEMOVE) {
      return new SimpleFreecellModel();
    } else if (type == GameType.MULTIMOVE) {
      return new MultiFreecellModel();
    } else {
      throw new IllegalArgumentException("GameType not supported.");
    }

  }

  /**
   * Type for the two different versions of Freecell that can be played.
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }
}



