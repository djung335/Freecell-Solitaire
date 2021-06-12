package cs3500.freecell.model.hw02;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the Freecell Game class, which implements methods that are shared between different
 * types of Freecell games.
 */
public abstract class FreecellGame implements FreecellModel<Card> {

  protected List<List<Card>> foundationPiles;
  protected List<List<Card>> cascadePiles;
  protected List<List<Card>> openPiles;


  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<Card>();

    for (SuitType st : SuitType.values()) {
      for (ValueType vt : ValueType.values()) {
        deck.add(new Card(vt, st));
      }

    }
    return deck;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {

    // this function should throw an exception in these situations, as well as an invalid deck
    if (deck == null || numCascadePiles < 4 || numOpenPiles < 1) {
      throw new IllegalArgumentException("invalid parameters");
    }

    // check validity of deck:
    // It does not have 52 cards
    // It has duplicate cards
    // It has at least one invalid card (invalid suit or invalid number)
    // the 3rd condition will never happen due to enumeration use
    // and how null is handled in the card constructor
    if (deck.size() != 52 || !this.noDuplicates(deck)) {
      throw new IllegalArgumentException("not a valid deck");
    }

    // create a copy of the deck
    // although this is a shallow copy, my Card fields are private and final
    // so I believe this was the best choice.
    ArrayList<Card> copy = new ArrayList<Card>();
    for (Card c : deck) {
      copy.add(c);

    }
    // shuffles the deck if necessary
    if (shuffle) {
      Collections.shuffle(copy);
    }

    this.foundationPiles = new ArrayList<List<Card>>();
    for (int i = 0; i < 4; i++) {
      this.foundationPiles.add(new ArrayList<Card>());
    }

    this.cascadePiles = new ArrayList<List<Card>>();
    // initialize all the indexes of the cascade pile to an empty ArrayList of Card
    for (int i = 0; i < numCascadePiles; i++) {
      this.cascadePiles.add(new ArrayList<Card>());
    }

    // creates the cascade piles
    int index = 0;
    while (copy.size() > 0) {
      this.cascadePiles.get(index % numCascadePiles).add(copy.get(0));
      copy.remove(0);
      index++;
    }

    this.openPiles = new ArrayList<List<Card>>();
    // initialize all the indexes of the open pile to an empty ArrayList of Card
    for (int i = 0; i < numOpenPiles; i++) {
      this.openPiles.add(new ArrayList<Card>());
    }


  }

  /**
   * Determine whether a given deck of cards has any duplicates in it.
   *
   * @param deck the deck to be dealt
   * @return true if the deck has no duplicates, false otherwise
   */
  private boolean noDuplicates(List<Card> deck) {
    // Note: this method has no need to check a null deck because the main method ensures that all
    // inputs to this function are non-null
    Set<Card> duplicateChecker = new HashSet<Card>();
    for (Card c : deck) {
      if (!duplicateChecker.add(c)) {
        return false;
      }
    }
    return true;
  }


  @Override
  public abstract void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException;




  @Override
  public boolean isGameOver() {
    for (List<Card> ac : this.foundationPiles) {
      if (ac.size() != 13) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (this.foundationPiles == null) {
      throw new IllegalStateException("the game has not started yet");
    } else if (index < 0 || index >= foundationPiles.size()) {
      throw new IllegalArgumentException("index is out of bounds");
    } else {

      return this.foundationPiles.get(index).size();

    }


  }

  @Override
  public int getNumCascadePiles() {
    if (this.cascadePiles == null) {
      return -1;
    } else {
      return this.cascadePiles.size();
    }


  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (this.cascadePiles == null) {
      throw new IllegalStateException("the game has not started");
    } else if (index < 0 || index >= this.cascadePiles.size()) {
      throw new IllegalArgumentException("invalid index");
    } else {
      return this.cascadePiles.get(index).size();
    }
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (this.openPiles == null) {
      throw new IllegalStateException("the game has not started");
    } else if (index < 0 || index >= this.openPiles.size()) {
      throw new IllegalArgumentException("invalid index");
    } else {
      return this.openPiles.get(index).size();
    }
  }

  @Override
  public int getNumOpenPiles() {
    if (this.openPiles == null) {
      return -1;
    } else {

      return this.openPiles.size();

    }
  }

  @Override
  public Card getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (this.foundationPiles == null) {
      throw new IllegalStateException("the game has not started yet");
    } else if (pileIndex < 0 || pileIndex >= this.foundationPiles.size() || cardIndex < 0
        || cardIndex >= this.foundationPiles.get(pileIndex).size()) {
      throw new IllegalArgumentException("one or more invalid indexes");
    } else {
      return this.foundationPiles.get(pileIndex).get(cardIndex);
    }


  }

  @Override
  public Card getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {

    if (this.cascadePiles == null) {
      throw new IllegalStateException("the game has not started yet");
    } else if (pileIndex < 0 || pileIndex >= this.cascadePiles.size() || cardIndex < 0
        || cardIndex >= this.cascadePiles.get(pileIndex).size()) {
      throw new IllegalArgumentException("one or more invalid indexes");
    } else {
      return this.cascadePiles.get(pileIndex).get(cardIndex);
    }
  }

  @Override
  public Card getOpenCardAt(int pileIndex) throws
      IllegalArgumentException, IllegalStateException {
    if (this.openPiles == null) {
      throw new IllegalStateException("game has not started");
    } else if (pileIndex >= this.openPiles.size() || pileIndex < 0
    ) {
      throw new IllegalArgumentException("invalid index");
    } else {
      if (this.openPiles.get(pileIndex).isEmpty()) {
        return null;
      } else {

        return this.openPiles.get(pileIndex).get(0);
      }
    }
  }



}
