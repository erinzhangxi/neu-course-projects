package cs3500.hw03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.hw02.GenericStandardDeckGame;
import cs3500.hw02.Player;
import cs3500.hw02.StandardCard;
import cs3500.hw02.StandardCard.Suit;
import cs3500.hw02.StandardCardComparator;


/**
 * A Whist Model Game implements CardGameModel<K> class and extends GenericStandardDeckGame
 * class. It determines how the whist game is played and passed in user values by controller.
 */
public class WhistModel extends GenericStandardDeckGame
        implements CardGameModel<StandardCard> {

  private int handWinner;
  private int curPlayer;
  private StandardCard.Suit curSuit;
  private ArrayList<StandardCard> handCards;

  /**
   * zero-constructor
   */
  public WhistModel() {
    super();
    handWinner = 0;
    curPlayer = 0;
    curSuit = null;
    handCards = new ArrayList<StandardCard>();
  }

  /**
   * Constructs a new whist game model with the default parameters.
   *
   * @param cards      deck of all the cards in the game
   * @param players    list of players in the game(including both won and still playing)
   * @param handWinner hand winner from last hand, decides the suit of this hand
   * @param curPlayer  current player whose turn to play card
   * @param curSuit    current suit of the game, every player needs to play the same suit. Suit
   *                   determined by the handWinner
   */
  public WhistModel(ArrayList<StandardCard> cards, ArrayList<Player> players, int handWinner,
                    int curPlayer, Suit curSuit, ArrayList<StandardCard> handCards) {
    this.cards = cards;
    this.players = players;
    this.handWinner = handWinner;

    this.curPlayer = curPlayer;
    this.curSuit = curSuit;
    this.handCards = handCards;
  }

  /**
   * Get the current hand's winner
   *
   * @return the index of the current winner of this hand
   */
  public int getHandWinner() {
    return this.handWinner;
  }

  /**
   * Updates the current handWinner of the game
   *
   * @param hw current handWinner
   */
  public void updateHandWinner(int hw) {
    this.handWinner = hw;
  }

  /**
   * Get the current suit of this hand
   *
   * @return the suit of this hand
   */
  public Suit getCurSuit() {
    return this.curSuit;
  }

  /**
   * Updates the current suit
   *
   * @param s new current suit
   */
  public void updateCurSuit(Suit s) {
    this.curSuit = s;
  }

  /**
   * Get the cards all players played in this hand
   *
   * @return an array list of cards played in this hand
   */
  public ArrayList<StandardCard> getHandCards() {
    return this.handCards;
  }

  /**
   * Adds the appropriate card to the current hand. This method should advance the game. If this is
   * the first play in the current hand, this card will determine this hand’s suit. If this is the
   * last play in the current hand, it must determine who won the hand and update the tally of hands
   * for that player.
   *
   * In any case, it should also determine who will play next. If the inputs are invalid (it is not
   * the player’s turn, the position cardIdx is invalid), or if the current card cannot be played
   * (i.e. it is of the wrong suit even though the player has a card of the correct suit), the
   * method should throw an IllegalArgumentException.
   *
   * <p><strong>INVARIANT:</strong>  0 <= playerNo <= players.size()
   * <p><strong>INVARIANT:</strong> 0 <= cardIdx <= number of cards of each player
   *
   * @param playerNo the index of current player
   * @param cardIdx  the index of card the playing is going to play
   */

  public void play(int playerNo, int cardIdx) {
    if (!isGameOver()) {
      if (playerNo == curPlayer) {
        if (cardIdx >= 0 && cardIdx < players.get(playerNo).getCards().size()) {
          Player player = players.get(playerNo);
          StandardCard cardPlayed = (StandardCard) player.getCards().get(cardIdx);

          // if this is the first player of the hand
          if (playerNo == handWinner) {
            handCards.add(cardPlayed);
            curSuit = cardPlayed.getSuit();
            curPlayer = (curPlayer + 1) % players.size();
            player.getCards().remove(cardPlayed);
          }
          // if this is the last player of the hand
          else if (stopLastPlay()) {
              handCards.add(cardPlayed);
              handWinner = getMax(handCards);
              players.get(handWinner).updateHandsWon(1);
              curPlayer = handWinner;
              player.getCards().remove(cardPlayed);
              handCards.clear();
          }
          // if this is neither the first nor the last
          else {
              handCards.add(cardPlayed);
              player.getCards().remove(cardPlayed);
              curPlayer = (curPlayer + 1) % players.size();
          }
        }
        else {
          throw new IllegalArgumentException("Card Index out of bound.");
        }
      }
      else {
        throw new IllegalArgumentException("Invalid player Index.");
      }
    }
  }

  public boolean stopLastPlay() {
    if (players.size() - 1 == handCards.size()) {
      return true;
    }
    else if (players.get((curPlayer + 1) % players.size()).getCards().size() == 0) {
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Get the index of the card with the biggest value
   *
   * <p><strong>INVARIANT:</strong> returns an int 0 <= i <= size of laid
   *
   * @param laid all cards played in a hand
   *
   * @return the biggest card
   */
  public int getMax(List<StandardCard> laid) {
    curSuit = laid.get(0).getSuit();
    int max = 0;

    for (int i = 1; i < laid.size(); i++) {
      if (laid.get(i).getSuit() == curSuit) {
        if (laid.get(i - 1).rankValue() < laid.get(i).rankValue()) {
          max = i;
        }
      }
    }
    return max;
  }

  /**
   * Get the current player of the hand
   *
   * <p><strong>INVARIANT:</strong> always return an int larger than or equal to 0, or less
   * than or equal to number of players in the hand
   *
   * @return an index representing the current player
   */

  public int getCurrentPlayer() {
    return curPlayer;
  }

  /**
   * Updates the current player
   *
   * @param cur the new current player
   */
  public void updateCurPlayer(int cur) {
    curPlayer = cur;
  }

  /**
   * Determines if the game is over.
   *
   * @return true if the game is over.
   */
  public boolean isGameOver() {

    if (getPlayers().size() == 0) {
      throw new IllegalArgumentException("Game not started");
    } else if (noPlayer()) {
      return true;
    } else if (onePlayer() && getHandCards().size() == 0) {
      return true;
    }
    else {
      return false;
    }

  }


  public boolean noPlayer() {
    int remainingPlayer = 0;
    for (Player<StandardCard> p : players) {
      if (p.getCards().size() != 0) {
        remainingPlayer += 1;
      }
    }
    return remainingPlayer == 0;

  }

  public boolean onePlayer() {
    int remainingPlayer = 0;
    for (Player<StandardCard> p : players) {
      if (p.getCards().size() != 0) {
        remainingPlayer += 1;
      }
    }
    return remainingPlayer == 1;
  }

  /**
   * Returns the winner of the game.
   *
   * <p><strong>PRECONDITION:</strong> the game is over and has a winner
   *
   * @return the player who wins the most hands
   * @throws IllegalStateException if {@code getStatus() != Status.Won}
   */
  public ArrayList<Integer> getWinner() {
    if (!isGameOver()) {
      throw new IllegalStateException("No one won the game yet.");
    } else {
      ArrayList<Integer> winners = new ArrayList<Integer>();
      ArrayList<Integer> handsWonList = new ArrayList<Integer>();

      for (int i = 0; i < players.size(); i++) {
        handsWonList.add(players.get(i).getHandsWon());
      }

      int mostWon = Collections.max(handsWonList);

      for (int i = 0; i < players.size(); i++) {
        if (players.get(i).getHandsWon() == mostWon) {
          winners.add(i);
        }
      }
      return winners;
    }
  }

  @Override
  public void startPlay(int numPlayers, List<StandardCard> cards) {
    super.startPlay(numPlayers, cards);

    StandardCardComparator comp = new StandardCardComparator();
    for (int i = 0; i < numPlayers; i++) {
      Collections.sort(players.get(i).getCards(), comp);
    }
  }

  @Override
  /**
   * The comma-separated lists should separate each item by a single comma and a single space;
   * there should be no trailing comma or space after the last item.
   *
   * The special message in the end should be either "Turn: Player X" if the game is ongoing
   * and it is player X’s turn to play next, or "Game over. Player X won."
   * if the game is over and player X has won.
   */
  public String getGameState() {

    String result = super.getGameState();

    for (int k = 0; k < players.size(); k++) {
      result += "Player " + Integer.toString(k + 1) + ": " +
              Integer.toString(players.get(k).getHandsWon()) + " hands won\n";

    }


    if (isGameOver()) {
      if (getWinner().size() == 1) {
        result += "Game over. Player " +
                Integer.toString(getWinner().get(0) + 1) + " won";
      } else if (getWinner().size() > 1) {
        result += "Game over. Stalemate.";

      }
    } else {
        result += "Turn: Player " + Integer.toString(curPlayer + 1);
      }
    return result;
  }




  @Override
  public List<StandardCard> getDeck() {

    List<StandardCard> tempCards = super.getDeck();

    Collections.shuffle(tempCards);
    return tempCards;
  }
}
