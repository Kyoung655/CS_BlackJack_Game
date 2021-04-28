PImage gold_plate;
PImage back_of_card;
PImage random_card;

// total number of images
int num = 10;
int numImages = 3;
// an array of images
PImage[] images = new PImage[num];

PFont f;

boolean boxClicked = false;
boolean locked = false;


int randomNumber;

void setup()
{
  //creates the size of the screen and makes the background dark red
  size(900,900);
  background(100,0,0);
  noStroke();

  //loads the gold plate image and sets the font
  gold_plate = loadImage("gold_plate.jpg");
  back_of_card = loadImage("back_of_card.png");
  
  //error
  for (int i = 0; i < numImages; i++)
  {
    images[i] = loadImage((i) + ".png");
  }
  
  
  
  random_card = loadImage("");
  f = createFont("Arial",16,true);
  
}





//creates lines by following the mouse
void draw()
{
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
  
  
  
  //Display's Dealer's info
  textFont(f,20);
  fill(255);
  text("Player: Dealer", 150, 100); 
  text("Current Hand Amount: ", 150, 130); 
  
  
  
  //Displays the bet
  textFont(f,35);
  fill(2232, 188, 102);
  text("Bet: $", 150, 430); 
  
  
  
  //Displays human's info 
  textFont(f,20);
  fill(255);
  text("Player: ", 150, 660); 
  text("Current Hand Amount: ", 150, 690); 
  text("Total Funds: $", 150, 720); 
   
   
   
  //Displays the number of levels played and the goal
  textFont(f,20);
  fill(232, 188, 102);
  text("Number of Levels Played: ", 150, 790); 
  text("Goal: $", 150, 820); 
  
  //Displays "BLACKJACK"
  textFont(f,60);
  fill(232, 188, 102);
  text("B\nL\nA\nC\nK\nJ\nA\nC\nK", 20, 100); 

  
}



void mousePressed()
{
  //stroke(random(0,255), random(0,255), random(0,255)); --> for reference
  
  //displays the word "hit"
  if (mouseX >= 686 && mouseX <= 856 && mouseY >= 350 && mouseY <= 413)
  {
    text("Hit", random(0,900),random(0,900));
    randomNumber = (int)random(numImages);
    
  }
  
  //displays the word "stand"
  else if (mouseX >= 686 && mouseX <= 856 && mouseY >= 505 && mouseY <= 568) 
  { 
    text("Stand", random(0,900),random(0,900));
  }

  
  

}



void mouseReleased() {
  locked = false;
}
