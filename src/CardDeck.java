import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.random.Random;
import components.random.Random1L;

public class CardDeck {

    private Sequence<Card> deck;

    /**
     * Constructs new empty card deck;
     * 
     * @ensures deck is empty
     */
    public CardDeck() {
        deck = new Sequence1L<>();
    }

    /**
     * Creats a 52-card deck in the sequence.
     * 
     * @ensures deck contains 52 cards, one of each suit and rank combination.
     * @return deck
     */
    public Sequence<Card> createNewDeck(){
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(0, new Card(suit, rank));
            }
        }
        return deck;
    }

    /**
     * Removes and returns the top card in the deck.
     * 
     * @requires deck is not empty
     * @ensures size of deck decreases by 1
     * @return top card in the deck
     */
    public Card draw() {
        Card c = deck.remove(0);

        return c;
    }

    /**
     * Adds Card c to the bottom of the deck.
     * 
     * @param c card to be placed at the bottom of the deck
     * 
     * @requires c is not null
     * @ensures size of deck increases by 1 and c is at the bottom of the deck
     */
    public void bury(Card c) {
        deck.add(deck.length() - 1, c);
    }

    /**
     * Returns the number of cards in the deck.
     * 
     * @return the size of the deck
     */
    public int size() {
        return deck.length();
    }

    /**
     * Returns whether or not the deck is empty.
     * 
     * @ensures returns true if deck has no cards, false otherwise
     * @return whether deck is empty
     */
    public boolean isEmpty() {
        return (deck.length() == 0);
    }

    /**
     * Returns the top card of the deck.
     * 
     * @requires deck is not empty
     * @ensures deck is unchanges
     * @return top card of the deck
     */
    public Card peek () {
        return deck.entry(0);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        CardDeck deck = new CardDeck();
        deck.createNewDeck();

        for (int i = 0; i < deck.deck.length(); i++) {
            Card c = deck.deck.entry(i);
            out.println(c + "   " + i);
        }

        out.println("SIZE before draw: " + deck.size());
        Card cTest = deck.draw();
        out.println("DRAW: " + cTest);
        out.println("SIZE after draw: " + deck.size());
        out.println("PEEK: " + deck.peek());
        deck.bury(cTest);
        out.println("SIZE after bury: " + deck.size());
        out.println("IS EMPTY:" + deck.isEmpty());

        out.println();

        in.close();
        out.close();
    }
}
