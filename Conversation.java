//respond method still has some issues with case and getting the second word and some edge scenarios but mostly workds

import java.util.ArrayList;
import java.util.Scanner; 
import java.util.Random;

//Class that implements Chatbot interface to have a conversation with the user
class Conversation implements Chatbot {

  String input; //string to store user input
  String randRes = ""; //string to store random response
  String mirrorRes = ""; //string to store mirrored response
  String response = ""; //string to store response
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

  //Runs the conversation with the user
  public void chat() {
    Scanner in = new Scanner(System.in); // Create a Scanner object
    
    System.out.println("Hello! How many rounds of conversation would you like to have?");

    if(!in.hasNextInt()){ //check if input is an integer
      System.out.println("Please enter a valid number of rounds.");
      return;
    }
    int rounds = in.nextInt(); //define rounds as user input
    in.nextLine(); //throw out the newline character
    
    System.out.println("Great! What would you like to talk about?");

    //for loop that runs the specified # of rounds of convo
    for (int i = 0; i < rounds; i++) {
      input = in.nextLine(); //take in user input as string
      transcript.add(input); //add user input to transcript
      response = respond(input); //get response from respond method
      transcript.add(response); // Add to transcript
      System.out.println(response); //do i need to print the response here?
    }

    System.out.println("Well, thanks for chatting!\n\nHere's a transcript of our conversation:\nWhat would you like to talk about?");
    
    printTranscript(); //call print transcript of conversation

    in.close(); //close scanner
  }

  //Prints transcript of conversation
  public void printTranscript() {
     
    for (int i = 0; i < transcript.size(); i++) {
      System.out.println(transcript.get(i));
    }
      
    System.out.println("Well, thanks for chatting!\nGoodbye!"); //includes goodbye message in transcript print and says bye
  }

  
  //Gives appropriate response (mirrored or canned) to user input
  public String respond(String inputString) {
    Random rand = new Random(); //create Random object
    String tempRes = ""; //string to store response
    boolean foundMirror = false; //boolean to check if mirror words found
    String[][] mirrorWords = {{"i ", "you "}, {"you","I"},
                              {"me", "you"}, //{"you", "me"},
                              {"myself ", "yourself "}, {"yourself ", "myself "},
                              {"my ", "your "}, {"your ", "my "},
                              {"mine ", "yours "}, {"yours ", "mine "}, 
                              {"i'd ","you'd "},//{"you'd ","I'd "},
                              {"i've ","you've "},//{"you've ","I've "},
                              {"i'll ","you'll "},//{"you'll ","I'll "},
                              {"i'm ","you're "},//{"you're ","I'm "},
                              {"am ","are "},{"are ","am "},
    };
    
    //Make entire input lowercase so all mirror words trigger
    inputString = inputString.toLowerCase();

    //check input for mirror words and punctuation, replace as appropriate
    for (int i = 0; i < mirrorWords.length; i++) {
      if (inputString.contains(mirrorWords[i][0])) {
        inputString = inputString.replace(mirrorWords[i][0], mirrorWords[i][1]); //replace mirror word with paired word
        foundMirror = true; //mark that a mirror word was found
      } 
    }
    //check if input ends with punctuation, if so replace with "?", if not just add "?"
    if (inputString.endsWith("!")||inputString.endsWith("?")||inputString.endsWith(".")){
      inputString = (inputString.substring(0, inputString.length()-1)+"?"); 
    }else{
      inputString += "?"; 
    }
    
    tempRes = inputString; //set response to mirrored input

    //check if response is empty (aka no mirror words found), if so rand response
    if (!foundMirror) {
      tempRes = randRespond.get(rand.nextInt(randRespond.size())); // Get a random response
    }
    
    //capitalize first letter and return
    tempRes = tempRes.substring(0,1).toUpperCase() + tempRes.substring(1);  
    return tempRes;
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();

  }
}
