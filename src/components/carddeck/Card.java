package components.carddeck;

/**
 * Class representing a playing card with a suit and rank.
 */
public class Card {

    /**
     * Enumerations for the suit of the card.
     */
    public enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS;
    }

    /**
     * Enumerations for the rank of the card.
     */
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    public Suit suit;
    public Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return this.rank + " of " + this.suit;
    }

}
