package cs3500.hw03;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import cs3500.hw02.StandardCard;

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

//package cs3500.hw03;
//
//        import java.io.IOException;
//        import java.io.InputStreamReader;
//        import java.util.List;
//        import java.util.Objects;
//        import java.util.Scanner;
//
//        import cs3500.hw03.CardGameModel;
//        import cs3500.hw03.WhistModel;
//        import cs3500.hw02.Card;
//        import cs3500.hw04.WhistTrumpModel;
//
//public class WhistController implements IWhistController {
//
//    final Readable in;
//    final Appendable out;
//
//    public WhistController(Readable in, Appendable out) {
//        this.in = in;
//        this.out = out;
//      }
//
//    public <K> void startGame(CardGameModel<K> game, int numPlayers){
//        List<K> deck = game.getDeck();
//        game.startPlay(numPlayers, deck);
//        Scanner scan = new Scanner(in);
//        try {
//            out.append(game.getGameState());
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
//
//
//        while (!game.isGameOver()) {
//
//            try {
//                out.append(System.lineSeparator() + "Card: ");
//              } catch (IOException e) {
//                e.printStackTrace();
//              }
//            int cardIdx;
//            while (!scan.hasNext()) {
//                scan.next();
//              }
//            String in = scan.next();
//            try {
//                cardIdx = Integer.parseInt(in);
//              }
//            catch (NumberFormatException e) {
//                try {
//                    out.append("Invalid input: " + in);
//                  } catch (IOException el) {
//                    el.printStackTrace();
//                  }
//                continue;
//              }
//
//            try {
//                game.play(game.getCurrentPlayer(), cardIdx);
//              }
//
//            catch(IllegalArgumentException e) {
//                try {
//                    out.append("Invalid index :" + cardIdx + " Play again\n" );
//                  } catch(IOException el) {
//                    el.printStackTrace();
//                  }
//                continue;
//              }
//            try {
//                out.append(game.getGameState() + "\n");
//              } catch (IOException e) {
//                e.printStackTrace();
//              }
//          }
//
////    try {
////      out.append(game.getGameState());
////    } catch (IOException e) {
////      e.printStackTrace();
////    }
//
//
//      }
//
//
//
//
//
//
//    public static void main(String[] args){
//
//        Scanner scan=new Scanner(System.in);
//        WhistController whistController =
//                new WhistController(new InputStreamReader(System.in), System.out);
//        CardGameModel game = new WhistModel();
//        CardGameModel game1 = new WhistTrumpModel();
//        whistController.startGame(game1, 5);
//
//      }
//}






