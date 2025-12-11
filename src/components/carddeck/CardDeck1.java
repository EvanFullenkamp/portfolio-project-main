package components.carddeck;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * @representationObject deck
 * @representationType Sequence<Card>
 *
 * @convention deck != null AND for all i in [0, deck.length()): deck.entry(i)!=
 *             null
 *
 * @correspondence this = <deck.entry(0), deck.entry(1), ...,
 *                 deck.entry(deck.length()-1)> where index 0 is the top of the
 *                 deck.
 */
public class CardDeck1 extends CardDeckSecondary {

    /**
     * Representation of the deck of cards.
     */
    private Sequence<Card> deck;

    /**
     * Default constructor.
     */
    public CardDeck1() {
        this.createNewRep();
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public final CardDeck1 newInstance() {
        return new CardDeck1();
    }

    @Override
    public void transferFrom(CardDeckKernal source) {
        assert source != this : "Violation of: source is not this";

        CardDeck1 s = (CardDeck1) source;
        this.deck = s.deck;
        s.deck = new Sequence1L<Card>();
    }

    /**
     * Creats a 52-card deck in the sequence.
     *
     * @ensures deck contains 52 cards, one of each suit and rank combination.
     */
    @Override
    public void createNewRep() {
        this.deck = new Sequence1L<Card>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                this.deck.add(0, new Card(suit, rank));
            }
        }
    }

    /**
     * Removes and returns the top card in the deck.
     *
     * @requires deck is not empty
     * @ensures size of deck decreases by 1 and only the top card is removed
     * @return top card in the deck
     */
    @Override
    public Card draw() {
        return this.deck.remove(0);
    }

    /**
     * Adds Card c to the bottom of the deck.
     *
     * @param c
     *            card to be placed at the bottom of the deck
     *
     * @requires c is not null
     * @ensures size of deck increases by 1 and c is at the bottom of the deck
     */
    @Override
    public void bury(Card c) {
        this.deck.add(this.deck.length(), c);
    }

    @Override
    public int size() {
        return this.deck.length();
    }

}
