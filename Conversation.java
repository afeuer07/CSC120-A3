//as of 2/13 2pm:
//the transcript printing works 100%
//the loop runs the correct numeber of times
//the canned responses are random and printed correctly
//need to:
//add a respond method to find mirror words and decide when to use them vs random

import java.util.ArrayList;
import java.util.Scanner; 
import java.util.Random;

class Conversation implements Chatbot {

  String input; //string to store user input
  ArrayList<String> transcript = new ArrayList<String>(); //array list to store conversation transcript 
  ArrayList<String> randRespond = new ArrayList<String>(); //array list to store canned responses
  
  //Constructor 
  public Conversation() {
    //add canned responses to array list
    randRespond.add("I see.");
    randRespond.add("Hmm...");
    randRespond.add("That's so cool!");
    randRespond.add("Really?");
    randRespond.add("Oh, I didn't know that.");
    randRespond.add("Thanks for telling me.");
    randRespond.add("No way!");
    randRespond.add("That's interesting.");
    randRespond.add("I'm not sure what to say.");
    randRespond.add("Mm-hmm.");
  }

  //Starts and runs the conversation with the user
  public void chat() {
    Scanner in = new Scanner(System.in); // Create a Scanner object
    Random rand = new Random(); //create random object
    
    System.out.println("Hello! How many rounds of conversation would you like to have?");
    int rounds = in.nextInt(); //define rounds as user input
    in.nextLine(); //throw out the newline character
    
    System.out.println("Great! What would you like to talk about?");

    //for loop that runs the number of rounds of conversation
    for (int i = 0; i < rounds; i++) {
      input = in.nextLine(); //take in user input as string
      //in.nextLine();
      transcript.add(input); //add user input to transcript
      String randRes = randRespond.get(rand.nextInt(randRespond.size())); // Get a random response
      System.out.println(randRes);
      transcript.add(randRes); // Add to transcript
    }
    System.out.println("Well, thanks for chatting!\n\nHere's a transcript of our conversation:");
    in.close(); //close scanner
  }

  //Prints transcript of conversation
  public void printTranscript() {
    for (int i = 0; i < transcript.size(); i++) {
      System.out.println(transcript.get(i));
    }
    System.out.println("Well, thanks for chatting!");
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String returnString = ""; 
    return returnString; 
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
