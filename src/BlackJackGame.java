import components.carddeck.Card;
import components.carddeck.CardDeck1;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Black Jack Game to show how CardDeck can be used.
 */
public class BlackJackGame {

    private static void hitOrStand(Sequence<Card> playerHand, int playerNumber,
            SimpleReader in, SimpleWriter out, CardDeck1 deck) {

        playerNumber++;
        // Ask if they want to hit or stand until they stand or bust.
        int handValue = handValue(playerHand);
        while (handValue < 21) {
            out.println("Player " + (playerNumber)
                    + ", do you want to hit or stand? (h/s): ");
            String response = in.nextLine();
            if (response.equalsIgnoreCase("h")) {
                playerHand.add(0, deck.draw());
                out.println("Player " + (playerNumber) + "'s new card: "
                        + playerHand.entry(0));
                handValue = handValue(playerHand);
                out.println("Player " + (playerNumber) + "'s hand value: "
                        + handValue);
            } else if (response.equalsIgnoreCase("s")) {
                out.println("Player " + (playerNumber) + " stands with value: "
                        + handValue);
                break;
            } else {
                out.println("Invalid input. Please enter 'h' or 's'.");
            }
        }
    }

    /**
     * Calculates the value of a hand in Black Jack.
     *
     * @param hand
     * @requires hand.length() > 0
     * @ensures hand is not changed
     * @return value of hand
     */
    private static int handValue(Sequence<Card> hand) {
        int value = 0;
        // Face cards are worth 10, Aces worth 11, others worth their rank value
        for (int i = 0; i < hand.length(); i++) {
            if (hand.entry(i).getRank() == Card.Rank.KING
                    || hand.entry(i).getRank() == Card.Rank.QUEEN
                    || hand.entry(i).getRank() == Card.Rank.JACK) {
                value += 10;
            } else if (hand.entry(i).getRank() == Card.Rank.ACE) {
                value += 11;
            } else {
                value += hand.entry(i).getRank().ordinal() + 1;
            }
        }
        if (value > 21) {
            // Adjust for Aces if bust
            for (int i = 0; i < hand.length(); i++) {
                if (hand.entry(i).getRank() == Card.Rank.ACE) {
                    value -= 10;
                    if (value <= 21) {
                        break;
                    }
                }
            }
        }
        return value;
    }

    /**
     * Main method to run a simple Black Jack game.
     *
     * @param args
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        // Create and shuffle deck
        CardDeck1 deck = new CardDeck1();
        deck.shuffle();

        out.println("Welcome to Black Jack! Enter Number of Players (1-4): ");
        int numPlayers = in.nextInteger();
        while (numPlayers < 1 || numPlayers > 4) {
            out.println("Invalid number of players. \n "
                    + "Please enter a number between 1 and 4: ");
            numPlayers = in.nextInteger();
        }

        // Sequence to hold dealer's hands.
        Sequence<Card> dealersHand = new Sequence1L<>();

        // Sequences to hold players' hands.
        Sequence<Sequence<Card>> playerHands = new Sequence1L<>();
        for (int i = 0; i < numPlayers; i++) {
            playerHands.add(i, new Sequence1L<Card>());
        }
        // Deal 2 cards to each player, show value, and check for blackjacks.
        for (int i = 0; i < numPlayers; i++) {
            Sequence<Card> hand = playerHands.entry(i);
            hand.add(0, deck.draw());
            out.println(
                    "Player " + (i + 1) + "'s first card: " + hand.entry(0));
            hand.add(0, deck.draw());
            out.println(
                    "Player " + (i + 1) + "'s second card: " + hand.entry(0));

            int handValue = handValue(hand);
            out.println("Player " + (i + 1) + "'s hand value: " + handValue);

            if (handValue == 21) {
                out.println("Player " + (i + 1) + " has Black Jack! Player "
                        + (i + 1) + " wins!");
            }
        }

        // Deal to dealer
        dealersHand.add(0, deck.draw());
        out.println("Dealer's first card: " + dealersHand.entry(0));
        dealersHand.add(0, deck.draw());
        // Dealers second card is hidden
        int dealerValue = handValue(dealersHand);
        if (dealerValue == 21) {
            out.println("Dealer has Black Jack! Dealer wins!");
        }

        // Ask each player if they want to hit or stand.
        for (int i = 0; i < numPlayers; i++) {
            Sequence<Card> hand = playerHands.entry(i);
            hitOrStand(hand, i, in, out, deck);
        }
        // Reveal dealer's hidden card
        out.println("Dealer's hidden card: " + dealersHand.entry(1));
        out.println("Dealer's hand value: " + dealerValue);

        // Dealer hits until reaching 17 or higher if all players haven't busted.
        boolean allBusted = true;
        for (int i = 0; i < numPlayers; i++) {
            Sequence<Card> hand = playerHands.entry(i);
            int playerValue = handValue(hand);
            if (playerValue <= 21) {
                // At least one player hasn't busted
                allBusted = false;
            }
        }
        if (!allBusted) {
            while (dealerValue < 17) {
                Card newCard = deck.draw();
                dealersHand.add(0, newCard);
                out.println("Dealer hits and gets: " + newCard);
                dealerValue = handValue(dealersHand);
                out.println("Dealer's new hand value: " + dealerValue);
                if (dealerValue > 21) {
                    out.println("Dealer busts!");
                } else if (dealerValue >= 17) {
                    out.println("Dealer stands with value: " + dealerValue);
                }
            }
        }

        // Show game results
        for (int i = 0; i < numPlayers; i++) {
            Sequence<Card> hand = playerHands.entry(i);
            int playerValue = handValue(hand);
            out.println("Player " + (i + 1) + "'s final hand value: "
                    + playerValue);
            if (playerValue > 21 && dealerValue <= 21) {
                out.println("Player " + (i + 1) + " loses!");
            } else if (dealerValue > 21 || playerValue > dealerValue) {
                out.println("Player " + (i + 1) + " wins!");
            } else if (playerValue < dealerValue) {
                out.println("Dealer wins against Player " + (i + 1) + ".");
            } else { // tie
                out.println("Push between Dealer and Player " + (i + 1) + ".");
            }
        }

        in.close();
        out.close();

    }
}
