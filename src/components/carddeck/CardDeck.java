package components.carddeck;

/**
 * Interface for the secondary methods in CardDeck.
 * {@code CardDeckKernal enhanced with secondary methods}
 */
public interface CardDeck extends CardDeckKernal {

    /**
     * Returns whether or not the deck is empty.
     *
     * @ensures this = #this
     *
     * @return true if deck is empty, false if not
     */
    boolean isEmpty();

    /**
     * Returns the card at the top of the deck without changing the deck.
     *
     * @requres deck is not empty
     *
     * @ensures this = #this and peek = the top card.
     *
     * @return the card at the top this deck
     */
    Card peek();

    /**
     * Shuffles the deck randomly.
     *
     * @updates {@code this}
     *
     * @ensures this is a permutation of #this
     */
    void shuffle();

}
