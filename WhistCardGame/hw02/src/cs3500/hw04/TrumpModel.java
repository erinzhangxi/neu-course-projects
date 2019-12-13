package cs3500.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Player;
import cs3500.hw02.StandardCard;
import cs3500.hw03.WhistModel;
import cs3500.hw02.StandardCard.Suit;

/**
 * The player who starts a hand determines the suit of the hand. All other players must play
 * a card of the same suit if they have one. However, if a player does not have a card of the
 * same suit, he/she has two choices. The first choice is as before: play a card of some
 * other suit and essentially “forfeit” this hand. However the player can also play a card
 * of the trump suit. Once a card of the trump suit is played in a hand, the winner of the
 * hand is the player who played a card of the trump suit of the highest value.
 */
public class TrumpModel extends WhistModel{
  private StandardCard.Suit trumpSuit;

  /** zero constructor */
  public TrumpModel() {
    super();
  }

  public TrumpModel(StandardCard.Suit trump) {
    super();
    this.trumpSuit = trump;
  }

  /**
   * Set the trump suit as the given suit
   *
   * @param ts new trump suit
   */
  public void setTrumpSuit(StandardCard.Suit ts) {
    this.trumpSuit = ts;
  }

  /**
   * Gets the current current trump suit
   *
   * @return the trump suit of this game
   */
  public Suit getTrumpSuit() {
    return this.trumpSuit;
  }

  @Override
  public void startPlay(int numPlayers, List<StandardCard> cards) {
    trumpSuit = cards.get(0).getSuit();
    super.startPlay(numPlayers, cards);
  }

  @Override
  public String getGameState() {
    String result = super.getGameState();
    if (players.size() > 0) {
      result += "\nTrump suit: " + trumpSuit.toString();
      return result;
    }
   else {
      return result;
    }
  }

  @Override
  /**
   * This player must play a card that has the same suit as the current suit.
   * If this player does not have one, he can discard any card, and the card is not placed in
   * the list of cards played.
   *
   * Another choice for this player is that he can play card with the trump suit, which will
   * be added to the list of cards played.
   *
   * @param playerNo the index of current player
   * @param cardIdx the index of cards to be played
   */
  public void middlePlayer(int playerNo, int cardIdx) {
    Player player = players.get(playerNo);
    StandardCard cardPlayed = player.getPlayerCards().get(cardIdx);

    boolean hasValidSuit = false;
    for (StandardCard c : player.getPlayerCards()) {
      if (c.getSuit() == curSuit) {
        hasValidSuit = true;
      }
    }

    if (hasValidSuit && cardPlayed.getSuit() != curSuit) {
      throw new IllegalArgumentException("Player must play a card with the current suit");
    }
  }


  @Override
  /**
   * Get the index of the card with the biggest value. Once a card of the trump suit is played,
   * the values of the hand’s suit do not matter anymore for that hand. If the suit of the
   * current hand is the trump suit (i.e. the first player in the hand played a card of
   * the trump suit) then the hand proceeds normally as before: the card of this suit with
   * the highest value wins the hand.
   *
   * @param laid contains cards with either current suit or trump suit
   *
   * @return the card with the biggest value
   */

  public int getMax(List<StandardCard> laid) {
    CardComparator comp = new CardComparator();
    ArrayList<StandardCard> trumps = new ArrayList<StandardCard>();

    for (int i = 0; i < laid.size(); i++) {
      if (laid.get(i).getSuit().equals(trumpSuit)) {
        trumps.add(laid.get(i));
      }
    }

    if (trumps.size() > 0) {
      int maxTrump = 0;
      for (int i = 1; i < trumps.size(); i++) {
          if (comp.compare(trumps.get(maxTrump), trumps.get(i)) == 1) {
          maxTrump = i;
        }
      }

      return laid.indexOf(trumps.get(maxTrump));
    }
    else {
      int max = 0;
      for (int i = 1; i < laid.size(); i++) {
        if (comp.compare(laid.get(max), laid.get(i)) == 1) {
          max = i;
        }
      }
      return max;
    }
  }
}

// 6 players. player 5 & 6 won. next turn, which one plays first next turn