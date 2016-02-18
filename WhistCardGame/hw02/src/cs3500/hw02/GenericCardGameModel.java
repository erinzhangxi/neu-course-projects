package cs3500.hw02;

import java.io.IOException;
import java.util.*;

/**
 * The deck of cards are distributed among all the players, so that each player has a subset of
 * cards at any time in the game. Players give up their cards as the game progresses, and the game
 * ends when all players have run out of cards.
 */
public interface GenericCardGameModel<K> {
  /**
   * lists the entire deck of relevant cards.
   *
   * @return a list of generic type of cards
   */
  List<K> getDeck();

  /**
   * Takes in the number of players and the type of cards to distribute these cards in specified
   * order among the players in clock-wise order.
   *
   * @param numPlayers the number of players
   * @param cards      a deck of generic type of cards
   */
  void startPlay(int numPlayers, List<K> cards);

  /**
   * Returns a String that contains the entire state of the game as follows, one on each line:
   * Number of players: N (printed as a normal decimal number)
   * Player 1: cards in sorted order (printed as a comma-separated list)
   * Player 2: cards in sorted order (printed as a comma-separated list)
   * ...
   * Player N: cards in sorted order (printed as a comma-separated list)
   *
   * @return a String that represents the state of the game.
   */
  String getGameState() throws IOException;
}
