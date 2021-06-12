package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModelState;
import java.io.IOException;

/**
 * This is the class for the Freecell text view, which implements methods from the interface
 * FreecellView in order to present a textual representation of the game.
 */
public class FreecellTextView implements FreecellView {

  private final FreecellModelState<?> model;
  private final Appendable appendable;

  /**
   * Constructs an object of a FreecellTextView given a FreecellModelState.
   *
   * @param model the Freecell model given
   * @throws IllegalArgumentException if the model is null
   */
  public FreecellTextView(FreecellModelState<?> model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("null model not allowed");
    }

    this.model = model;
    this.appendable = new StringBuilder();

  }

  /**
   * Constructs an object of a FreecellTextView given a FreecellModelState and an Appendable.
   *
   * @param model      the Freecell model given
   * @param appendable the appendable given
   * @throws IllegalArgumentException if the model or the appendable is null
   */
  public FreecellTextView(FreecellModelState<?> model, Appendable appendable)
      throws IllegalArgumentException {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("parameters cannot be null");
    }

    this.model = model;
    this.appendable = appendable;

  }

  @Override
  public String toString() {
    if (model.getNumOpenPiles() == -1) {
      return "";
    } else {
      StringBuilder textView = new StringBuilder();
      textView.append(this.foundationToText());
      textView.append(this.openToText());
      textView.append(this.cascadeToText());
      return textView.toString();
    }

  }

  @Override
  public void renderBoard() throws IOException {
    this.appendable.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }

  /**
   * Provides a textual representation of the Model's foundation piles, including the card's within
   * them.
   *
   * @return a textual representation of the Model's foundation piles in String format
   */
  private String foundationToText() {
    StringBuilder foundationTextView = new StringBuilder();
    for (int i = 0; i < 4; i++) {
      foundationTextView.append("F" + Integer.toString(i + 1) + ":");
      for (int j = 0; j < this.model.getNumCardsInFoundationPile(i); j++) {
        foundationTextView.append(" " + this.model.getFoundationCardAt(i, j).toString());

        if (j != this.model.getNumCardsInFoundationPile(i) - 1) {
          foundationTextView.append(",");

        }
      }
      foundationTextView.append("\n");

    }
    return foundationTextView.toString();
  }

  /**
   * Provides a textual representation of the Model's open piles, including the card's within them.
   *
   * @return a textual representation of the Model's open piles in String format
   */
  private String openToText() {
    StringBuilder openTextView = new StringBuilder();
    for (int i = 0; i < this.model.getNumOpenPiles(); i++) {
      openTextView.append("O" + Integer.toString(i + 1) + ":");
      for (int j = 0; j < this.model.getNumCardsInOpenPile(i); j++) {
        openTextView.append(" " + this.model.getOpenCardAt(i).toString());

        if (j != this.model.getNumCardsInOpenPile(i) - 1) {
          openTextView.append(",");

        }

      }
      openTextView.append("\n");

    }
    return openTextView.toString();
  }

  /**
   * Provides a textual representation of the Model's cascade piles, including the card's within
   * them.
   *
   * @return a textual representation of the Model's cascade piles in String format
   */
  private String cascadeToText() {
    StringBuilder cascadeTextView = new StringBuilder();
    for (int i = 0; i < this.model.getNumCascadePiles(); i++) {
      cascadeTextView.append("C" + Integer.toString(i + 1) + ":");
      for (int j = 0; j < this.model.getNumCardsInCascadePile(i); j++) {

        cascadeTextView.append(" " + this.model.getCascadeCardAt(i, j).toString());

        if (j != this.model.getNumCardsInCascadePile(i) - 1) {
          cascadeTextView.append(",");

        }

      }

      if (i != this.model.getNumCascadePiles() - 1) {
        cascadeTextView.append("\n");
      }


    }
    return cascadeTextView.toString();


  }


}
