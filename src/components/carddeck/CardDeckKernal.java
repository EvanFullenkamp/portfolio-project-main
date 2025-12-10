package components.carddeck;
import components.standard.Standard;

/**
 * Card deck component with primary methods.
 *
 * {@code CardDeck is modeled by a sequence of 52 Cards}
 *
 * @initially <pre>
 * {@code default:
 * ensures this contains 52 cards, one of each rank and suit.}
 * </pre>
 *
 */
public interface CardDeckKernal extends Standard<CardDeckKernal> {

    /**
     * Removes the top card from the deck and returns it.
     *
     * @updates {@code this}
     *
     * @requres deck is not empty
     *
     * @ensures this = #this - card drawn
     *
     * @return the card drawn
     */
    Card draw();

    /**
     * Adds Card c to the bottom of this.
     *
     * @param c
     *            the card to add
     *
     * @updates {@code this}
     *
     * @requres c != null
     *
     * @ensures this = #this + c at the bottom
     */
    void bury(Card c);

    /**
     * Returns the number of cards in this deck.
     *
     * @ensures this = #this
     *
     * @return number of cards
     */
    int size();

}
