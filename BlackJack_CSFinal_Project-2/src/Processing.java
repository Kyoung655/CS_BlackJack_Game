
import processing.core.*;
import ddf.minim.*;

public class Processing extends PApplet{    	
	
    public static void main(String[] args) {
    	PApplet.main("Processing");
    	
    	
    }
    //images for card background and you win picture
	PImage gold_plate;
	PImage back_of_card;
	PImage random_card;
	PImage youwin;

	// total number of images
	int num = 11;
	int numImages = 4;
	// an array of images
	PImage[] images = new PImage[num];

	PFont f;

	boolean boxClicked = false; //make sure you cant hit/stand after round
	boolean locked = false; //mouse conditions
	boolean betchoice = false; //make sure cant change bet after made
	boolean buttonpress = false; //make sure hit/stand cant also be used if over
	
	boolean player_won = true;//overflow/bust conditions
    boolean computer_won = true;
    boolean player_turn = true;	
    boolean computer_turn = true;
    
    boolean outofmoney = false; //cant continue when out of money

	int randomNumber; //for shuffle
	
	int total_money = 4800; //starting money
    int bet_amount; //bet choice
    
    int Rounds_Played = 0; //to mark number of rounds
    
    Deck regular = new Deck(); //creation of deck
    Hand computer = new Hand(regular); //creation of computer hand
    Hand player = new Hand(regular); //creation of user hand
    
    String stringbet = ""; //for easy display
    AudioPlayer audioplayer; //audio player
	Minim minim;
	
	//creates the size of the screen and makes the background dark red
	public void settings() {
		
	  
	  size(1200,850);
	  minim = new Minim(this);
	  audioplayer = minim.loadFile("music2.mp3", 2048);
	  audioplayer.setGain(-10); 
	  audioplayer.play();
	  
	}
	
	//loads images, fonts, and resets background
	public void setup()
	{

	  background(100,0,0);
	  noStroke();

	  //loads the gold plate image and sets the font
	  gold_plate = loadImage("gold_plate.jpg");
	  back_of_card = loadImage("back_of_card.png");
	  youwin = loadImage("youwin.png");
	  
	  //Select Font
	  f = createFont("Arial",16,true);
	  
	  
	  
	}
	
	//creates lines by following the mouse
	public void draw() {

  	  //Gold marble image 
  	  image(gold_plate, 670, 320, 200, gold_plate.height/4);
  	  
  	  //Back of playing card image
  	  image(back_of_card, 150, 150, 150, gold_plate.height/5);
  	  
  	  
  	  //First Rectangle ("Hit")
  	  fill(248, 215, 140);
  	  if (mouseX >= 686 && mouseX <= 856 && mouseY >= 350 && mouseY <= 413) 
  	  {
  	  rect(686, 350, 170, 63);
  	  boxClicked = true; 
  	  } 
  	  else
  	  {
  	    fill(210,173,93);
  	    rect(686, 350, 170, 63);
  	  }
  	  
  	  
  	  //Second rectangle (Stand)
  	  fill(248, 215, 140);
  	  if (mouseX >= 686 && mouseX <= 856 && mouseY >= 505 && mouseY <= 568) 
  	  {
  	  rect(686, 505, 170, 63);
  	  boxClicked = true; 
  	  } 
  	  else
  	  {
  	   fill(210,173,93);
  	   rect(686, 505, 170, 63);
  	  }
  	  
  	  //Hit and Stand text
  	  textFont(f,36);
  	  fill(0);
  	  text("Hit",750,395);
  	  text("Stand",725,550);
  	  
  	  //Displays the number of levels played and the goal
  	  textFont(f,20);
  	  fill(232, 188, 102);
  	  text("Rounds Played: " + Rounds_Played, 150, 790); 
  	  text("Goal: $ 5000", 150, 820); 
  	  	
  	  //bet/wallet info
	  textFont(f,30);
  	  fill(255);
  	  text("$ Bet: " + stringbet,500,200);
  	  
  	//Displays "Bet Options"
  	  textFont(f,25);
	  fill(255);
	  text("(Press 1 for $100, 2 for $200, 3 for $300)",500,250);
	  
	//Displays "Total Money"
	  textFont(f,20);
	  fill(255);
	  text("Total Funds: $" + total_money, 150, 720);
	  
	  //Displays "BLACKJACK"
	  textFont(f,60);
	  fill(232, 188, 102);
	  text("B\nL\nA\nC\nK\nJ\nA\nC\nK", 20, 100); 
  	  
	  if (bet_amount>0) { //makes sure cannot continue without placing a bet
		  
		  //Displays human's info 
		  textFont(f,20);
		  fill(255);
		  text("Player Total: " + player.sum_of_cards(), 150, 660); 
		  text("Current Hand: " + player.toString(true), 150, 690); 
		  text("Total Funds: $" + total_money, 150, 720);
		 
		  //displays computer info
		  //textFont(f,20);
		  //fill(255);
		  //text("Dealer Total:" + computer.sum_of_cards(), 150, 100); 
		  //text("Dealer Hand: " + computer.toString(false), 150, 130); 

	  }
	  if (bet_amount==0){ //warning to place bet
		  textFont(f,25);
		  fill(255);
		  text("Please place bet before continuing",500,300);
		  
	  }
	  if (outofmoney==true) { //player can no longer continue without money
		
		  textFont(f,30);
		  fill(255);
		  text("OUT OF MONEY (GAME OVER!)",200,100);
		
	  }
	  if (total_money>=5000) { //goal/win screen
		  setup();
		  textFont(f,60);
		  fill(232, 188, 102);
		  text("CONGRATS YOU REACHED $5000",150,130);
		  image(youwin, 600, 400, 400, 400);
	  }
	
        
}
	
	//deals with hit/stand mouse interaction and wins/lost based after click
	public void mousePressed()
	{
	  if (bet_amount>0 && buttonpress==false) { //button has not already been touched and bet has been placed
		  //displays the word "hit"
		  if (mouseX >= 686 && mouseX <= 856 && mouseY >= 350 && mouseY <= 413) {
			  
			  minim = new Minim(this);
			  audioplayer = minim.loadFile("hits.mp3", 2048); //hit audio for button press
			  audioplayer.setGain(-10); 
			  audioplayer.play();

			  background(100,0,0);

			  player_won = !player.hand_overflow(); // If looses from the start
			  
			  // lose if over
			  if (player.hand_overflow()) {
				  player_won = false;
				  player_turn = false;

				  //Displays the winner
				  fill(179,30,60);
				  rect(150, 500, 200, 90); //x,y,l,h

				  //Displays the winner
				  textFont(f,35);
				  fill(255,255,255);
				  text("OVER 21", 175, 560); 
				  buttonpress=true; //cannot press buttons again
			  }

			  if (!player.hand_overflow()) {
				  player.pick_card(regular);
			  }

			  //Displays human's info 
			  textFont(f,20);
			  fill(255);
			  text("Player Total: " + player.sum_of_cards(), 150, 660); 
			  text("Current Hand: " + player.toString(true), 150, 690); 
			  //text("Total Funds: $" + total_money, 150, 720);
			  
			  if (player.sum_of_cards() > 21) {
				  //System.out.println("PLAYER LOST");
				  total_money -= bet_amount;

				  //Displays the winner
				  fill(179,30,60);
				  rect(150, 500, 280, 90); //x,y,l,h

				  //Displays the winner
				  textFont(f,35);
				  fill(255,255,255);
				  text("  YOU LOST  ", 175, 560); 
				  textFont(f,50);
				  fill(255);
				  text("Do you want to play again? (Press: 'R')", 150, 94); //for restart
				 
				  minim = new Minim(this);
				  audioplayer = minim.loadFile("lose.mp3", 2048); //lose audio
				  audioplayer.setGain(-20); 
				  audioplayer.play();
				  
				  buttonpress=true; //no button press
			  }


		  }

		  //displays the word "stand"
		  else if (mouseX >= 686 && mouseX <= 856 && mouseY >= 505 && mouseY <= 568) 
		  {
			  minim = new Minim(this);
			  audioplayer = minim.loadFile("stand.mp3", 2048); //stand audio for button
			  audioplayer.setGain(-20); 
			  audioplayer.play(); 
			  
			  player_turn = false;



			  while (computer.sum_of_cards() <= 16) { //computer will hit under 16 value
				  computer.pick_card(regular);
			  }


			  background(100,0,0);

			  //Display's Dealer's info
			  textFont(f,20);
			  fill(255);
			  text("Dealer Total: " + computer.sum_of_cards(), 150, 100); 
			  text("Dealer Hand: " + computer.toString(true), 150, 130); 

			  if (player.sum_of_cards() > 21) { //bust
				  //System.out.println("PLAYER LOST");
				  total_money -= bet_amount; //calculate wallet

				  //Displays the winner
				  fill(179,30,60);
				  rect(150, 500, 280, 90); //x,y,l,h

				  //Displays the winner
				  textFont(f,35);
				  fill(255,255,255);
				  text("  YOU LOST  ", 175, 560); 
				  
				  minim = new Minim(this);
				  audioplayer = minim.loadFile("lose.mp3", 2048); //lose audio
				  audioplayer.setGain(-20); 
				  audioplayer.play();
				  
				  textFont(f,50);
				  fill(255);
				  text("Do you want to play again? (Press: 'R')", 150, 65); //for restart
				  buttonpress=true; //no button press
			  }

			  else if (computer.sum_of_cards() > 21) { //computer bust
				  //System.out.println("PLAYER WON");
				  total_money += bet_amount; //wallet calculation
 
				  //Displays the winner
				  fill(19,139,67);
				  rect(150, 500, 280, 90); //x,y,l,h

				  //Displays the winner
				  textFont(f,35);
				  fill(255,255,255);
				  text("  YOU WON!  ", 175, 560); 
				  
				  minim = new Minim(this);
				  audioplayer = minim.loadFile("win.mp3", 2048); //win audio
				  audioplayer.setGain(-20); 
				  audioplayer.play();

				  textFont(f,50);
				  fill(255);
				  text("Do you want to play again? (Press: 'R')", 150, 65); //for restart
				  buttonpress=true; //no button press
			  }

			  else if ((computer.sum_of_cards() > player.sum_of_cards()) && (computer_won)) { 
				  //System.out.println("PLAYER LOST");
				  total_money -= bet_amount; //wallet calculation

				  //Displays the winner
				  fill(179,30,60);
				  rect(150, 500, 280, 90); //x,y,l,h

				  //Displays the winner
				  textFont(f,35);
				  fill(255,255,255);
				  text("  YOU LOST  ", 175, 560); 
				  
				  minim = new Minim(this);
				  audioplayer = minim.loadFile("lose.mp3", 2048); //lose audio
				  audioplayer.setGain(-20); 
				  audioplayer.play();

				  textFont(f,50);
				  fill(255);
				  text("Do you want to play again? (Press: 'R')", 150, 65); //for restart
				  buttonpress=true; //no button press
			  }
			  
			  else if ((computer.sum_of_cards() == player.sum_of_cards()) && (computer_won)) { //draw outcome
				  //System.out.println("DRAW!")
				  //no money gained or lost from wallet
				  //Displays the winner
				  fill(37, 210, 173);
				  rect(150, 500, 280, 90); //x,y,l,h

				  //Displays the winner
				  textFont(f,35);
				  fill(255,255,255);
				  text("    DRAW    ", 175, 560); 
				  
				  minim = new Minim(this);
				  audioplayer = minim.loadFile("draw.mp3", 2048); //"draw" audio
				  audioplayer.setGain(-20); 
				  audioplayer.play();
				


				  textFont(f,50);
				  fill(255);
				  text("Do you want to play again? (Press: 'R')", 150, 65); //for restart
				  buttonpress=true; //no button press
			  }
			  
			  else {
				  //System.out.println("PLAYER WON");
				  total_money += bet_amount; //wallet calculation

				  //Displays the winner
				  fill(19,139,67);
				  rect(150, 500, 280, 90); //x,y,l,h

				  //Displays the winner
				  textFont(f,35);
				  fill(255,255,255);
				  text("  YOU WON!  ", 175, 560); 
				  
				  minim = new Minim(this);
				  audioplayer = minim.loadFile("win.mp3", 2048); //win audio
				  audioplayer.setGain(-20); 
				  audioplayer.play();

				  textFont(f,50);
				  fill(255);
				  text("Do you want to play again? (Press: 'R')", 150, 65); //for restart
				  buttonpress=true; //no button press
			  }


			  //Displays the number of levels played and the goal
			  textFont(f,20);
			  fill(232, 188, 102);
			  text("Rounds Played: " + Rounds_Played, 150, 790); 
			  text("Goal: $ 5000", 150, 820); 

		  }
	  }
	  
	}
	//deals with restarting game / resetting board and bet choice
	public void keyPressed() {
		  if (key == 'r' && buttonpress == true) { //choice to restart game after betting/standing
			
			stringbet = ""; //reset bet 
			bet_amount = 0;
				    
		  	Rounds_Played += 1; //adding to rounds
		    
		    computer = new Hand(regular); //new deck
		    player = new Hand(regular);


		    player_won = true; //reset variables
		    computer_won = true;
		    buttonpress = false;

		    player_turn = true; 
		    computer_turn = true;
		    betchoice = false;
		    
		    setup(); //reset background
		  }
		  if (total_money>0 && betchoice == false) { //player has enough money to continue betting and hasnt bet yet
		  if (key == '1' && total_money>=100) { //bet 100
				
				stringbet = "100";
				bet_amount = 100;
				betchoice = true; //bet marked
			  
		  }
		  else if (key == '2' && total_money>=200) { //bet 200
				
				stringbet = "200";
				bet_amount = 200;
				betchoice=true; //bet marked
			  
		  }
		  else if (key == '3' && total_money>=300) { //bet 300
				
				stringbet = "300";
				bet_amount = 300;
				betchoice=true; //bet marked
				
			  }
		
		  }
		  if (total_money==0) { //no more money left
			  outofmoney = true; //updates for draw "out of money"
	
		  }
		  
	}
	
	//condition for mouse
	public void mouseReleased() { 
	  locked = false;
	 
	}
	 
}

