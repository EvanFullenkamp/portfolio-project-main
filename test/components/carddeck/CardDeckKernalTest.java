package components.carddeck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * JUnit test fixture for CardDeckKernal methods.
 */
public class CardDeckKernalTest {

    /**
     * Creates and returns a standard 52-card deck.
     *
     * @return a standard 52-card deck
     */
    private CardDeck1 createStandardDeck() {
        return new CardDeck1();
    }

    /**
     * Test constructor, should contain 52 cards.
     */
    @Test
    public void testConstructorSize() {
        CardDeck1 d = new CardDeck1();
        assertEquals(52, d.size());
    }

    /**
     * Test constructor and check the first draw returns a valid card.
     */
    @Test
    public void testConstructorDrawOne() {
        CardDeck1 d = new CardDeck1();
        Card c = d.draw();
        assertNotEquals(null, c);
        assertEquals(51, d.size());
    }

    /**
     * Test bury, add a card to bottom.
     */
    @Test
    public void testBury() {
        CardDeck1 d = new CardDeck1();
        Card bottom = new Card(Card.Suit.CLUBS, Card.Rank.ACE);

        d.bury(bottom);

        assertEquals(53, d.size());

        // remove all cards until the last
        Card lastCard = null;
        while (d.size() > 0) {
            lastCard = d.draw();
        }

        assertEquals(bottom.getSuit(), lastCard.getSuit());
        assertEquals(bottom.getRank(), lastCard.getRank());
    }

    /**
     * Test size after draw.
     */
    @Test
    public void testSizeAfterDraw() {
        CardDeck1 d = new CardDeck1();
        d.draw();
        assertEquals(51, d.size());
    }

    /**
     * Test clear, should reset deck to 52 new cards.
     */
    @Test
    public void testClear() {
        CardDeck1 d = new CardDeck1();
        d.draw(); // shrink deck
        d.clear();
        assertEquals(52, d.size());
    }

    /**
     * Test newInstance, must return empty new deck of size 52.
     */
    @Test
    public void testNewInstance() {
        CardDeck1 d = new CardDeck1();
        CardDeck1 newDeck = d.newInstance();

        // new deck should be full and separate
        assertEquals(52, newDeck.size());
        assertEquals(52, d.size());
        assertNotEquals(d, newDeck); // distinct objects
    }

    /**
     * Test transferFrom, moves contents into destination, empties source.
     */
    @Test
    public void testTransferFrom() {
        CardDeck1 d1 = new CardDeck1();
        CardDeck1 d2 = new CardDeck1();

        // shrink d2 so we know which is which
        d2.draw();
        d2.draw(); // d2 now has 50 cards

        d1.transferFrom(d2);

        // d1 should now have former d2's content (size 50)
        assertEquals(50, d1.size());

        // d2 must now be empty
        assertEquals(0, d2.size());
    }

    /**
     * Test createNewRep, resets deck to 52 cards.
     */
    @Test
    public void testCreateNewRep() {
        CardDeck1 d = new CardDeck1();
        d.draw();
        d.draw();
        d.createNewRep();
        assertEquals(52, d.size());
    }

}
