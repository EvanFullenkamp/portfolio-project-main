import java.util.Random;

import javax.smartcardio.Card;

/**
 * Secondary implementation of the {@code CardDeck} component.
 */
public abstract class CardDeckSecondary implements CardDeck {

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public Card peek() {
        assert this.size() > 0 : "Violation of: this is not empty";

        int size = this.size();
        // Get the top card
        Card top = this.draw();
        // Put top card back in the deck
        this.bury(top);

        // Rotate to get original order
        for (int i = 0; i < size - 1; i++) {
            Card c = this.draw();
            this.bury(c);
        }
        return top;
    }

    @Override
    public void shuffle() {
        int size = this.size();
        Random rand = new Random();

        // Draw top card, then bury it in a random position
        for (int i = 0; i < size; i++) {
            // Draw
            Card c = this.draw();
            // Random number to shuffle deck
            int r = rand.nextInt(size);

            // Shuffle deck
            for (int j = 0; j < r; j++) {
                Card temp = this.draw();
                this.bury(temp);
            }
            // Bury
            this.bury(c);
        }
    }

    @Override
    public String toString() {
        int n = this.size();
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < n; i++) {
            Card c = this.draw();
            sb.append(c.toString()); // relies on Card.toString()
            if (i < n - 1) {
                sb.append(", ");
            }
            this.bury(c); // restore deck
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean same = true;

        if (!(obj instanceof CardDeck)) {
            same = false;
        }

        CardDeck other = (CardDeck) obj;
        if (this.size() != other.size()) {
            same = false;
        }

        int n = this.size();

        int i = 0;
        while (i < n && same) {
            Card c1 = this.draw();
            Card c2 = other.draw();

            if (!c1.equals(c2)) {
                same = false;
            }

            this.bury(c1);
            other.bury(c2);

            i++;
        }

        return same;
    }
}
