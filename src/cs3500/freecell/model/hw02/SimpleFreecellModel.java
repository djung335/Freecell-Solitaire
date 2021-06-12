package cs3500.freecell.model.hw02;

import cs3500.freecell.model.PileType;


/**
 * This is the class of the simple Freecell model. It implements the Freecell model using the Card
 * object as well as all its respective methods.
 */
public class SimpleFreecellModel extends FreecellGame {


  @Override
  public void move(cs3500.freecell.model.PileType source, int pileNumber, int cardIndex,
      cs3500.freecell.model.PileType destination, int destPileNumber)
      throws IllegalArgumentException, IllegalStateException {

    if (this.cascadePiles == null) {
      throw new IllegalStateException("the game has not started yet");
    }

    // exceptions for invalid indexes will be handled in their respective helpers
    if (source == null || destination == null || source == PileType.FOUNDATION) {
      throw new IllegalArgumentException("invalid parameters");
    }

    if (source == PileType.CASCADE) {

      if (cardIndex != this.getNumCardsInCascadePile(pileNumber) - 1) {
        throw new IllegalArgumentException("move is not allowed");
      } else {
        this.moveFromCascade(pileNumber, cardIndex, destination, destPileNumber);
      }
    } else if (source == PileType.OPEN) {
      if (this.getOpenCardAt(pileNumber) == null) {
        throw new IllegalArgumentException("no open card at that index");
      } else {
        this.moveFromOpen(pileNumber, cardIndex, destination, destPileNumber);
      }


    }

  }

  /**
   * Move a card from the cascade pile to the given destination pile, if the move is valid.
   *
   * @param pileNumber     the pile number of the cascade pile, starting at 0
   * @param cardIndex      the index of the card to be moved from the cascade pile, starting at 0
   * @param destination    the type of the destination pile (see PileType)
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible
   * @throws IllegalStateException    if a move is attempted before the game has started
   */
  private void moveFromCascade(int pileNumber, int cardIndex,
      cs3500.freecell.model.PileType destination, int destPileNumber)
      throws IllegalArgumentException, IllegalStateException {

    Card c = this.getCascadeCardAt(pileNumber, cardIndex);

    if (destination == PileType.CASCADE) {

      if (this.getNumCardsInCascadePile(destPileNumber) == 0 || (c.toCascade(
          this.getCascadeCardAt(destPileNumber,
              this.getNumCardsInCascadePile(destPileNumber) - 1)))) {
        this.cascadePiles.get(destPileNumber).add(c);
      } else {
        throw new IllegalArgumentException("this move is illegal");
      }


    } else if (destination == PileType.OPEN) {
      if (this.getNumCardsInOpenPile(destPileNumber) == 0) {
        this.openPiles.get(destPileNumber).add(c);

      } else {
        throw new IllegalArgumentException("this move is illegal");
      }

    } else if (destination == PileType.FOUNDATION) {
      if ((this.getNumCardsInFoundationPile(destPileNumber) == 0 && c.toString().charAt(0) == 'A')
          || (c.toFoundation(
          this.getFoundationCardAt(destPileNumber,
              this.getNumCardsInFoundationPile(destPileNumber) - 1)))) {

        this.foundationPiles.get(destPileNumber).add(c);
      } else {
        throw new IllegalArgumentException("illegal move");
      }

    }
    this.cascadePiles.get(pileNumber).remove(cardIndex);
  }

  /**
   * Move a card from the open pile to the given destination pile, if the move is valid.
   *
   * @param pileNumber     the pile number of the open pile, starting at 0
   * @param cardIndex      the index of the card to be moved from the open pile, starting at 0
   * @param destination    the type of the destination pile (see PileType)
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible
   * @throws IllegalStateException    if a move is attempted before the game has started
   */
  private void moveFromOpen(int pileNumber, int cardIndex,
      cs3500.freecell.model.PileType destination, int destPileNumber)
      throws IllegalArgumentException, IllegalStateException {
    Card c = this.getOpenCardAt(pileNumber);
    if (destination == PileType.CASCADE) {
      if (this.getNumCardsInCascadePile(destPileNumber) == 0 || c
          .toCascade(this.getCascadeCardAt(destPileNumber,
              this.getNumCardsInCascadePile(destPileNumber) - 1))) {

        this.cascadePiles.get(destPileNumber).add(c);
      } else {
        throw new IllegalArgumentException("Illegal move");

      }

    } else if (destination == PileType.FOUNDATION) {
      // first condition checks to see if there is no cards in the foundation pile and the current
      // card is an ace, the second condition checks if this card can be moved to the foundation.
      if ((this.getNumCardsInFoundationPile(destPileNumber) == 0 && c.toString().charAt(0) == 'A')
          || (c.toFoundation(this.getFoundationCardAt(destPileNumber,
          this.getNumCardsInFoundationPile(destPileNumber) - 1)))) {
        this.foundationPiles.get(destPileNumber).add(c);
      } else {
        throw new IllegalArgumentException("illegal move");
      }


    } else if (destination == PileType.OPEN) {
      if (this.getNumCardsInOpenPile(destPileNumber) == 0) {
        this.openPiles.get(destPileNumber).add(c);

      } else {
        throw new IllegalArgumentException("illegal move");
      }
    }
    this.openPiles.get(pileNumber).remove(cardIndex);


  }


}

