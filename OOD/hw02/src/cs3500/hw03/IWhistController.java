package cs3500.hw03;

import cs3500.hw02.GenericCardGameModel;

/**
 * The controller interface
 */
interface IWhistController {
  /**
   * Starts the provided game with the provided number of players, and return
   * only after the game has ended.
   *
   * @param game the given card game
   * @param numPlayers the number of players
   */
  <K> void startGame(CardGameModel<K> game, int numPlayers);
}
