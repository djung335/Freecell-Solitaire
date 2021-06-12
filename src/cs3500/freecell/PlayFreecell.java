package cs3500.freecell;

import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.hw02.MultiFreecellModel;

import java.io.InputStreamReader;

/**
 * A class which serves as a way to play the Freecell game through its main method.
 */
public class PlayFreecell {


  /**
   * The main function through which the Freecell game is played.
   *
   * @param args an array of Strings which store command line arguments.
   */
  public static void main(String[] args) {

    FreecellController controller = new SimpleFreecellController(new MultiFreecellModel(),
        new InputStreamReader(System.in), System.out);

    controller.playGame(new MultiFreecellModel().getDeck(), 52, 4, false);

  }


}
