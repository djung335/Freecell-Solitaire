package cs3500.freecell.model.hw02;

/**
 * Type for the thirteen types of values in a deck of cards.
 */
public enum ValueType {
  TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE(
      "9"), TEN("10"), JACK("11"), QUEEN("12"), KING("13"), ACE("1");

  private final String value;

  /**
   * Constructs a ValueType object with a value.
   *
   * @param value the value of the card as a String
   * @throws IllegalArgumentException if the value is null
   */
  ValueType(String value) throws IllegalArgumentException {
    if (value == null) {
      throw new IllegalArgumentException("value cannot be null");
    }
    this.value = value;

  }

  @Override
  public String toString() {

    if (this.value.equals("11")) {
      return "J";
    } else if (this.value.equals("12")) {
      return "Q";
    } else if (this.value.equals("13")) {
      return "K";
    } else if (this.value.equals("1")) {
      return "A";
    } else {
      return value;
    }
  }

  /**
   * Gets the ValueType's value as a number.
   *
   * @return the value as a number
   */
  public int getValue() {
    return Integer.parseInt(this.value);
  }


}
