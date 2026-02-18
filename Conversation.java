// You should **not** update any call signatures in this file
// only modify the body of each function
class Conversation implements ConversationRequirements {

  // Attributes

  private java.util.Scanner scanner;
  private java.util.Random random;

  private int rounds;

  private String[] transcript;
  private int transcriptIndex;

  private static String[] cannedResponses = {
    "Mmm-hm.",
    "Tell me more.",
    "I see.",
    "Interesting."
  };

  private String[][] mirrorWords = {
    {"i", "you"},
    {"me", "you"},
    {"am", "are"},
    {"you", "i"},
    {"my", "your"},
    {"your", "my"}
  };


  /**
   * Constructor
   */
  Conversation() {

    scanner = new java.util.Scanner(System.in);
    random = new java.util.Random();

    transcriptIndex = 0;
  }


  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {

    // ask rounds
    System.out.print("How many rounds?  ");
    rounds = scanner.nextInt();
    scanner.nextLine();

    // create transcript array
    transcript = new String[2 * rounds + 2];

    // greeting
    String greeting = "Hi there!  What's on your mind?";
    System.out.println(greeting);

    transcript[transcriptIndex] = greeting;
    transcriptIndex++;


    // conversation loop
    for (int i = 0; i < rounds; i++) {

      String userInput = scanner.nextLine();

      transcript[transcriptIndex] = userInput;
      transcriptIndex++;

      String response = respond(userInput);

      System.out.println(response);

      transcript[transcriptIndex] = response;
      transcriptIndex++;
    }


    // goodbye
    String goodbye = "See ya!";
    System.out.println(goodbye);

    transcript[transcriptIndex] = goodbye;
    transcriptIndex++;
  }


  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {

    System.out.println();
    System.out.println("TRANSCRIPT:");

    for (int i = 0; i < transcriptIndex; i++) {

      System.out.println(transcript[i]);
    }
  }


  /**
   * Gives appropriate response
   */
  public String respond(String inputString) {

    String lower = inputString.toLowerCase();

    String[] words = lower.split(" ");

    boolean foundMirror = false;


    // check mirror words
    for (int i = 0; i < words.length; i++) {

      for (int j = 0; j < mirrorWords.length; j++) {

        if (words[i].equals(mirrorWords[j][0])) {

          foundMirror = true;
        }
      }
    }


    // if mirror found
    if (foundMirror) {

      String[] newWords = new String[words.length];

      for (int i = 0; i < words.length; i++) {

        boolean replaced = false;

        for (int j = 0; j < mirrorWords.length; j++) {

          if (words[i].equals(mirrorWords[j][0])) {

            newWords[i] = mirrorWords[j][1];
            replaced = true;
          }
        }

        if (replaced == false) {

          newWords[i] = words[i];
        }
      }

      String result = String.join(" ", newWords);

      return result + "?";
    }


    // otherwise canned response
    int index = random.nextInt(cannedResponses.length);

    return cannedResponses[index];
  }


  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();

    myConversation.chat();

    myConversation.printTranscript();
  }
}