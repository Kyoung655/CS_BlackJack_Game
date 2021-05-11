import java.util.ArrayList;

public class Deck {											// Deck object type

    ArrayList<Card> card_deck = new ArrayList<Card>(52); 	// Array stores the entire deck (of cards)

    public Deck () {										// used to construct the deck
        reset_deck (card_deck);								// creates a new deck, will all cards labeled as unused
    }

    public static void reset_deck (ArrayList<Card> cards_left) {	// takes in the array, to populate the cards
        cards_left.clear();											// ensures the array is empty 
       
        for (int i = 1; i <= 13; i++) {								// for loop for each value of card (1,2,3... queen, king)
            Card new_club = new Card(i, "Clubs");					// each value needs one of each suit (creates card object)
            cards_left.add(new_club);								// Stores object in array 
            	
            Card new_diamond = new Card(i, "Diamonds");				// each value needs one of each suit (creates card object)
            cards_left.add(new_diamond);							// Stores object in array 

            Card new_hearts = new Card(i, "Hearts");				// each value needs one of each suit (creates card object)
            cards_left.add(new_hearts);								// Stores object in array 

            Card new_spade = new Card(i, "Spades");					// each value needs one of each suit (creates card object)
            cards_left.add(new_spade);								// Stores object in array 
        }
    }

    public String toString() {								// used to print out entire deck (for testing)

        String cards_in_deck = "DECK: \n";					// formatting

        for (Card card : card_deck) {						// iterate through every card
            cards_in_deck += (card + ", ");					// formatting
            cards_in_deck += (card.card_avail + "\n");		// Used to see if card is used
        }

        return cards_in_deck;								// returns string of entire deck
    }
}
