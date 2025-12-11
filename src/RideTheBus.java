import components.carddeck.Card;
import components.carddeck.CardDeck1;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class RideTheBus {

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        // Create and shuffle the deck
        CardDeck1 deck = new CardDeck1();
        deck.shuffle();

        // Draw first card
        Card firstCard = deck.draw();
        out.println("First card drawn: " + firstCard);

        // GUESS HIGHER OR LOWER
        boolean validInput = false;
        Card secondCard = deck.draw();
        while (!validInput) {
            out.print("Will the next card be higher or lower? (h/l): ");
            String guess = in.nextLine();
            if (guess.equals("h")) {
                out.println("Second card drawn: " + secondCard);
                if (secondCard.getRank().compareTo(firstCard.getRank()) > 0) {
                    out.println("Correct! The second card is higher.");
                    validInput = true; // Exit loop on valid input
                } else {
                    out.println(
                            "Incorrect. The second card is not higher. You lose.");
                    // Return because the player lost
                    return;
                }
            } else if (guess.equals("l")) {
                out.println("Second card drawn: " + secondCard);
                if (secondCard.getRank().compareTo(firstCard.getRank()) < 0) {
                    out.println("Correct! The second card is lower.");
                    validInput = true; // Exit loop on valid input
                } else {
                    out.println(
                            "Incorrect. The second card is not lower. You lose.");
                    // Return because the player lost
                    return;
                }
            } else {
                out.println("Invalid input. Please enter 'h' for higher"
                        + " or 'l' for lower.");
            }
        }

        // GUESS RED OR BLACK
        validInput = false;
        Card thirdCard = deck.draw();
        while (!validInput) {
            out.print("Will the next card be red or black? (r/b): ");
            String colorGuess = in.nextLine();
            out.println("Third card drawn: " + thirdCard);
            boolean isRed = thirdCard.getSuit() == Card.Suit.HEARTS
                    || thirdCard.getSuit() == Card.Suit.DIAMONDS;
            if (colorGuess.equals("r")) {
                if (isRed) {
                    out.println("Correct! The third card is red.");
                    validInput = true; // Exit loop on valid input
                } else {
                    out.println(
                            "Incorrect. The third card is not red. You lose.");
                    // Return because the player lost
                    return;
                }
            } else if (colorGuess.equals("b")) {
                if (!isRed) {
                    out.println("Correct! The third card is black.");
                    validInput = true; // Exit loop on valid input
                } else {
                    out.println(
                            "Incorrect. The third card is not black. You lose.");
                    // Return because the player lost
                    return;
                }
            } else {
                out.println(
                        "Invalid input. Please enter 'r' for red or 'b' for black.");
            }
        }

        // INSIDE OR OUTSIDE
        validInput = false;
        out.println("Guess if the next card's rank is inside or outside "
                + firstCard.getRank() + " and " + secondCard.getRank()
                + " (i/o).");
        String ioGuess = in.nextLine();
        Card fourthCard = deck.draw();
        while (!validInput) {
            out.println("Fourth card drawn: " + fourthCard);
            int lowRank = Math.min(firstCard.getRank().ordinal(),
                    secondCard.getRank().ordinal());
            int highRank = Math.max(firstCard.getRank().ordinal(),
                    secondCard.getRank().ordinal());
            int fourthRank = fourthCard.getRank().ordinal();
            if (ioGuess.equals("i")) {
                if (fourthRank > lowRank && fourthRank < highRank) {
                    out.println("Correct! The fourth card is inside.");
                    validInput = true; // Exit loop on valid input
                } else {
                    out.println(
                            "Incorrect. The fourth card is not inside. You lose.");
                    // Return because the player lost
                    return;
                }
            } else if (ioGuess.equals("o")) {
                if (fourthRank < lowRank || fourthRank > highRank) {
                    out.println("Correct! The fourth card is outside.");
                    validInput = true; // Exit loop on valid input
                } else {
                    out.println(
                            "Incorrect. The fourth card is not outside. You lose.");
                    // Return because the player lost
                    return;
                }
            } else {
                out.println("Invalid input. Please enter 'i' for inside"
                        + " or 'o' for outside.");
            }
        }

        // GUESS SUIT
        validInput = false;
        Card fifthCard = deck.draw();
        while (!validInput) {
            out.print("Guess the suit of the next card "
                    + "(hearts, diamonds, clubs, spades): ");
            String suitGuess = in.nextLine().toLowerCase();
            out.println("Card drawn: " + fifthCard);
            String actualSuit = fifthCard.getSuit().toString().toLowerCase();
            if (suitGuess.equals(actualSuit)) {
                out.println("Correct! The card is " + actualSuit + ".");
                validInput = true; // Exit loop on valid input
            } else {
                out.println("Incorrect. The card is not " + suitGuess
                        + ". You lose.");
                // Return because the player lost
                return;
            }
        }
        out.println("Congratulations! You have successfully ridden the bus!");

        in.close();
        out.close();
    }
}
