package cs3500.hw02;

import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import cs3500.hw03.WhistController;
import cs3500.hw03.WhistModel;

import static cs3500.hw02.StandardCard.Rank;
import static cs3500.hw02.StandardCard.Suit;
import static org.junit.Assert.assertEquals;


/**
 * Tests for {@link GenericCardGameModel}s.
 */
public class GenericCardGameModelTest {

  GenericStandardDeckGame start = new GenericStandardDeckGame();

  List<StandardCard> deck = Arrays.asList(

          new StandardCard(Rank.ACE, Suit.CLUB),
          new StandardCard(Rank.ACE, Suit.DIAMOND),
          new StandardCard(Rank.ACE, Suit.HEART),
          new StandardCard(Rank.ACE, Suit.SPADE),
          new StandardCard(Rank.KING, Suit.CLUB),
          new StandardCard(Rank.KING, Suit.DIAMOND),
          new StandardCard(Rank.KING, Suit.HEART),
          new StandardCard(Rank.KING, Suit.SPADE),
          new StandardCard(Rank.QUEEN, Suit.CLUB),
          new StandardCard(Rank.QUEEN, Suit.DIAMOND),
          new StandardCard(Rank.QUEEN, Suit.HEART),
          new StandardCard(Rank.QUEEN, Suit.SPADE),
          new StandardCard(Rank.JACK, Suit.CLUB),
          new StandardCard(Rank.JACK, Suit.DIAMOND),
          new StandardCard(Rank.JACK, Suit.HEART),
          new StandardCard(Rank.JACK, Suit.SPADE),
          new StandardCard(Rank.TEN, Suit.CLUB),
          new StandardCard(Rank.TEN, Suit.DIAMOND),
          new StandardCard(Rank.TEN, Suit.HEART),
          new StandardCard(Rank.TEN, Suit.SPADE),
          new StandardCard(Rank.NINE, Suit.CLUB),
          new StandardCard(Rank.NINE, Suit.DIAMOND),
          new StandardCard(Rank.NINE, Suit.HEART),
          new StandardCard(Rank.NINE, Suit.SPADE),
          new StandardCard(Rank.EIGHT, Suit.CLUB),
          new StandardCard(Rank.EIGHT, Suit.DIAMOND),
          new StandardCard(Rank.EIGHT, Suit.HEART),
          new StandardCard(Rank.EIGHT, Suit.SPADE),
          new StandardCard(Rank.SEVEN, Suit.CLUB),
          new StandardCard(Rank.SEVEN, Suit.DIAMOND),
          new StandardCard(Rank.SEVEN, Suit.HEART),
          new StandardCard(Rank.SEVEN, Suit.SPADE),
          new StandardCard(Rank.SIX, Suit.CLUB),
          new StandardCard(Rank.SIX, Suit.DIAMOND),
          new StandardCard(Rank.SIX, Suit.HEART),
          new StandardCard(Rank.SIX, Suit.SPADE),
          new StandardCard(Rank.FIVE, Suit.CLUB),
          new StandardCard(Rank.FIVE, Suit.DIAMOND),
          new StandardCard(Rank.FIVE, Suit.HEART),
          new StandardCard(Rank.FIVE, Suit.SPADE),
          new StandardCard(Rank.FOUR, Suit.CLUB),
          new StandardCard(Rank.FOUR, Suit.DIAMOND),
          new StandardCard(Rank.FOUR, Suit.HEART),
          new StandardCard(Rank.FOUR, Suit.SPADE),
          new StandardCard(Rank.THREE, Suit.CLUB),
          new StandardCard(Rank.THREE, Suit.DIAMOND),
          new StandardCard(Rank.THREE, Suit.HEART),
          new StandardCard(Rank.THREE, Suit.SPADE),
          new StandardCard(Rank.TWO, Suit.CLUB),
          new StandardCard(Rank.TWO, Suit.DIAMOND),
          new StandardCard(Rank.TWO, Suit.HEART),
          new StandardCard(Rank.TWO, Suit.SPADE));

  List<StandardCard> unsortedDeck = Arrays.asList(
          new StandardCard(Rank.ACE, Suit.CLUB),
          new StandardCard(Rank.ACE, Suit.DIAMOND),
          new StandardCard(Rank.ACE, Suit.HEART),
          new StandardCard(Rank.ACE, Suit.SPADE),
          new StandardCard(Rank.TEN, Suit.CLUB),
          new StandardCard(Rank.TEN, Suit.DIAMOND),
          new StandardCard(Rank.FIVE, Suit.CLUB),
          new StandardCard(Rank.FIVE, Suit.DIAMOND),
          new StandardCard(Rank.FIVE, Suit.HEART),
          new StandardCard(Rank.TWO, Suit.CLUB),
          new StandardCard(Rank.TWO, Suit.DIAMOND),
          new StandardCard(Rank.TWO, Suit.HEART),
          new StandardCard(Rank.TEN, Suit.HEART),
          new StandardCard(Rank.TEN, Suit.SPADE),
          new StandardCard(Rank.NINE, Suit.CLUB),
          new StandardCard(Rank.NINE, Suit.DIAMOND),
          new StandardCard(Rank.NINE, Suit.HEART),
          new StandardCard(Rank.NINE, Suit.SPADE),
          new StandardCard(Rank.FOUR, Suit.HEART),
          new StandardCard(Rank.EIGHT, Suit.CLUB),
          new StandardCard(Rank.EIGHT, Suit.DIAMOND),
          new StandardCard(Rank.THREE, Suit.HEART),
          new StandardCard(Rank.THREE, Suit.SPADE),
          new StandardCard(Rank.SEVEN, Suit.HEART),
          new StandardCard(Rank.QUEEN, Suit.HEART),
          new StandardCard(Rank.EIGHT, Suit.HEART),
          new StandardCard(Rank.EIGHT, Suit.SPADE),
          new StandardCard(Rank.SEVEN, Suit.CLUB),
          new StandardCard(Rank.SEVEN, Suit.DIAMOND),
          new StandardCard(Rank.FOUR, Suit.SPADE),
          new StandardCard(Rank.THREE, Suit.CLUB),
          new StandardCard(Rank.THREE, Suit.DIAMOND),
          new StandardCard(Rank.QUEEN, Suit.SPADE),
          new StandardCard(Rank.JACK, Suit.CLUB),
          new StandardCard(Rank.FIVE, Suit.SPADE),
          new StandardCard(Rank.FOUR, Suit.CLUB),
          new StandardCard(Rank.FOUR, Suit.DIAMOND),
          new StandardCard(Rank.JACK, Suit.DIAMOND),
          new StandardCard(Rank.JACK, Suit.HEART),
          new StandardCard(Rank.SEVEN, Suit.SPADE),
          new StandardCard(Rank.SIX, Suit.CLUB),
          new StandardCard(Rank.KING, Suit.CLUB),
          new StandardCard(Rank.KING, Suit.DIAMOND),
          new StandardCard(Rank.KING, Suit.HEART),
          new StandardCard(Rank.KING, Suit.SPADE),
          new StandardCard(Rank.QUEEN, Suit.CLUB),
          new StandardCard(Rank.QUEEN, Suit.DIAMOND),
          new StandardCard(Rank.JACK, Suit.SPADE),
          new StandardCard(Rank.SIX, Suit.DIAMOND),
          new StandardCard(Rank.SIX, Suit.HEART),
          new StandardCard(Rank.SIX, Suit.SPADE),
          new StandardCard(Rank.TWO, Suit.SPADE));

  WhistModel wm0 = new WhistModel();

  StandardCard c1 = new StandardCard(Rank.ACE, Suit.CLUB);
  StandardCard c2 = new StandardCard(Rank.TEN, Suit.DIAMOND);
  StandardCard c3 = new StandardCard(Rank.TWO, Suit.DIAMOND);
  StandardCard c4 = new StandardCard(Rank.ACE, Suit.DIAMOND);
  StandardCard c5 = new StandardCard(Rank.FIVE, Suit.CLUB);
  StandardCard c6 = new StandardCard(Rank.TWO, Suit.HEART);
  StandardCard c7 = new StandardCard(Rank.NINE, Suit.HEART);
  StandardCard c8 = new StandardCard(Rank.THREE, Suit.HEART);
  StandardCard c9 = new StandardCard(Rank.EIGHT, Suit.SPADE);



  @Test
  public void StandardCardExample1() {
    assertEquals("A♠", new StandardCard(StandardCard.Rank.ACE,
            StandardCard.Suit.SPADE).toString());
  }

  @Test
  public void StandardCardExample2() {
    assertEquals("K♥", new StandardCard(StandardCard.Rank.KING,
            StandardCard.Suit.HEART).toString());
  }

  @Test
  public void StandardCardExample3() {
    assertEquals("J♣", new StandardCard(StandardCard.Rank.JACK,
            StandardCard.Suit.CLUB).toString());
  }

  @Test
  public void StandardCardExample4() {
    assertEquals("10♣", new StandardCard(StandardCard.Rank.TEN,
            StandardCard.Suit.CLUB).toString());
  }

  @Test
  public void StandardCardExample5() {
    assertEquals("2♦", new StandardCard(StandardCard.Rank.TWO,
            StandardCard.Suit.DIAMOND).toString());
  }

  @Test
  public void testGetDeck() {
    assertEquals(deck, start.getDeck());
  }

  @Test
  public void testAddCardAndGetCard() {
    Player<StandardCard> player1 = new Player<StandardCard>();
    Player<StandardCard> player2 = new Player<StandardCard>();
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.CLUB);
    StandardCard c2 = new StandardCard(Rank.TWO, Suit.HEART);
    StandardCard c3 = new StandardCard(Rank.KING, Suit.HEART);
    StandardCard c4 = new StandardCard(Rank.KING, Suit.SPADE);
    StandardCard c5 = new StandardCard(Rank.FOUR, Suit.SPADE);

    player1.addCard(c1);
    player1.addCard(c2);
    player1.addCard(c3);
    player1.addCard(c4);
    player1.addCard(c5);

    player2.addCard(c3);
    player2.addCard(c5);
    player2.addCard(c1);

    assertEquals(c1, player1.getCards().get(0));
    assertEquals(c2, player1.getCards().get(1));
    assertEquals(c3, player1.getCards().get(2));
    assertEquals(c4, player1.getCards().get(3));
    assertEquals(c5, player1.getCards().get(4));
    assertEquals(c3, player2.getCards().get(0));
    assertEquals(c5, player2.getCards().get(1));
    assertEquals(c1, player2.getCards().get(2));
  }

  @Test
  public void testToString() {
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.CLUB);
    StandardCard c2 = new StandardCard(Rank.TWO, Suit.HEART);
    StandardCard c3 = new StandardCard(Rank.KING, Suit.HEART);
    StandardCard c4 = new StandardCard(Rank.KING, Suit.DIAMOND);
    StandardCard c5 = new StandardCard(Rank.FOUR, Suit.SPADE);
    assertEquals("A♣", c1.toString());
    assertEquals("2♥", c2.toString());
    assertEquals("K♥", c3.toString());
    assertEquals("K♦", c4.toString());
    assertEquals("4♠", c5.toString());
  }

  @Test
  public void testGetRank() {
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.CLUB);
    StandardCard c2 = new StandardCard(Rank.TWO, Suit.HEART);
    StandardCard c3 = new StandardCard(Rank.KING, Suit.HEART);
    StandardCard c4 = new StandardCard(Rank.JACK, Suit.DIAMOND);
    StandardCard c5 = new StandardCard(Rank.TEN, Suit.SPADE);

    assertEquals(Rank.ACE, c1.getRank());
    assertEquals(Rank.TWO, c2.getRank());
    assertEquals(Rank.KING, c3.getRank());
    assertEquals(Rank.JACK, c4.getRank());
    assertEquals(Rank.TEN, c5.getRank());
  }

  @Test
  public void testRankName() {
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.CLUB);
    StandardCard c2 = new StandardCard(Rank.TWO, Suit.HEART);
    StandardCard c3 = new StandardCard(Rank.KING, Suit.HEART);
    StandardCard c4 = new StandardCard(Rank.JACK, Suit.DIAMOND);
    StandardCard c5 = new StandardCard(Rank.TEN, Suit.SPADE);

    assertEquals("A", c1.rankName());
    assertEquals("2", c2.rankName());
    assertEquals("K", c3.rankName());
    assertEquals("J", c4.rankName());
    assertEquals("10", c5.rankName());
  }

  @Test
  public void testRankValue() {
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.CLUB);
    StandardCard c2 = new StandardCard(Rank.TWO, Suit.HEART);
    StandardCard c3 = new StandardCard(Rank.KING, Suit.HEART);
    StandardCard c4 = new StandardCard(Rank.JACK, Suit.DIAMOND);
    StandardCard c5 = new StandardCard(Rank.TEN, Suit.SPADE);

    assertEquals(14, c1.rankValue());
    assertEquals(2, c2.rankValue());
    assertEquals(13, c3.rankValue());
    assertEquals(11, c4.rankValue());
    assertEquals(10, c5.rankValue());
  }

  @Test
  public void testGetSuit() {
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.CLUB);
    StandardCard c2 = new StandardCard(Rank.TWO, Suit.HEART);
    StandardCard c3 = new StandardCard(Rank.KING, Suit.HEART);
    StandardCard c4 = new StandardCard(Rank.JACK, Suit.DIAMOND);
    StandardCard c5 = new StandardCard(Rank.TEN, Suit.SPADE);

    assertEquals(Suit.CLUB, c1.getSuit());
    assertEquals(Suit.HEART, c2.getSuit());
    assertEquals(Suit.HEART, c3.getSuit());
    assertEquals(Suit.DIAMOND, c4.getSuit());
    assertEquals(Suit.SPADE, c5.getSuit());
  }
  @Test
  public void testSuitName() {
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.CLUB);
    StandardCard c2 = new StandardCard(Rank.TWO, Suit.HEART);
    StandardCard c3 = new StandardCard(Rank.KING, Suit.HEART);
    StandardCard c4 = new StandardCard(Rank.JACK, Suit.DIAMOND);
    StandardCard c5 = new StandardCard(Rank.TEN, Suit.SPADE);

    assertEquals("♣", c1.suitName());
    assertEquals("♥", c2.suitName());
    assertEquals("♥", c3.suitName());
    assertEquals("♦", c4.suitName());
    assertEquals("♠", c5.suitName());
  }

  @Test
  public void testSuitValue() {
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.CLUB);
    StandardCard c2 = new StandardCard(Rank.TWO, Suit.HEART);
    StandardCard c3 = new StandardCard(Rank.KING, Suit.HEART);
    StandardCard c4 = new StandardCard(Rank.JACK, Suit.DIAMOND);
    StandardCard c5 = new StandardCard(Rank.TEN, Suit.SPADE);

    assertEquals(1, c1.suitValue());
    assertEquals(3, c2.suitValue());
    assertEquals(3, c3.suitValue());
    assertEquals(2, c4.suitValue());
    assertEquals(4, c5.suitValue());
  }


  @Test
  public void testValidDeck1() {
    assertEquals(true, start.validDeck(start.getDeck()));
  }

  /**
   * given less than 52 cards
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidDeck2() {
    List<StandardCard> invalidDeck = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.ACE, Suit.DIAMOND),
            new StandardCard(Rank.ACE, Suit.HEART),
            new StandardCard(Rank.ACE, Suit.SPADE),
            new StandardCard(Rank.KING, Suit.CLUB),
            new StandardCard(Rank.KING, Suit.DIAMOND),
            new StandardCard(Rank.KING, Suit.HEART),
            new StandardCard(Rank.KING, Suit.SPADE),
            new StandardCard(Rank.QUEEN, Suit.CLUB),
            new StandardCard(Rank.QUEEN, Suit.DIAMOND),
            new StandardCard(Rank.QUEEN, Suit.HEART),
            new StandardCard(Rank.QUEEN, Suit.SPADE),
            new StandardCard(Rank.JACK, Suit.CLUB),
            new StandardCard(Rank.JACK, Suit.DIAMOND),
            new StandardCard(Rank.JACK, Suit.HEART),
            new StandardCard(Rank.JACK, Suit.SPADE),
            new StandardCard(Rank.TEN, Suit.CLUB),
            new StandardCard(Rank.TEN, Suit.DIAMOND),
            new StandardCard(Rank.TEN, Suit.HEART),
            new StandardCard(Rank.TEN, Suit.SPADE),
            new StandardCard(Rank.NINE, Suit.CLUB));
    start.validDeck(invalidDeck);
  }

  /**
   * given duplicate card
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidDeck3() {
    List<StandardCard> invalidDeck = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.ACE, Suit.HEART),
            new StandardCard(Rank.ACE, Suit.SPADE),
            new StandardCard(Rank.KING, Suit.CLUB),
            new StandardCard(Rank.KING, Suit.DIAMOND),
            new StandardCard(Rank.KING, Suit.HEART),
            new StandardCard(Rank.KING, Suit.SPADE),
            new StandardCard(Rank.QUEEN, Suit.CLUB),
            new StandardCard(Rank.QUEEN, Suit.DIAMOND),
            new StandardCard(Rank.QUEEN, Suit.HEART),
            new StandardCard(Rank.QUEEN, Suit.SPADE),
            new StandardCard(Rank.JACK, Suit.CLUB),
            new StandardCard(Rank.JACK, Suit.DIAMOND),
            new StandardCard(Rank.JACK, Suit.HEART),
            new StandardCard(Rank.JACK, Suit.SPADE),
            new StandardCard(Rank.TEN, Suit.CLUB),
            new StandardCard(Rank.TEN, Suit.DIAMOND),
            new StandardCard(Rank.TEN, Suit.HEART),
            new StandardCard(Rank.TEN, Suit.SPADE),
            new StandardCard(Rank.NINE, Suit.CLUB),
            new StandardCard(Rank.NINE, Suit.DIAMOND),
            new StandardCard(Rank.NINE, Suit.HEART),
            new StandardCard(Rank.NINE, Suit.SPADE),
            new StandardCard(Rank.EIGHT, Suit.CLUB),
            new StandardCard(Rank.EIGHT, Suit.DIAMOND),
            new StandardCard(Rank.EIGHT, Suit.HEART),
            new StandardCard(Rank.EIGHT, Suit.SPADE),
            new StandardCard(Rank.SEVEN, Suit.CLUB),
            new StandardCard(Rank.SEVEN, Suit.DIAMOND),
            new StandardCard(Rank.SEVEN, Suit.HEART),
            new StandardCard(Rank.SEVEN, Suit.SPADE),
            new StandardCard(Rank.SIX, Suit.CLUB),
            new StandardCard(Rank.SIX, Suit.DIAMOND),
            new StandardCard(Rank.SIX, Suit.HEART),
            new StandardCard(Rank.SIX, Suit.SPADE),
            new StandardCard(Rank.FIVE, Suit.CLUB),
            new StandardCard(Rank.FIVE, Suit.DIAMOND),
            new StandardCard(Rank.FIVE, Suit.HEART),
            new StandardCard(Rank.FIVE, Suit.SPADE),
            new StandardCard(Rank.FOUR, Suit.CLUB),
            new StandardCard(Rank.FOUR, Suit.DIAMOND),
            new StandardCard(Rank.FOUR, Suit.HEART),
            new StandardCard(Rank.FOUR, Suit.SPADE),
            new StandardCard(Rank.THREE, Suit.CLUB),
            new StandardCard(Rank.THREE, Suit.DIAMOND),
            new StandardCard(Rank.THREE, Suit.HEART),
            new StandardCard(Rank.THREE, Suit.SPADE),
            new StandardCard(Rank.TWO, Suit.CLUB),
            new StandardCard(Rank.TWO, Suit.DIAMOND),
            new StandardCard(Rank.TWO, Suit.HEART),
            new StandardCard(Rank.TWO, Suit.SPADE));
    start.validDeck(invalidDeck);
  }

  /**
   * when there is less than 52 cards in the deck, throws an IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartPlay1() {
    List<StandardCard> deck = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.ACE, Suit.DIAMOND),
            new StandardCard(Rank.ACE, Suit.HEART),
            new StandardCard(Rank.ACE, Suit.SPADE),
            new StandardCard(Rank.TWO, Suit.CLUB),
            new StandardCard(Rank.TWO, Suit.DIAMOND),
            new StandardCard(Rank.TWO, Suit.HEART),
            new StandardCard(Rank.TWO, Suit.SPADE),
            new StandardCard(Rank.THREE, Suit.CLUB),
            new StandardCard(Rank.THREE, Suit.DIAMOND),
            new StandardCard(Rank.THREE, Suit.HEART),
            new StandardCard(Rank.THREE, Suit.SPADE),
            new StandardCard(Rank.FOUR, Suit.CLUB),
            new StandardCard(Rank.FOUR, Suit.DIAMOND),
            new StandardCard(Rank.FOUR, Suit.HEART),
            new StandardCard(Rank.FOUR, Suit.SPADE),
            new StandardCard(Rank.FIVE, Suit.CLUB));
    start.startPlay(3, deck);
  }

  /**
   * when there are duplicate cards, throws an illegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartPlay2() {
    List<StandardCard> deck = Arrays.asList(

            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.TWO, Suit.SPADE),
            new StandardCard(Rank.THREE, Suit.CLUB),
            new StandardCard(Rank.THREE, Suit.DIAMOND),
            new StandardCard(Rank.THREE, Suit.HEART),
            new StandardCard(Rank.THREE, Suit.SPADE),
            new StandardCard(Rank.FOUR, Suit.CLUB),
            new StandardCard(Rank.FOUR, Suit.DIAMOND),
            new StandardCard(Rank.FOUR, Suit.HEART),
            new StandardCard(Rank.FOUR, Suit.SPADE),
            new StandardCard(Rank.FIVE, Suit.CLUB),
            new StandardCard(Rank.FIVE, Suit.DIAMOND),
            new StandardCard(Rank.FIVE, Suit.HEART),
            new StandardCard(Rank.FIVE, Suit.SPADE),
            new StandardCard(Rank.SIX, Suit.CLUB),
            new StandardCard(Rank.SIX, Suit.DIAMOND),
            new StandardCard(Rank.SIX, Suit.HEART),
            new StandardCard(Rank.SIX, Suit.SPADE),
            new StandardCard(Rank.SEVEN, Suit.CLUB),
            new StandardCard(Rank.SEVEN, Suit.DIAMOND),
            new StandardCard(Rank.SEVEN, Suit.HEART),
            new StandardCard(Rank.SEVEN, Suit.SPADE),
            new StandardCard(Rank.EIGHT, Suit.CLUB),
            new StandardCard(Rank.EIGHT, Suit.DIAMOND),
            new StandardCard(Rank.EIGHT, Suit.HEART),
            new StandardCard(Rank.EIGHT, Suit.SPADE),
            new StandardCard(Rank.NINE, Suit.CLUB),
            new StandardCard(Rank.NINE, Suit.DIAMOND),
            new StandardCard(Rank.NINE, Suit.HEART),
            new StandardCard(Rank.NINE, Suit.SPADE),
            new StandardCard(Rank.TEN, Suit.CLUB),
            new StandardCard(Rank.TEN, Suit.DIAMOND),
            new StandardCard(Rank.TEN, Suit.HEART),
            new StandardCard(Rank.TEN, Suit.SPADE),
            new StandardCard(Rank.JACK, Suit.CLUB),
            new StandardCard(Rank.JACK, Suit.DIAMOND),
            new StandardCard(Rank.JACK, Suit.HEART),
            new StandardCard(Rank.JACK, Suit.SPADE),
            new StandardCard(Rank.QUEEN, Suit.CLUB),
            new StandardCard(Rank.QUEEN, Suit.DIAMOND),
            new StandardCard(Rank.QUEEN, Suit.HEART),
            new StandardCard(Rank.QUEEN, Suit.SPADE),
            new StandardCard(Rank.KING, Suit.CLUB),
            new StandardCard(Rank.KING, Suit.DIAMOND),
            new StandardCard(Rank.KING, Suit.HEART),
            new StandardCard(Rank.KING, Suit.SPADE));
    start.startPlay(4, deck);
  }

  /**
   * when there is no more than 1 player, throws an IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartPlay3() {

    start.startPlay(1, deck);
  }

  /**
   * Given a sorted deck of cards when the deck and the number of players are valid, check if each
   * player has the cards s/he should have.
   */
  @Test
  public void testStartPlayAndGameState4() {

    start.startPlay(3, deck);

    List<StandardCard> player1 = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.ACE, Suit.SPADE),
            new StandardCard(Rank.KING, Suit.HEART),
            new StandardCard(Rank.QUEEN, Suit.DIAMOND),
            new StandardCard(Rank.JACK, Suit.CLUB),
            new StandardCard(Rank.JACK, Suit.SPADE),
            new StandardCard(Rank.TEN, Suit.HEART),
            new StandardCard(Rank.NINE, Suit.DIAMOND),
            new StandardCard(Rank.EIGHT, Suit.CLUB),
            new StandardCard(Rank.EIGHT, Suit.SPADE),
            new StandardCard(Rank.SEVEN, Suit.HEART),
            new StandardCard(Rank.SIX, Suit.DIAMOND),
            new StandardCard(Rank.FIVE, Suit.CLUB),
            new StandardCard(Rank.FIVE, Suit.SPADE),
            new StandardCard(Rank.FOUR, Suit.HEART),
            new StandardCard(Rank.THREE, Suit.DIAMOND),
            new StandardCard(Rank.TWO, Suit.CLUB),
            new StandardCard(Rank.TWO, Suit.SPADE));

    List<StandardCard> player2 = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.DIAMOND),
            new StandardCard(Rank.KING, Suit.CLUB),
            new StandardCard(Rank.KING, Suit.SPADE),
            new StandardCard(Rank.QUEEN, Suit.HEART),
            new StandardCard(Rank.JACK, Suit.DIAMOND),
            new StandardCard(Rank.TEN, Suit.CLUB),
            new StandardCard(Rank.TEN, Suit.SPADE),
            new StandardCard(Rank.NINE, Suit.HEART),
            new StandardCard(Rank.EIGHT, Suit.DIAMOND),
            new StandardCard(Rank.SEVEN, Suit.CLUB),
            new StandardCard(Rank.SEVEN, Suit.SPADE),
            new StandardCard(Rank.SIX, Suit.HEART),
            new StandardCard(Rank.FIVE, Suit.DIAMOND),
            new StandardCard(Rank.FOUR, Suit.CLUB),
            new StandardCard(Rank.FOUR, Suit.SPADE),
            new StandardCard(Rank.THREE, Suit.HEART),
            new StandardCard(Rank.TWO, Suit.DIAMOND));

    List<StandardCard> player3 = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.HEART),
            new StandardCard(Rank.KING, Suit.DIAMOND),
            new StandardCard(Rank.QUEEN, Suit.CLUB),
            new StandardCard(Rank.QUEEN, Suit.SPADE),
            new StandardCard(Rank.JACK, Suit.HEART),
            new StandardCard(Rank.TEN, Suit.DIAMOND),
            new StandardCard(Rank.NINE, Suit.CLUB),
            new StandardCard(Rank.NINE, Suit.SPADE),
            new StandardCard(Rank.EIGHT, Suit.HEART),
            new StandardCard(Rank.SEVEN, Suit.DIAMOND),
            new StandardCard(Rank.SIX, Suit.CLUB),
            new StandardCard(Rank.SIX, Suit.SPADE),
            new StandardCard(Rank.FIVE, Suit.HEART),
            new StandardCard(Rank.FOUR, Suit.DIAMOND),
            new StandardCard(Rank.THREE, Suit.CLUB),
            new StandardCard(Rank.THREE, Suit.SPADE),
            new StandardCard(Rank.TWO, Suit.HEART));

    assertEquals(start.players.get(0).getCards(), player1);
    assertEquals(start.players.get(1).getCards(), player2);
    assertEquals(start.players.get(2).getCards(), player3);

    String gameState1 = "Number of players: 3\n" +
            "Player 1: A♣, A♠, K♥, Q♦, J♣, J♠, 10♥, 9♦, 8♣, 8♠, 7♥," +
            " 6♦, 5♣, 5♠, 4♥, 3♦, 2♣, 2♠\n" +
            "Player 2: A♦, K♣, K♠, Q♥, J♦, 10♣, 10♠, 9♥, 8♦, 7♣, 7♠," +
            " 6♥, 5♦, 4♣, 4♠, 3♥, 2♦\n" +
            "Player 3: A♥, K♦, Q♣, Q♠, J♥, 10♦, 9♣, 9♠, 8♥, 7♦, 6♣, " +
            "6♠, 5♥, 4♦, 3♣, 3♠, 2♥\n";
    assertEquals(start.getGameState(), gameState1);
  }

  /**
   * given 0 players
   */
  @Test (expected = IllegalArgumentException.class)
  public void testStartPlayAndGameState5() {
    start.startPlay(0, deck);

  }

  /**
   * given an unsorted deck of card
   */
  @Test
  public void testStartPlayAndGameState6() {
    start.startPlay(5, unsortedDeck);

    List<StandardCard> player1 = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.TEN, Suit.DIAMOND),
            new StandardCard(Rank.TWO, Suit.DIAMOND),
            new StandardCard(Rank.NINE, Suit.DIAMOND),
            new StandardCard(Rank.EIGHT, Suit.DIAMOND),
            new StandardCard(Rank.EIGHT, Suit.HEART),
            new StandardCard(Rank.THREE, Suit.CLUB),
            new StandardCard(Rank.FOUR, Suit.CLUB),
            new StandardCard(Rank.SIX, Suit.CLUB),
            new StandardCard(Rank.QUEEN, Suit.CLUB),
            new StandardCard(Rank.SIX, Suit.SPADE)
    );
    List<StandardCard> player2 = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.DIAMOND),
            new StandardCard(Rank.FIVE, Suit.CLUB),
            new StandardCard(Rank.TWO, Suit.HEART),
            new StandardCard(Rank.NINE, Suit.HEART),
            new StandardCard(Rank.THREE, Suit.HEART),
            new StandardCard(Rank.EIGHT, Suit.SPADE),
            new StandardCard(Rank.THREE, Suit.DIAMOND),
            new StandardCard(Rank.FOUR, Suit.DIAMOND),
            new StandardCard(Rank.KING, Suit.CLUB),
            new StandardCard(Rank.QUEEN, Suit.DIAMOND),
            new StandardCard(Rank.TWO, Suit.SPADE)
    );

    List<StandardCard> player3 = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.HEART),
            new StandardCard(Rank.FIVE, Suit.DIAMOND),
            new StandardCard(Rank.TEN, Suit.HEART),
            new StandardCard(Rank.NINE, Suit.SPADE),
            new StandardCard(Rank.THREE, Suit.SPADE),
            new StandardCard(Rank.SEVEN, Suit.CLUB),
            new StandardCard(Rank.QUEEN, Suit.SPADE),
            new StandardCard(Rank.JACK, Suit.DIAMOND),
            new StandardCard(Rank.KING, Suit.DIAMOND),
            new StandardCard(Rank.JACK, Suit.SPADE)
    );
    List<StandardCard> player4 = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.SPADE),
            new StandardCard(Rank.FIVE, Suit.HEART),
            new StandardCard(Rank.TEN, Suit.SPADE),
            new StandardCard(Rank.FOUR, Suit.HEART),
            new StandardCard(Rank.SEVEN, Suit.HEART),
            new StandardCard(Rank.SEVEN, Suit.DIAMOND),
            new StandardCard(Rank.JACK, Suit.CLUB),
            new StandardCard(Rank.JACK, Suit.HEART),
            new StandardCard(Rank.KING, Suit.HEART),
            new StandardCard(Rank.SIX, Suit.DIAMOND)
    );

    List<StandardCard> player5 = Arrays.asList(
            new StandardCard(Rank.TEN, Suit.CLUB),
            new StandardCard(Rank.TWO, Suit.CLUB),
            new StandardCard(Rank.NINE, Suit.CLUB),
            new StandardCard(Rank.EIGHT, Suit.CLUB),
            new StandardCard(Rank.QUEEN, Suit.HEART),
            new StandardCard(Rank.FOUR, Suit.SPADE),
            new StandardCard(Rank.FIVE, Suit.SPADE),
            new StandardCard(Rank.SEVEN, Suit.SPADE),
            new StandardCard(Rank.KING, Suit.SPADE),
            new StandardCard(Rank.SIX, Suit.HEART)
    );

    assertEquals(start.players.get(0).getCards(), player1);
    assertEquals(start.players.get(1).getCards(), player2);
    assertEquals(start.players.get(2).getCards(), player3);
    assertEquals(start.players.get(3).getCards(), player4);
    assertEquals(start.players.get(4).getCards(), player5);

    String gameState3 = "Number of players: 5\n" +
            "Player 1: A♣, Q♣, 10♦, 9♦, 8♦, 8♥, 6♣, 6♠, 4♣, 3♣, 2♦\n" +
            "Player 2: A♦, K♣, Q♦, 9♥, 8♠, 5♣, 4♦, 3♦, 3♥, 2♥, 2♠\n" +
            "Player 3: A♥, K♦, Q♠, J♦, J♠, 10♥, 9♠, 7♣, 5♦, 3♠\n" +
            "Player 4: A♠, K♥, J♣, J♥, 10♠, 7♦, 7♥, 6♦, 5♥, 4♥\n" +
            "Player 5: K♠, Q♥, 10♣, 9♣, 8♣, 7♠, 6♥, 5♠, 4♠, 2♣\n";

    assertEquals(gameState3, start.getGameState());
  }

  /**
   * when there are more than 52 players
   */
  @Test
  public void testStartPlayAndGameState7() {
    start.startPlay(54, deck);

    String gameState4 = "Number of players: 54\n" +
            "Player 1: A♣\n" +
            "Player 2: A♦\n" +
            "Player 3: A♥\n" +
            "Player 4: A♠\n" +
            "Player 5: K♣\n" +
            "Player 6: K♦\n" +
            "Player 7: K♥\n" +
            "Player 8: K♠\n" +
            "Player 9: Q♣\n" +
            "Player 10: Q♦\n" +
            "Player 11: Q♥\n" +
            "Player 12: Q♠\n" +
            "Player 13: J♣\n" +
            "Player 14: J♦\n" +
            "Player 15: J♥\n" +
            "Player 16: J♠\n" +
            "Player 17: 10♣\n" +
            "Player 18: 10♦\n" +
            "Player 19: 10♥\n" +
            "Player 20: 10♠\n" +
            "Player 21: 9♣\n" +
            "Player 22: 9♦\n" +
            "Player 23: 9♥\n" +
            "Player 24: 9♠\n" +
            "Player 25: 8♣\n" +
            "Player 26: 8♦\n" +
            "Player 27: 8♥\n" +
            "Player 28: 8♠\n" +
            "Player 29: 7♣\n" +
            "Player 30: 7♦\n" +
            "Player 31: 7♥\n" +
            "Player 32: 7♠\n" +
            "Player 33: 6♣\n" +
            "Player 34: 6♦\n" +
            "Player 35: 6♥\n" +
            "Player 36: 6♠\n" +
            "Player 37: 5♣\n" +
            "Player 38: 5♦\n" +
            "Player 39: 5♥\n" +
            "Player 40: 5♠\n" +
            "Player 41: 4♣\n" +
            "Player 42: 4♦\n" +
            "Player 43: 4♥\n" +
            "Player 44: 4♠\n" +
            "Player 45: 3♣\n" +
            "Player 46: 3♦\n" +
            "Player 47: 3♥\n" +
            "Player 48: 3♠\n" +
            "Player 49: 2♣\n" +
            "Player 50: 2♦\n" +
            "Player 51: 2♥\n" +
            "Player 52: 2♠\n" +
            "Player 53: \n" +
            "Player 54: \n";

    assertEquals(gameState4, start.getGameState());
  }

  @Test
  public void testStandardCardComparator() {
    Comparator<StandardCard> comp = new StandardCardComparator();
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.CLUB);
    StandardCard c2 = new StandardCard(Rank.ACE, Suit.CLUB);
    StandardCard c3 = new StandardCard(Rank.TWO, Suit.HEART);
    StandardCard c4 = new StandardCard(Rank.THREE, Suit.SPADE);
    StandardCard c5 = new StandardCard(Rank.KING, Suit.HEART);
    StandardCard c6 = new StandardCard(Rank.KING, Suit.SPADE);
    StandardCard c7 = new StandardCard(Rank.FOUR, Suit.SPADE);
    StandardCard c8 = c1;

    assertEquals(0, comp.compare(c1, c2));
    assertEquals(0, comp.compare(c1, c8));
    assertEquals(-1, comp.compare(c1, c3));
    assertEquals(1, comp.compare(c3, c1));
    assertEquals(0, comp.compare(c7, c7));
    assertEquals(-1, comp.compare(c5, c6));
    assertEquals(1, comp.compare(c6, c5));
    assertEquals(1, comp.compare(c4, c7));
  }

  @Test
  public void testCardEqual() {
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.CLUB);
    StandardCard c2 = new StandardCard(Rank.ACE, Suit.CLUB);
    StandardCard c3 = new StandardCard(Rank.TWO, Suit.HEART);
    StandardCard c8 = c1;

    assertEquals(true, c1.equals(c2));
    assertEquals(true, c1.equals(c8));
    assertEquals(true, c2.equals(c8));
    assertEquals(false, c1.equals(c3));
  }










  /***************************************  HW03  *************************************/


  @Test
  public void testUpdateHandsWon() {
    ArrayList<StandardCard> cards1 = new ArrayList<StandardCard>();
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.DIAMOND);
    StandardCard c2 = new StandardCard(Rank.FIVE, Suit.DIAMOND);
    StandardCard c3 = new StandardCard(Rank.FOUR, Suit.DIAMOND);
    StandardCard c4 = new StandardCard(Rank.TWO, Suit.HEART);
    cards1.add(c1);
    cards1.add(c2);
    cards1.add(c3);
    cards1.add(c4);

    Player p1 = new Player<StandardCard>(cards1, 0);
    assertEquals(0, p1.getHandsWon());
    p1.updateHandsWon(1);

    assertEquals(1, p1.getHandsWon());

  }

  @Test
  public void testGetMax() {
    ArrayList<StandardCard> cards = new ArrayList<StandardCard>();
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.DIAMOND);
    StandardCard c2 = new StandardCard(Rank.FIVE, Suit.DIAMOND);
    StandardCard c3 = new StandardCard(Rank.FOUR, Suit.DIAMOND);
    StandardCard c4 = new StandardCard(Rank.TWO, Suit.HEART);
    cards.add(c1);
    cards.add(c2);
    cards.add(c3);
    cards.add(c4);
    assertEquals(0, wm0.getMax(cards));
  }
  // when the game first started with unsorted deck
  @Test
  public void testGetGameState1() {

//    List<StandardCard> cards1 = Arrays.asList(
//            new StandardCard(Rank.ACE, Suit.CLUB),
//            new StandardCard(Rank.TEN, Suit.DIAMOND),
//            new StandardCard(Rank.TWO, Suit.DIAMOND),
//            new StandardCard(Rank.NINE, Suit.DIAMOND),
//            new StandardCard(Rank.EIGHT, Suit.DIAMOND),
//            new StandardCard(Rank.EIGHT, Suit.HEART),
//            new StandardCard(Rank.THREE, Suit.CLUB),
//            new StandardCard(Rank.FOUR, Suit.CLUB),
//            new StandardCard(Rank.SIX, Suit.CLUB),
//            new StandardCard(Rank.QUEEN, Suit.CLUB),
//            new StandardCard(Rank.SIX, Suit.SPADE)
//    );
//    List<StandardCard> cards2 = Arrays.asList(
//            new StandardCard(Rank.ACE, Suit.DIAMOND),
//            new StandardCard(Rank.FIVE, Suit.CLUB),
//            new StandardCard(Rank.TWO, Suit.HEART),
//            new StandardCard(Rank.NINE, Suit.HEART),
//            new StandardCard(Rank.THREE, Suit.HEART),
//            new StandardCard(Rank.EIGHT, Suit.SPADE),
//            new StandardCard(Rank.THREE, Suit.DIAMOND),
//            new StandardCard(Rank.FOUR, Suit.DIAMOND),
//            new StandardCard(Rank.KING, Suit.CLUB),
//            new StandardCard(Rank.QUEEN, Suit.DIAMOND),
//            new StandardCard(Rank.TWO, Suit.SPADE)
//    );
//
//    List<StandardCard> cards3 = Arrays.asList(
//            new StandardCard(Rank.ACE, Suit.HEART),
//            new StandardCard(Rank.FIVE, Suit.DIAMOND),
//            new StandardCard(Rank.TEN, Suit.HEART),
//            new StandardCard(Rank.NINE, Suit.SPADE),
//            new StandardCard(Rank.THREE, Suit.SPADE),
//            new StandardCard(Rank.SEVEN, Suit.CLUB),
//            new StandardCard(Rank.QUEEN, Suit.SPADE),
//            new StandardCard(Rank.JACK, Suit.DIAMOND),
//            new StandardCard(Rank.KING, Suit.DIAMOND),
//            new StandardCard(Rank.JACK, Suit.SPADE)
//    );
//    List<StandardCard> cards4 = Arrays.asList(
//            new StandardCard(Rank.ACE, Suit.SPADE),
//            new StandardCard(Rank.FIVE, Suit.HEART),
//            new StandardCard(Rank.TEN, Suit.SPADE),
//            new StandardCard(Rank.FOUR, Suit.HEART),
//            new StandardCard(Rank.SEVEN, Suit.HEART),
//            new StandardCard(Rank.SEVEN, Suit.DIAMOND),
//            new StandardCard(Rank.JACK, Suit.CLUB),
//            new StandardCard(Rank.JACK, Suit.HEART),
//            new StandardCard(Rank.KING, Suit.HEART),
//            new StandardCard(Rank.SIX, Suit.DIAMOND)
//    );
//
//    List<StandardCard> cards5 = Arrays.asList(
//            new StandardCard(Rank.TEN, Suit.CLUB),
//            new StandardCard(Rank.TWO, Suit.CLUB),
//            new StandardCard(Rank.NINE, Suit.CLUB),
//            new StandardCard(Rank.EIGHT, Suit.CLUB),
//            new StandardCard(Rank.QUEEN, Suit.HEART),
//            new StandardCard(Rank.FOUR, Suit.SPADE),
//            new StandardCard(Rank.FIVE, Suit.SPADE),
//            new StandardCard(Rank.SEVEN, Suit.SPADE),
//            new StandardCard(Rank.KING, Suit.SPADE),
//            new StandardCard(Rank.SIX, Suit.HEART)
//    );

    wm0.startPlay(5, unsortedDeck);

    wm0.updateCurPlayer(4);
    wm0.updateCurSuit(Suit.CLUB);
    wm0.updateHandWinner(2);

    wm0.getPlayers().get(0).updateHandsWon(1);
    wm0.getPlayers().get(1).updateHandsWon(2);
    wm0.getPlayers().get(2).updateHandsWon(2);
    wm0.getPlayers().get(3).updateHandsWon(4);
    wm0.getPlayers().get(4).updateHandsWon(3);

    assertEquals(wm0.getPlayers().get(0).getHandsWon(), 1);
    assertEquals(wm0.getPlayers().get(1).getHandsWon(), 2);
    assertEquals(wm0.getPlayers().get(2).getHandsWon(), 2);
    assertEquals(wm0.getPlayers().get(3).getHandsWon(), 4);
    assertEquals(wm0.getPlayers().get(4).getHandsWon(), 3);

    String gameState1 = "Number of players: 5\n" +
            "Player 1: A♣, Q♣, 10♦, 9♦, 8♦, 8♥, 6♣, 6♠, 4♣, 3♣, 2♦\n" +
            "Player 2: A♦, K♣, Q♦, 9♥, 8♠, 5♣, 4♦, 3♦, 3♥, 2♥, 2♠\n" +
            "Player 3: A♥, K♦, Q♠, J♦, J♠, 10♥, 9♠, 7♣, 5♦, 3♠\n" +
            "Player 4: A♠, K♥, J♣, J♥, 10♠, 7♦, 7♥, 6♦, 5♥, 4♥\n" +
            "Player 5: K♠, Q♥, 10♣, 9♣, 8♣, 7♠, 6♥, 5♠, 4♠, 2♣\n" +
            "Player 1: 1 hands won\n" +
            "Player 2: 2 hands won\n" +
            "Player 3: 2 hands won\n" +
            "Player 4: 4 hands won\n" +
            "Player 5: 3 hands won\n" +
            "Turn: Player 5";

    assertEquals(gameState1, wm0.getGameState());
  }

  // one of the players won
  @Test
  public void testGetGameState2() {

    Player p1 = new Player<StandardCard>();
    Player p2 = new Player<StandardCard>();
    Player p3 = new Player<StandardCard>();

    p1.addCard(c1);
    p1.addCard(c2);
    p1.addCard(c3);
    p2.addCard(c4);
    p2.addCard(c5);
    p2.addCard(c6);
    p2.addCard(c7);
    p2.addCard(c8);
    p2.addCard(c9);

    wm0.addPlayer(p1);
    wm0.addPlayer(p2);
    wm0.addPlayer(p3);


    List<StandardCard> cards1 = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.CLUB),
            new StandardCard(Rank.TEN, Suit.DIAMOND),
            new StandardCard(Rank.TWO, Suit.DIAMOND));

    List<StandardCard> cards2 = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.DIAMOND),
            new StandardCard(Rank.FIVE, Suit.CLUB),
            new StandardCard(Rank.TWO, Suit.HEART),
            new StandardCard(Rank.NINE, Suit.HEART),
            new StandardCard(Rank.THREE, Suit.HEART),
            new StandardCard(Rank.EIGHT, Suit.SPADE));

    List<StandardCard> cards3 = new ArrayList<StandardCard>();

    wm0.updateCurPlayer(1);
    wm0.updateCurSuit(Suit.DIAMOND);
    wm0.updateHandWinner(2);

    wm0.getPlayers().get(0).updateHandsWon(2);
    wm0.getPlayers().get(1).updateHandsWon(1);
    wm0.getPlayers().get(2).updateHandsWon(4);

    assertEquals(wm0.getPlayers().get(0).getHandsWon(), 2);
    assertEquals(wm0.getPlayers().get(1).getHandsWon(), 1);
    assertEquals(wm0.getPlayers().get(2).getHandsWon(), 4);

    assertEquals(wm0.getPlayers().get(0).getCards(), cards1);
    assertEquals(wm0.getPlayers().get(1).getCards(), cards2);
    assertEquals(wm0.getPlayers().get(2).getCards(), cards3);


    String gameState1 = "Number of players: 3\n" +
            "Player 1: A♣, 10♦, 2♦\n" +
            "Player 2: A♦, 9♥, 8♠, 5♣, 3♥, 2♥\n" +
            "Player 3: \n" +
            "Player 1: 2 hands won\n" +
            "Player 2: 1 hands won\n" +
            "Player 3: 4 hands won\n" +
            "Turn: Player 2";

    assertEquals(gameState1, wm0.getGameState());
  }

  // Game over.
  @Test
  public void testGetGameState3() {

    StandardCard c1 = new StandardCard(Rank.ACE, Suit.DIAMOND);
    StandardCard c2 = new StandardCard(Rank.FIVE, Suit.CLUB);

    Player p1 = new Player<StandardCard>();
    Player p2 = new Player<StandardCard>();
    Player p3 = new Player<StandardCard>();
    Player p4 = new Player<StandardCard>();

    p1.addCard(c1);
    p1.addCard(c2);

    wm0.addPlayer(p1);
    wm0.addPlayer(p2);
    wm0.addPlayer(p3);
    wm0.addPlayer(p4);

    wm0.updateCurPlayer(1);
    wm0.updateCurSuit(Suit.CLUB);
    wm0.updateHandWinner(4);

    List<StandardCard> cards1 = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.DIAMOND),
            new StandardCard(Rank.FIVE, Suit.CLUB)
    );

    List<StandardCard> cards2 = new ArrayList<StandardCard>();

    List<StandardCard> cards3 = new ArrayList<StandardCard>();

    List<StandardCard> cards4 =new ArrayList<StandardCard>();

    wm0.getPlayers().get(0).updateHandsWon(0);
    wm0.getPlayers().get(1).updateHandsWon(3);
    wm0.getPlayers().get(2).updateHandsWon(3);
    wm0.getPlayers().get(3).updateHandsWon(4);

    assertEquals(wm0.getPlayers().get(0).getHandsWon(), 0);
    assertEquals(wm0.getPlayers().get(1).getHandsWon(), 3);
    assertEquals(wm0.getPlayers().get(2).getHandsWon(), 3);
    assertEquals(wm0.getPlayers().get(3).getHandsWon(), 4);

    assertEquals(wm0.getPlayers().get(0).getCards(), cards1);
    assertEquals(wm0.getPlayers().get(1).getCards(), cards2);
    assertEquals(wm0.getPlayers().get(2).getCards(), cards3);
    assertEquals(wm0.getPlayers().get(3).getCards(), cards4);


    String gameState1 = "Number of players: 4\n" +
            "Player 1: A♦, 5♣\n" +
            "Player 2: \n" +
            "Player 3: \n" +
            "Player 4: \n" +
            "Player 1: 0 hands won\n" +
            "Player 2: 3 hands won\n" +
            "Player 3: 3 hands won\n" +
            "Player 4: 4 hands won\n" +
            "Game over. Player 4 won";

    assertEquals(gameState1, wm0.getGameState());
  }

  // Game over with a stalemate
  @Test
  public void testGetGameState4() {

    StandardCard c1 = new StandardCard(Rank.ACE, Suit.DIAMOND);
    StandardCard c2 = new StandardCard(Rank.FIVE, Suit.CLUB);

    Player p1 = new Player<StandardCard>();
    Player p2 = new Player<StandardCard>();
    Player p3 = new Player<StandardCard>();
    Player p4 = new Player<StandardCard>();

    p1.addCard(c1);
    p1.addCard(c2);

    wm0.addPlayer(p1);
    wm0.addPlayer(p2);
    wm0.addPlayer(p3);
    wm0.addPlayer(p4);

    wm0.updateCurPlayer(1);
    wm0.updateCurSuit(Suit.CLUB);
    wm0.updateHandWinner(4);

    List<StandardCard> cards1 = Arrays.asList(
            new StandardCard(Rank.ACE, Suit.DIAMOND),
            new StandardCard(Rank.FIVE, Suit.CLUB)
    );

    List<StandardCard> cards2 = new ArrayList<StandardCard>();

    List<StandardCard> cards3 = new ArrayList<StandardCard>();

    List<StandardCard> cards4 =new ArrayList<StandardCard>();

    wm0.getPlayers().get(0).updateHandsWon(2);
    wm0.getPlayers().get(1).updateHandsWon(3);
    wm0.getPlayers().get(2).updateHandsWon(4);
    wm0.getPlayers().get(3).updateHandsWon(4);

    assertEquals(wm0.getPlayers().get(0).getHandsWon(), 2);
    assertEquals(wm0.getPlayers().get(1).getHandsWon(), 3);
    assertEquals(wm0.getPlayers().get(2).getHandsWon(), 4);
    assertEquals(wm0.getPlayers().get(3).getHandsWon(), 4);

    assertEquals(wm0.getPlayers().get(0).getCards(), cards1);
    assertEquals(wm0.getPlayers().get(1).getCards(), cards2);
    assertEquals(wm0.getPlayers().get(2).getCards(), cards3);
    assertEquals(wm0.getPlayers().get(3).getCards(), cards4);


    String gameState1 = "Number of players: 4\n" +
            "Player 1: A♦, 5♣\n" +
            "Player 2: \n" +
            "Player 3: \n" +
            "Player 4: \n" +
            "Player 1: 2 hands won\n" +
            "Player 2: 3 hands won\n" +
            "Player 3: 4 hands won\n" +
            "Player 4: 4 hands won\n" +
            "Game over. Stalemate.";

    assertEquals(gameState1, wm0.getGameState());
  }

  // before a game is started
  @Test (expected = IllegalArgumentException.class)
  public void testIsGameOver1() {
    wm0.isGameOver();
  }

  // more than 1 remaining player
  @Test
  public void testIsGameOver2() {
    wm0.startPlay(4, deck);
    assertEquals(false, wm0.isGameOver());
  }

  // less than 1 remaining player && stalemate
  @Test
  public void testIsGameOver3() {
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.DIAMOND);
    StandardCard c2 = new StandardCard(Rank.FIVE, Suit.CLUB);

    Player p1 = new Player<StandardCard>();
    Player p2 = new Player<StandardCard>();
    Player p3 = new Player<StandardCard>();
    Player p4 = new Player<StandardCard>();

    p1.addCard(c1);
    p1.addCard(c2);

    wm0.addPlayer(p1);
    wm0.addPlayer(p2);
    wm0.addPlayer(p3);
    wm0.addPlayer(p4);

    wm0.updateCurPlayer(1);
    wm0.updateCurSuit(Suit.CLUB);
    wm0.updateHandWinner(4);

    wm0.getPlayers().get(0).updateHandsWon(0);
    wm0.getPlayers().get(1).updateHandsWon(3);
    wm0.getPlayers().get(2).updateHandsWon(3);
    wm0.getPlayers().get(3).updateHandsWon(4);

    assertEquals(true, wm0.isGameOver());
  }

  // before the game is started
  @Test (expected = IllegalArgumentException.class)
  public void testGetWinner1() {

    wm0.getWinner();
  }

  // when there is only 1 winner
  @Test
  public void testGetWinner2() {
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.DIAMOND);
    StandardCard c2 = new StandardCard(Rank.FIVE, Suit.CLUB);

    Player p1 = new Player<StandardCard>();
    Player p2 = new Player<StandardCard>();
    Player p3 = new Player<StandardCard>();
    Player p4 = new Player<StandardCard>();

    p1.addCard(c1);
    p1.addCard(c2);

    wm0.addPlayer(p1);
    wm0.addPlayer(p2);
    wm0.addPlayer(p3);
    wm0.addPlayer(p4);

    wm0.updateCurPlayer(1);
    wm0.updateCurSuit(Suit.CLUB);
    wm0.updateHandWinner(4);

    ArrayList<Integer> winners2 = new ArrayList<Integer>();
    winners2.add(0);
    Integer idxWinner = winners2.get(0);

    assertEquals(idxWinner, wm0.getWinner().get(0));
  }

  // when there are more than 1 winners
  @Test
  public void testGetWinner3() {
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.DIAMOND);
    StandardCard c2 = new StandardCard(Rank.FIVE, Suit.CLUB);

    Player p1 = new Player<StandardCard>();
    Player p2 = new Player<StandardCard>();
    Player p3 = new Player<StandardCard>();
    Player p4 = new Player<StandardCard>();

    p1.addCard(c1);
    p1.addCard(c2);

    wm0.addPlayer(p1);
    wm0.addPlayer(p2);
    wm0.addPlayer(p3);
    wm0.addPlayer(p4);

    wm0.updateCurPlayer(1);
    wm0.updateCurSuit(Suit.CLUB);
    wm0.updateHandWinner(4);

    wm0.getPlayers().get(0).updateHandsWon(2);
    wm0.getPlayers().get(1).updateHandsWon(3);
    wm0.getPlayers().get(2).updateHandsWon(4);
    wm0.getPlayers().get(3).updateHandsWon(4);

    ArrayList<Integer> winners3 = new ArrayList<Integer>();
    winners3.add(2);
    winners3.add(3);

    assertEquals(winners3, wm0.getWinner());
  }

  // when the position cardIdx is invalid (index > size)
  @Test (expected = IllegalArgumentException.class)
  public void testPlay1() {

    wm0.startPlay(5, deck);

    wm0.updateCurPlayer(5);
    wm0.updateCurSuit(Suit.CLUB);
    wm0.updateHandWinner(2);

    wm0.play(4, 12);

  }

  // when the position cardIdx is invalid(index < 0)
  @Test (expected = IllegalArgumentException.class)
  public void testPlay2() {

    wm0.startPlay(5, unsortedDeck);

    wm0.updateCurPlayer(5);
    wm0.updateCurSuit(Suit.CLUB);
    wm0.updateHandWinner(2);

    wm0.play(4, -1);

  }

  // when it is not the current player's turn
  @Test (expected = IllegalArgumentException.class)
  public void testPlay3() {
    wm0.startPlay(3, deck);

    wm0.updateCurPlayer(2);
    wm0.updateCurSuit(Suit.CLUB);
    wm0.updateHandWinner(1);

    wm0.play(1, 0);
  }

  // when the index of player does not exit
  @Test (expected = IllegalArgumentException.class)
  public void testPlay4() {
    wm0.startPlay(5, deck);

    wm0.updateCurPlayer(4);
    wm0.updateCurSuit(Suit.SPADE);
    wm0.updateHandWinner(1);

    wm0.play(6, 0);
  }
  // when the player index is less than 0
  @Test (expected = IllegalArgumentException.class)
  public void testPlay5() {
    wm0.startPlay(4, deck);

    wm0.updateCurPlayer(1);
    wm0.updateCurSuit(Suit.SPADE);
    wm0.updateHandWinner(2);

    wm0.play(-1, 0);
  }

  // when the current card cannot be played(of the wrong suit even though
  // the player has a card of the correct suit)
  @Test (expected = IllegalArgumentException.class)
  public void testPlay6() {
    StandardCard c1 = new StandardCard(Rank.ACE, Suit.DIAMOND);
    StandardCard c2 = new StandardCard(Rank.FIVE, Suit.CLUB);
    StandardCard c3 = new StandardCard(Rank.FOUR, Suit.HEART);
    StandardCard c4 = new StandardCard(Rank.TWO, Suit.HEART);

    Player p1 = new Player<StandardCard>();
    Player p2 = new Player<StandardCard>();
    Player p3 = new Player<StandardCard>();

    p1.addCard(c1);
    p1.addCard(c2);
    p1.addCard(c3);
    p2.addCard(c4);

    wm0.addPlayer(p1);
    wm0.addPlayer(p2);
    wm0.addPlayer(p3);

    wm0.updateCurPlayer(1);
    wm0.updateCurSuit(Suit.CLUB);
    wm0.updateHandWinner(4);

    wm0.play(0, 3);
  }

  // the first play in the current hand
  @Test
  public void testPlay7() {

    StandardCard c1 = new StandardCard(Rank.ACE, Suit.DIAMOND);
    StandardCard c2 = new StandardCard(Rank.FIVE, Suit.CLUB);
    StandardCard c3 = new StandardCard(Rank.FOUR, Suit.HEART);
    StandardCard c4 = new StandardCard(Rank.TWO, Suit.HEART);

    Player p1 = new Player<StandardCard>();
    Player p2 = new Player<StandardCard>();
    Player p3 = new Player<StandardCard>();

    p1.addCard(c1);
    p1.addCard(c2);
    p1.addCard(c3);
    p2.addCard(c4);

    wm0.addPlayer(p1);
    wm0.addPlayer(p2);
    wm0.addPlayer(p3);

    wm0.updateCurPlayer(0);
    wm0.updateCurSuit(Suit.CLUB);

    wm0.play(0, 2);

    assertEquals(Suit.HEART, wm0.getCurSuit());
    assertEquals(0, wm0.getHandWinner());
  }

  //the last play in the current hand
  @Test
  public void testPlay8() {

    StandardCard c1 = new StandardCard(Rank.FIVE, Suit.DIAMOND);
    StandardCard c2 = new StandardCard(Rank.ACE, Suit.DIAMOND);
    StandardCard c3 = new StandardCard(Rank.FOUR, Suit.DIAMOND);
    StandardCard c4 = new StandardCard(Rank.TWO, Suit.HEART);
    StandardCard c5 = new StandardCard(Rank.NINE, Suit.DIAMOND);
    StandardCard c6 = new StandardCard(Rank.SEVEN, Suit.CLUB);

    Player p1 = new Player<StandardCard>();
    Player p2 = new Player<StandardCard>();
    Player p3 = new Player<StandardCard>();

    p1.addCard(c1);
    p2.addCard(c2);
    p3.addCard(c3);
    p1.addCard(c4);
    p2.addCard(c5);
    p3.addCard(c6);

    wm0.addPlayer(p1);
    wm0.addPlayer(p2);
    wm0.addPlayer(p3);

    assertEquals(0, wm0.getCurrentPlayer());

    wm0.play(0, 0);
    assertEquals(1, wm0.getCurrentPlayer());
    assertEquals(Suit.DIAMOND, wm0.getCurSuit());

    wm0.play(1, 0);
    assertEquals(Suit.DIAMOND, wm0.getCurSuit());

    wm0.play(2, 0);
    assertEquals(Suit.DIAMOND, wm0.getCurSuit());

    assertEquals(1, wm0.getHandWinner());

    assertEquals(0, p1.getHandsWon());
    assertEquals(1, p2.getHandsWon());
    assertEquals(0, p3.getHandsWon());
  }

  // neither the first nor the last one
  @Test
  public void testPlay9() {
    StandardCard c1 = new StandardCard(Rank.FIVE, Suit.DIAMOND);
    StandardCard c2 = new StandardCard(Rank.KING, Suit.SPADE);
    StandardCard c3 = new StandardCard(Rank.SIX, Suit.HEART);
    StandardCard c4 = new StandardCard(Rank.TWO, Suit.HEART);
    StandardCard c5 = new StandardCard(Rank.NINE, Suit.DIAMOND);
    StandardCard c6 = new StandardCard(Rank.SEVEN, Suit.CLUB);

    Player p1 = new Player<StandardCard>();
    Player p2 = new Player<StandardCard>();
    Player p3 = new Player<StandardCard>();

    p1.addCard(c1);
    p2.addCard(c2);
    p3.addCard(c3);
    p1.addCard(c4);
    p2.addCard(c5);
    p3.addCard(c6);

    wm0.addPlayer(p1);
    wm0.addPlayer(p2);
    wm0.addPlayer(p3);

    wm0.updateCurPlayer(1);
    wm0.updateCurSuit(Suit.SPADE);

    wm0.play(1, 0);

    assertEquals(2, wm0.getCurrentPlayer());
  }


  // when there are four players
//  @Test
//  public void testRun() throws Exception {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" +
//            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
//    WhistController wc = new WhistController(in, out);
//    wc.startGame(wm0, 4);
//
//    WhistModel copy = wm0;
//
//
//
//  }


//  @Test
//  public void testStartGame() throws Exception {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("0");
//            wm0.startGame(new WhistModel(),0);
//  }



//
  /** *controller* handles exception*/
  @Test (expected = IllegalArgumentException.class)
  public void testStartGameException1() {
    StringReader rd = new StringReader("2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" +
           " 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
    StringBuffer ap = new StringBuffer();
    WhistController W1 = new WhistController(rd, ap);
    WhistModel G1 = new WhistModel();
    W1.startGame(G1, 4);
  }

  /** when there is no player */
  @Test (expected = IllegalArgumentException.class)
  public void testStartGameException2() {
    StringReader rd = new StringReader("4 1 1 1 1");
    StringBuffer ap = new StringBuffer();
    WhistController W1 = new WhistController(rd, ap);
    WhistModel G1 = new WhistModel();
    W1.startGame(G1, 4);
  }


//  @Test
//  public void testWhistController() throws Exception {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("0 0 0 0 0 0 0 0 0 0 " +
//            "0 0 0 0 0 0 0 0 0 0 " +
//            "0 0 0 0 0 0 0 0 0 0 " +
//            "0 0 0 0 0 0 0 0 0 0 " +
//            "0 0 0 0 0 0 0 0 0 0 " +
//            "0 0 0");
//    WhistController w1 = new WhistController(in, out);
//    w1.startGame(new WhistModel(), 3);
//
//    String result = ("Number of players: 3\n" +
//            "Player 1: A♣, J♣, 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, " +
//            "7♥, 4♥, A♠, J♠, 8♠, 5♠, " +
//            "2♠, \n" +
//            "Player 2: K♣, 10♣, 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, " +
//            "6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: Q♣, 9♣, 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, " +
//            "5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 0 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "\n" +
//            "Number of players: 3\n" +
//            "Player 1: J♣, 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, " +
//            "4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: K♣, 10♣, 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥," +
//            " 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: Q♣, 9♣, 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, " +
//            "5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 0 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: J♣, 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, " +
//            "4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 10♣, 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, " +
//            "3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: Q♣, 9♣, 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, " +
//            "5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 0 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: J♣, 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥," +
//            " A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 10♣, 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, " +
//            "K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 9♣, 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, " +
//            "Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 1 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 10♣, 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 9♣, 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 1 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 9♣, 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 1 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 2 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 2 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 2 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 3 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 3 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 3 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 4 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 4 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 4 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 5 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 5 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 5 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 6 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 6 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 6 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 7 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 7 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 7 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 8 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 8 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 8 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 9 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 9 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 9 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 10 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 10 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 10 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 11 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 6♥, 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 11 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 5♥, 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 11 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: 4♥, A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 12 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 3♥, K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 12 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: 2♥, Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 12 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: A♠, J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 13 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: K♠, 10♠, 7♠, 4♠, \n" +
//            "Player 3: Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 13 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 10♠, 7♠, 4♠, \n" +
//            "Player 3: Q♠, 9♠, 6♠, 3♠, \n" +
//            "Player 1: 13 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: J♠, 8♠, 5♠, 2♠, \n" +
//            "Player 2: 10♠, 7♠, 4♠, \n" +
//            "Player 3: 9♠, 6♠, 3♠, \n" +
//            "Player 1: 14 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: 8♠, 5♠, 2♠, \n" +
//            "Player 2: 10♠, 7♠, 4♠, \n" +
//            "Player 3: 9♠, 6♠, 3♠, \n" +
//            "Player 1: 14 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: 8♠, 5♠, 2♠, \n" +
//            "Player 2: 7♠, 4♠, \n" +
//            "Player 3: 9♠, 6♠, 3♠, \n" +
//            "Player 1: 14 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: 8♠, 5♠, 2♠, \n" +
//            "Player 2: 7♠, 4♠, \n" +
//            "Player 3: 6♠, 3♠, \n" +
//            "Player 1: 15 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: 5♠, 2♠, \n" +
//            "Player 2: 7♠, 4♠, \n" +
//            "Player 3: 6♠, 3♠, \n" +
//            "Player 1: 15 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: 5♠, 2♠, \n" +
//            "Player 2: 4♠, \n" +
//            "Player 3: 6♠, 3♠, \n" +
//            "Player 1: 15 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: 5♠, 2♠, \n" +
//            "Player 2: 4♠, \n" +
//            "Player 3: 3♠, \n" +
//            "Player 1: 16 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player1\n" +
//            "Number of players: 3\n" +
//            "Player 1: 2♠, \n" +
//            "Player 2: 4♠, \n" +
//            "Player 3: 3♠, \n" +
//            "Player 1: 16 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player2\n" +
//            "Number of players: 3\n" +
//            "Player 1: 2♠, \n" +
//            "Player 2: \n" +
//            "Player 3: 3♠, \n" +
//            "Player 1: 16 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Turn: Player3\n" +
//            "Number of players: 3\n" +
//            "Player 1: 2♠, \n" +
//            "Player 2: \n" +
//            "Player 3: \n" +
//            "Player 1: 17 hands won\n" +
//            "Player 2: 0 hands won\n" +
//            "Player 3: 0 hands won\n" +
//            "Game over. Player 1 won.");
//
//    assertEquals(result, out.toString());
//  }
}

