package cs3500.hw02;
import java.util.*;

/**
 * A Player has a subset of cards at any time in the game. S/he gets cards and gives
 * up cards every round.
 */
public class Player<K> {
  protected List<K> cards;
  protected int handsWon;

  /**
   * zero-constructor
   */
  public Player() {
    this.cards = new ArrayList<K>();
    this.handsWon = 0;
  }

  public Player(List c, int w) {
    this.cards = c;
    this.handsWon = w;
  }

  /**
   * adds a card to a player's current cards list
   *
   * @param c a card that is added to the player's card list
   */
  public void addCard(K c) {
    cards.add(c);
  }

  /**
   * gets the deck of cards of this player
   *
   * @return a list of generic type of cards
   */
  public List<K> getCards() {
    return this.cards;
  }

  /**
   * Updates the number of hands won of this player
   *
   * @param incr the number of hands won incremented
   */
  public void updateHandsWon(int incr) {

    handsWon = handsWon + incr;
  }

  /**
   * Gets hands won
   *
   * @return the number of hands won of this player
   */
  public int getHandsWon() {
    return handsWon;
  }

}
