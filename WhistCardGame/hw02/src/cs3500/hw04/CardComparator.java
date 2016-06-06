package cs3500.hw04;

import java.util.Comparator;

import cs3500.hw02.StandardCard;

/**
 * A comparator for StandardCard that determines the length of cards in descending order by number
 * with aces being highest (A, K, Q, J, 10, ..., 2). Sorted order for suit of a card is defined as
 * alphabetical order of suits (i.e., clubs, diamonds, hearts, spades).
 */
public class CardComparator implements Comparator<StandardCard> {
  /**
   * determines the strength of two standard cards
   *
   * @param c1 standard card from player 1
   * @param c2 standard card from player 2
   * @return -1 if c1 is stronger than c2 and vice versa, returns 0 if they are of equal strengths
   */
  public int compare(StandardCard c1, StandardCard c2) {

    if (c1.suitValue() > c2.suitValue()) {
      return 1;
    }
    else if (c1.suitValue() < c2.suitValue()) {
      return -1;
    }
    if (c1.rankValue() > c2.rankValue()) {
      return -1;
    }
    else if (c1.rankValue() < c2.rankValue()) {
      return 1;
    } else {
      return 0;
    }
  }
}