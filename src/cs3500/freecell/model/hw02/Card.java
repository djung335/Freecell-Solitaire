package cs3500.freecell.model.hw02;


import java.util.Objects;

/**
 * This class represents the type of cards to be used within the game, which have a value (2, 3, 4
 * ... Q, K, A) and a suit (♣, ♥, ♦, ♠).
 */
public class Card {

  private final ValueType value;
  private final SuitType suit;

  /**
   * Constructs a Card object with a value and a suit.
   *
   * @param value the value of the Card (see ValueType)
   * @param suit  the suit of the Card (see SuitType)
   * @throws IllegalArgumentException if either the value or the suit is null
   */
  public Card(ValueType value, SuitType suit) throws IllegalArgumentException {
    if (value == null || suit == null) {
      throw new IllegalArgumentException("value or suit cannot be null");
    }
    this.value = value;
    this.suit = suit;
  }

  @Override
  public String toString() {
    return this.value.toString() + this.suit.toString();

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Card)) {
      return false;
    }

    Card c = (Card) o;

    return this.suit == c.suit && this.value == c.value;

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.value, this.suit);
  }


  /**
   * Determines whether or not this Card can be added to a Cascade pile given the last Card of that
   * pile.
   *
   * @param c the Card of comparison
   * @return true if this Card can be added to the Cascade pile, false otherwise
   */

  public boolean toCascade(Card c) {

    return !this.colorOfCard().equals(c.colorOfCard()) && c.oneOver(this);

  }


  /**
   * Determines whether or not this Card can be added to a Foundation pile given the last Card of
   * that pile.
   *
   * @param c the Card of comparison
   * @return true if this Card can be added to the Foundation pile, false otherwise
   */
  public boolean toFoundation(Card c) {

    return this.oneOver(c) && this.suit == c.suit;

  }

  private enum ColorType {
    RED, BLACK;
  }


  /**
   * Determines the color of this Card. Cards of the suit Hearts and Diamonds are red, while Cards
   * of the suit Clubs and Spades are black.
   *
   * @return the Color of the card
   */
  private ColorType colorOfCard() {
    if (this.suit == SuitType.HEARTS || this.suit == SuitType.DIAMONDS) {

      return ColorType.RED;
    } else {
      return ColorType.BLACK;
    }

  }

  /**
   * Determines if this Card's value is exactly one over a given Card's value.
   *
   * @param c the Card of comparison
   * @return true if the Card's value is exactly one over the given Card's value, false otherwise
   */
  private boolean oneOver(Card c) {
    return this.value.getValue() - c.value.getValue() == 1;
  }


}
