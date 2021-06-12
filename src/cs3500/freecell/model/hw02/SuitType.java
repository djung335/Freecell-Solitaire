package cs3500.freecell.model.hw02;

/**
 * Type for the four types of suits in a deck of cards.
 */
public enum SuitType {
  HEARTS("♥"), CLUBS("♣"), DIAMONDS("♦"), SPADES("♠");

  private final String suit;

  /**
   * Constructs a SuitType object with a suit.
   *
   * @param suit the suit of the card as a String
   * @throws IllegalArgumentException if the suit is null
   */
  SuitType(String suit) throws IllegalArgumentException {
    if (suit == null) {
      throw new IllegalArgumentException("suit cannot be a null value");
    }
    this.suit = suit;

  }

  @Override
  public String toString() {
    return this.suit;
  }


}
