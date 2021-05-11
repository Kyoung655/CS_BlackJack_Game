import java.util.Random;

public class Card {									// Each card would be an object with certain properties 

    int card_value;									// Initialize variable for card value 
    int real_card_value;							// Initialize variable for card value (real as, jack, queen, king = 10)
    String card_suit;								// Assigns each card a suit
    String card_name;								// Assigns the card a name (for printing)
    String card_image_name;							// Each card can be displayed as an image (not in use- yet) 
    Boolean card_avail = true;

    public Card (int number, String suit) {				// Initializes card when given a number and suit

        card_value = number;							// Initializes card with given number
        	
        if (card_value >= 10) {							// Real value of card (jack,queen and king) is set to 10
        	real_card_value = 10; 
        } else {real_card_value = card_value;}			// Else we leave the value as is
        
        card_suit = suit;								// Initializes card with given suit
        card_name = card_name(card_value, card_suit);	// Stores card name
        card_image_name = card_image_name(card_name);   // Stores card image name (not in use)
    }

    public Card () {									// Initializes card when NOT given a number and suit (needs to select on its own... random)
        card_value = card_value();						// Calls method to get value (random) 
         
        if (card_value >= 10) {							// Real value of card (jack,queen and king) is set to 10
        	real_card_value = 10; 			
        } else {real_card_value = card_value;}			// Else we leave the value as is
        
        card_suit = card_suit();						// Calls method to get suit (random) 
        card_name = card_name(card_value, card_suit);	// Stores card name
        card_image_name = card_image_name(card_name);	// Stores card image name (not in use)
    }

    public static int card_value() {					// get random value (between 1 and 13) for card to use
        Random num = new Random();						// Random generator
        int card_value = (num.nextInt(12) + 1);			// gets only integer, +1 to avoid 0

        return card_value;								// returns value generated
    }

    public static String card_suit() {					// gets random suit
        Random num = new Random();						// Random generator
        int suit_value = num.nextInt(4);				// gets only integer, 4 integers for 4 suits

        String suit = "";								// Initializes empty suit

			// USE THIS TO DRAW IMAGES
        switch (suit_value) {							// Selects suit based on random number
            case 0:
                suit = ("c");
                break;
            case 1:
                suit = ("d");
                break;
            case 2:
                suit = ("h");
                break;
            case 3:
                suit = ("s");
                break;
    }

        return suit;									// returns suit
    }	

    public static String card_name(int card_value, String card_suit) {		// creates formatted names based on value and suit of card

        String card_name = String.valueOf(card_value) + card_suit;			// creates formatted names based on value and suit of card
        return card_name;													// returns 'nice' name
    }

    public static String card_image_name(String card_name) {				// NOT IN USE YET -  creates formatted names based on value and suit of card (based of image names)
        String card_image_name = card_name + ".png";						// creates formatted names based on value and suit of card
        return card_image_name;												// returns 'nice' name
    }	

    public static boolean print_image(String card_image_name) {				// NOT IN USE YET

        // ADD IN GRAPHICS CODE TO DRAW THE IMAGE IN PROCESSING (account for root to the print the image)

        return true;														// If successful, if not will display normal image
    }

    public String toString() {												// To print card value (for testing)
        return card_name;
    }
}
