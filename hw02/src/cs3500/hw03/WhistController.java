package cs3500.hw03;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import cs3500.hw02.StandardCard;
import cs3500.hw02.StandardCardComparator;

/**
 * This Whist controller should accept and store Readable and Appendable for doing input and output.
 * Readable reads from user and Appendable writes to them. Ultimately this controller transmits a
 * String to an Appendable and read integers from a Readable object.
 */
public class WhistController implements IWhistController {
  private final Readable rd;
  private final Appendable ap;
  private CardGameModel<StandardCard> cardGame;

  /**
   * Constructs a whist controller
   *
   * @param rd reads in user's input
   * @param ap transmits the output
   */
  public WhistController(Readable rd, Appendable ap) {
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void startGame(CardGameModel game, int numPlayers) {
    this.cardGame = game;
    cardGame.startPlay(numPlayers, cardGame.getDeck());
    run();
  }

  private void run() {


    while (!cardGame.isGameOver()) {
      try {

        ap.append(cardGame.getGameState() + "\n");
        ap.append("Please choose a card: \n");
        Scanner card = new Scanner(rd);
        int cardIdx = card.nextInt() - 1;

        cardGame.play(cardGame.getCurrentPlayer(), cardIdx);
      }
      catch (IllegalArgumentException ie) {
        try {
          ap.append("Try again, that was invalid input: " + ie.getMessage()
                  + "\n");
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      catch (IOException e) {
        try {
          ap.append("Try again, that was invalid input: " + e.getMessage()
                  + "\n");
        }
        catch (IOException e1) {
          e.printStackTrace();
        }
      }
      catch (InputMismatchException ee) {

      }
    }

    try {
      ap.append(cardGame.getGameState() + "\n");
    } catch (IOException e) {
      try {
        ap.append("Try again, that was invalid input: " + e.getMessage()
                + "\n" + "\n");
      } catch (IOException e1) {
        e.printStackTrace();
      }
    }
  }


  public static void main(String[] args) {

    WhistModel game = new WhistModel();
    boolean playing = true;
    WhistController wc = new WhistController(new InputStreamReader(System.in), System.out);

    while (playing) {
      try {
        try {
          wc.ap.append("Please enter the number of players: " + "\n");
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        Scanner in1 = new Scanner(wc.rd);
        int numPlayer = in1.nextInt();

        try {
          wc.startGame(game, numPlayer);
          playing = false;
        }
        catch (IllegalArgumentException ie) {
          try {
            wc.ap.append("Try again, that was invalid input: " + ie.getMessage() + "\n" + "\n");
          }
          catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      catch (InputMismatchException e) {

      }
    }

    try {
      wc.ap.append(game.getGameState());
    }
    catch (IOException e) {
      e.printStackTrace();
    }

  }
}






