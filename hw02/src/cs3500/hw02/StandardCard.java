package cs3500.hw02;

/**
 * A standard card contains a value and a suit. Its value is from 1 to 13,
 * and its suits are ♣, ♦, ♥, and ♠. 'A', 'J', 'Q', 'K' are represented as 1,
 * 11, 12, 13. But it will be printed out as characters. A sorted order for
 * a standard deck of cards is defined as alphabetical order of suits (i.e., clubs,
 * diamonds, hearts, spades). Within each suit, cards should be ordered in descending
 * order by number with aces being highest (A, K, Q, J, 10, ..., 2).
 */
public class StandardCard implements Card {
  private final Rank rank;
  private final Suit suit;

  /**
   * constructor for a StandardCard
   *
   * @param r rank of a card
   * @param s suit of a card
   */
  public StandardCard(Rank r, Suit s) {
    this.rank = r;
    this.suit = s;
  }

  @Override
  public String toString() {
    return rank.name + suit.name;
  }

  /**
   * gets the Rank of this card
   *
   * @return a Rank that is one of the Rank enum
   */
  public Rank getRank() {
    return this.rank;
  }

  /**
   * Gets the index of a rank of standard card
   *
   * @return the value of the card
   */
  public int rankValue() {
    return this.getRank().value;
  }

  /**
   * Gets the name of the rank of a standard card
   *
   * @return a String representing the name of a rank
   */
  public String rankName() {
    return this.getRank().name;
  }

  /**
   * gets the Suit of this card
   *
   * @return a Suit that is one of the Suit enum
   */
  public Suit getSuit() {
    return this.suit;
  }

  /**
   * Gets the index of a suit of standard card
   *
   * @return the integer value of the card
   */
  public int suitValue() {
    return this.getSuit().value;
  }

  /**
   * Gets the name of the suit
   *
   * @return the String of the name
   */
  public String suitName() {
    return this.getSuit().name;
  }


  /**
   * to represents a collection of Rank, each of which includes a numerical value representing
   * the card and a String name
   */
  public enum Rank {
    ACE(14, "A"), KING(13, "K"), QUEEN(12, "Q"), JACK(11, "J"), TEN(10, "10"),
    NINE(9, "9"), EIGHT(8, "8"), SEVEN(7, "7"), SIX(6, "6"), FIVE(5, "5"),
    FOUR(4, "4"), THREE(3, "3"), TWO(2, "2");

    private final int value;
    private final String name;

    /**
     * @param v the numerical value of the Rank
     * @param n the name of the Rank as a String
     */
    Rank(int v, String n) {
      this.value = v;
      this.name = n;
    }
  }

  /**
   * to represents a collection of Suit, each of which includes a numerical value representing the
   * card and a String name
   */
  public enum Suit {
    CLUB(1, "♣"), DIAMOND(2, "♦"), HEART(3, "♥"), SPADE(4, "♠");

    private final int value;
    private final String name;

    /**
     * @param v the numerical value of the card
     * @param n the name of the card as a String
     */
    Suit(int v, String n) {
      this.value = v;
      this.name = n;
    }
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    } else if (!(that instanceof StandardCard)) {
      return false;
    }
    return ((StandardCard) that).rankValue() == this.rankValue() &&
            ((StandardCard) that).suitValue() == this.suitValue();
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.rankValue() + this.getSuit().hashCode());
  }
}
