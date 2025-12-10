import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.carddeck.Card;
import components.carddeck.CardDeck1;

/**
 * JUnit test fixture for the secondary methods in CardDeck.
 */
public class CardDeckSecondaryTest {

    /**
     * Test isEmpty on a new deck (should be false).
     */
    @Test
    public void testIsEmptyFalse() {
        CardDeck1 d = new CardDeck1();
        assertFalse(d.isEmpty());
    }

    /**
     * Test isEmpty after removing all cards (should be true).
     */
    @Test
    public void testIsEmptyTrue() {
        CardDeck1 d = new CardDeck1();

        while (!d.isEmpty()) {
            d.draw();
        }

        assertTrue(d.isEmpty());
    }

    /**
     * Test peek, should return top card without removing it.
     */
    @Test
    public void testPeekOne() {
        CardDeck1 d = new CardDeck1();

        Card top1 = d.peek();
        Card top2 = d.peek();

        // deck should be unchanged
        assertEquals(52, d.size());

        // peek should be consistent
        assertEquals(top1.getRank(), top2.getRank());
        assertEquals(top1.getSuit(), top2.getSuit());
    }

    /**
     * Test shuffle, deck should remain same size but order should change.
     */
    @Test
    public void testShuffle1() {
        CardDeck1 d1 = new CardDeck1();
        CardDeck1 d2 = new CardDeck1();

        d1.shuffle();

        // Size must still be 52
        assertEquals(52, d1.size());

        // d1 should not be identical to d2 most of the time
        Card c1 = d1.draw();
        Card c2 = d2.draw();

        assertNotEquals(c2.toString(), c1.toString());
    }

    /**
     * Test shuffle on 2 decks, shuffle should be random so they should differ.
     */
    @Test
    public void testShuffle2() {
        CardDeck1 d1 = new CardDeck1();
        CardDeck1 d2 = new CardDeck1();

        d1.shuffle();
        d2.shuffle();

        // d1 should not be identical to d2 most of the time
        Card c1 = d1.draw();
        Card c2 = d2.draw();

        assertNotEquals(c2.toString(), c1.toString());
    }

    /**
     * Ensures peek() on 1-card deck works properly.
     */
    @Test
    public void testPeekOnSingleCardDeck() {
        CardDeck1 d = new CardDeck1();

        // remove 51 cards
        while (d.size() > 1) {
            d.draw();
        }

        Card only = d.peek();
        assertEquals(1, d.size());

        Card onlyAgain = d.peek();
        assertEquals(only.getRank(), onlyAgain.getRank());
    }
}
