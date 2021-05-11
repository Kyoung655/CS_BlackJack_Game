import java.util.ArrayList;
import java.util.Random;

public class Hand { 										// Class to store the any players cards (Both the computer and user)
	
    ArrayList<Card> cards_in_hand = new ArrayList<Card>();	//Array that stores any hand

    public Hand(Deck deck) {								// Pick card by calling method
        pick_card(deck);
        pick_card(deck);									// Pick card by calling method
    }

    public void pick_card(Deck deck) {						// method picks a random card
        Random num = new Random();							// use random number, to pick random card
        boolean used;										// used to label card
        Card to_append;										// used to store what card to add

        do {											
            int card_value = num.nextInt(52);				// picks card
            to_append = deck.card_deck.get(card_value);		// checks if card has been used
            used = !to_append.card_avail;
        } while(used);										// re-picks random card if it has been used

        cards_in_hand.add(to_append);						// adds unused card to hand
        to_append.card_avail = false;						// labels that card that is added as used

        Card.print_image(to_append.card_image_name);		// used for debugging (not seen by user in processing)
    }

    public int sum_of_cards() {								// method to sum the cards

        int sum_of_cards = 0;								// Initializes sum to 0

        for (Card card : cards_in_hand) {					// Initializes through to get the value of all the cards
            sum_of_cards += card.real_card_value;			// adds up each cards value
        }
        return sum_of_cards;								// returns the sum of cards
    }

    public boolean hand_overflow() {						// checks if the sum of the hand is above 21 (game lost)
        return sum_of_cards() > 21;							// returns true if so
    }

    public String toString(boolean all) {					// Used to print out the hand to the user (boolean to see what to print)

        String cards_in_deck = "";							// initializes what to print as nothing 

        for (Card card : cards_in_hand) {					// initializes through every card 
        	
        	String card_real = "";							// initializes what to print as nothing (card specific)
        	
            switch (card.card_value) {						// use swicth to name every card value
            
            case 1:
            	card_real = ("Ace");
                break;
            case 2:
            	card_real = ("Two");
                break;
            case 3:
            	card_real = ("Three");
                break;
            case 4:
            	card_real = ("Four");
                break;
            case 5:
            	card_real = ("Five");
                break;
            case 6:
            	card_real = ("Six");
                break;
            case 7:
            	card_real = ("Seven");
                break;
            case 8:
            	card_real = ("Eight");
                break; 
            case 9:
            	card_real = ("Nine");
                break;
            case 10:
            	card_real = ("Ten");
                break;
            case 11:
            	card_real = ("Jack");
                break;
            case 12:
            	card_real = ("Queen");
                break;
            case 13:
            	card_real = ("King");
                break;
        }

        	
            cards_in_deck += (card_real + " of " + card.card_suit + " - ");		// adds card name to string, with formatting
            
            if (all == false) {													// breaks if we only what to display the first card (used for dealer)
            	break;
            }
            
        }

        cards_in_deck = cards_in_deck.substring(0, (cards_in_deck.length() - 2));		// formatting
        return cards_in_deck;															// returns cards in deck
    }
}
