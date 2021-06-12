package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * This is the class for the simple Freecell controller, which implements methods from the interface
 * FreecellController in order to provide a game of Freecell.
 */
public class SimpleFreecellController implements FreecellController<Card> {

  private final FreecellModel<Card> model;
  private final Readable rd;
  private final FreecellView view;

  /**
   * Constructs an object of a SimpleFreecellController given a FreecellModel, a Readable, and a
   * Appendable.
   *
   * @param model the FreecellModel which enforces all the rules
   * @param rd    the given Readable to take in input
   * @param ap    the given Appendable to display the game
   * @throws IllegalArgumentException if the model, readable, or appendable are null.
   */
  public SimpleFreecellController(FreecellModel<Card> model, Readable rd, Appendable ap)
      throws IllegalArgumentException {
    if (model == null || rd == null || ap == null) {
      throw new IllegalArgumentException(
          "Parameters in SimpleFreecellController constructor cannot be null.");
    }

    this.model = model;
    this.rd = rd;
    this.view = new FreecellTextView(this.model, ap);

  }


  @Override
  public void playGame(List<Card> deck, int numCascades, int numOpens, boolean shuffle)
      throws IllegalStateException, IllegalArgumentException {
    if (deck == null) {
      throw new IllegalArgumentException("deck cannot be null");
    }

    try {
      this.model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e) {
      this.handleIORenderMessage("Could not start game.");
      return;
    }

    this.handleIORenderBoard();
    this.handleIORenderMessage("\n");

    Scanner fileScanner = new Scanner(this.rd);

    String commandOne = null;
    String commandTwo = null;
    String commandThree = null;

    while (fileScanner.hasNext()) {
      String line = fileScanner.next();

      for (String s : line.split(" ")) {

        if (s.equalsIgnoreCase("q")) {
          this.handleIORenderMessage("Game quit prematurely.");
          return;
        }

        if (commandOne == null) {
          if (this.isInvalidInput(s)) {
            this.handleIORenderMessage("Enter a valid source pile and index." + "\n");
          } else {
            commandOne = s;
          }

        } else if (commandTwo == null) {
          if (!this.isStringAnInteger(s)) {
            this.handleIORenderMessage("Enter a valid card index." + "\n");

          } else {
            commandTwo = s;
          }

        } else if (commandThree == null) {

          if (this.isInvalidInput(s)) {
            this.handleIORenderMessage("Enter a valid destination pile and index." + "\n");


          } else {
            commandThree = s;
            // execute command here
            try {
              this.model.move(this.determinePileType(commandOne.charAt(0)),
                  Integer.parseInt(commandOne.substring(1)) - 1, Integer.parseInt(commandTwo) - 1,
                  this.determinePileType(commandThree.charAt(0)),
                  Integer.parseInt(commandThree.substring(1)) - 1);

              this.handleIORenderBoard();
              this.handleIORenderMessage("\n");

              if (model.isGameOver()) {
                this.handleIORenderMessage("Game over.");
                return;
              }


            } catch (IllegalArgumentException e) {
              this.handleIORenderMessage("Invalid move. Try again." + "\n");

            }

            commandOne = null;
            commandTwo = null;
            commandThree = null;
          }


        }


      }


    }
    if (!fileScanner.hasNext()) {
      throw new IllegalStateException("Reading from the provided Readable has failed.");
    }


  }


  /**
   * Utilizes the view in order to render a given message to the console.
   *
   * @param msg the message as a String to be rendered
   * @throws IllegalStateException if renderMessage throws an IOException
   */
  private void handleIORenderMessage(String msg) throws IllegalStateException {
    try {
      view.renderMessage(msg);
    } catch (IOException io) {
      throw new IllegalStateException(
          "transmission of the message to the provided data destination has failed");
    }

  }

  /**
   * Utilizes the view in order to render the Freecell board to the console.
   *
   * @throws IllegalStateException if renderBoard throws an IOException
   */
  private void handleIORenderBoard() throws IllegalStateException {
    try {
      this.view.renderBoard();
    } catch (IOException io) {
      throw new IllegalStateException(
          "transmission of the board to the provided data destination has failed.");
    }

  }

  /**
   * Determines if a given command is an invalid input for the pile and pile index command.
   *
   * @param s the given command as a String
   * @return true if the given command is an invalid input, false otherwise.
   */
  private boolean isInvalidInput(String s) {
    return (s.charAt(0) != 'F' && s.charAt(0) != 'O' && s.charAt(0) != 'C') || (!this
        .isStringAnInteger(s.substring(1)));
  }


  /**
   * Determines if a given String is an integer.
   *
   * @param s the given String
   * @return true if the given String is an integer, false otherwise.
   */
  private boolean isStringAnInteger(String s) {
    if (s.length() == 0) {
      return false;
    }

    for (int i = 0; i < s.length(); i++) {
      if (!Character.isDigit(s.charAt(i))) {
        return false;
      }
    }

    return true;
  }

  /**
   * * Determines a PileType given a character that represents it.
   *
   * @param c the character that represents the PileType
   * @return the PileType that the character represents
   * @throws IllegalArgumentException if the given character doesn't represent any PileType.
   */
  private PileType determinePileType(char c) throws IllegalArgumentException {

    if (c == 'O') {
      return PileType.OPEN;
    } else if (c == 'F') {
      return PileType.FOUNDATION;
    } else if (c == 'C') {
      return PileType.CASCADE;
    } else {
      throw new IllegalArgumentException("PileType doesn't exist.");
    }

  }


}
