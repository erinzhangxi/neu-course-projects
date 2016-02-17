package cs3500.hw03;


import cs3500.hw02.GenericCardGameModel;

/**
 * The interface of this game’s model supports the functionality specified by
 * the GenericCardGameModel<K> interface and some additional operations specific to
 * this game.
 */

interface CardGameModel<K> extends GenericCardGameModel<K> {
  /**
   * plays the card at index cardIdx in the set of cards for player number
   * playerNo. It is assumed that both player numbers and card indices begin
   * with 0. It is further assumed that players’ hands are sorted.
   *
   * @param playerNo the index of player
   * @param cardIdx the index of card
   */
  void play(int playerNo, int cardIdx);

  /**
   * Gets the index of the player
   *
   * @return the player whose turn it is to play.
   */
  int getCurrentPlayer();

  /**
   * Determines if the game is over.
   *
   * @return true if the game is over, false otherwise.
   */
  boolean isGameOver();
}
