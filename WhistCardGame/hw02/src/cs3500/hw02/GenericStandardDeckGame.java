package cs3500.hw02;

import java.io.IOException;
import java.util.*;

/**
 * An implementation of the GenericCardGameModel. It contains a complete standard deck of playing
 * cards(i.e. 52 cards with 2,..., jack, queen, king, ace in each of clubs, diamonds, hearts,
 * spades).
 */
public class GenericStandardDeckGame implements GenericCardGameModel<StandardCard> {
  protected ArrayList<StandardCard> cards;
  protected ArrayList<Player> players;

  public GenericStandardDeckGame() {
    this.cards = new ArrayList<StandardCard>();
    this.players = new ArrayList<Player>();
  }

  @Override
  /**
   * lists the entire deck of relevant cards in the descending order.
   * Rank: A, K, Q, J, 10, ..., 2
   * Suit: ♣, ♦, ♥, ♠
   *
   * @return a list of generic type of cards
   */
  public List getDeck() {
    StandardCard.Rank ranks[] = StandardCard.Rank.values();
    StandardCard.Suit suits[] = StandardCard.Suit.values();

    List<StandardCard> tempCards = new ArrayList<StandardCard>();

    for (int i = 0; i < 13; i++) {

      for (int j = 0; j < 4; j++) {

        tempCards.add(new StandardCard(ranks[i], suits[j]));

      }
    }
    return tempCards;
  }

  /**
   * To start a play, check if the deck of cards is valid and takes in a valid
   * number of players.
   * Then, distribute these cards in the specified order among the players in
   * round-robin fashion.
   *
   * @param numPlayers the number of players
   * @param cards      a deck of generic type of cards
   */

  @Override
  public void startPlay(int numPlayers, List<StandardCard> cards) {
    if (this.validDeck(cards)) {
      if (!(numPlayers > 1)) {
        throw new IllegalArgumentException("You must have more than 1 player!");
      } else {

        for (int i = 0; i < numPlayers; i++) {
          players.add(new Player<StandardCard>());
        }

          for (int j = 0; j < 52; j++) {
            players.get(j % numPlayers).addCard(cards.get(j));
          }
        }
      }
    }

  /**
   * Check if a given deck of card is valid.
   * A deck is invalid if it does not have 52 cards, or if there are duplicate cards,
   * or if there are invalid cards (invalid suit or invalid number)
   * @param c a list of standard cards
   *
   * @return true if the given deck of cards is valid
   */
  public boolean validDeck(List<StandardCard> c) {
    if (c.size() != 52) {
      throw new IllegalArgumentException("Invalid number of cards.");
    } else {
      Set<StandardCard> cardSet = new HashSet<StandardCard>(c);

      if (cardSet.size() != 52) {
        throw new IllegalArgumentException("Duplicate cards in the deck.");
      }
      return true;
    }
  }

  @Override
  public String getGameState() {

    String result = "";

    result += "Number of players: " + Integer.toString(players.size()) + "\n";

    for (int i = 0; i < players.size(); i++) {
      result += "Player " + Integer.toString(i + 1) + ": ";

      ArrayList<StandardCard> temp = new ArrayList<StandardCard>();

      for (int j = 0; j < players.get(i).getCards().size(); j++) {
        temp.add((StandardCard) players.get(i).getCards().get(j));
      }

      Comparator<StandardCard> comp = new StandardCardComparator();
      Collections.sort(temp, comp);

      for (int k = 0; k < temp.size(); k++) {
        if (k == temp.size() - 1) {
          result += temp.get(k).toString();
        }
        else {
          result += temp.get(k).toString() + ", ";
        }
      }

      result += "\n";
    }
    return result;
  }

  /**
   * Adds the given player to the player list
   *
   * @param p given player to be added to the game
   */
  public void addPlayer(Player p) {
    players.add(p);
  }

  public ArrayList<StandardCard> getCards() {
    return this.cards;
  }

  public ArrayList<Player> getPlayers() {
    return this.players;
  }
}
