import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

public class Markov
{
    //Create hashmap
    public static Hashtable<String, Vector<String>> markovChain = new Hashtable<String, Vector<String>>();
    static Random rand = new Random();
    
    /**
     * Main method
     */
    public static void main(String[] args) throws IOException
    {
      //Create the first two entries (k: start, k: end)
      markovChain.put("start", new Vector<String>());
      markovChain.put("end", new Vector<String>());
      while(true)
      {
        //Get some words
        System.out.print("Enter your phrase > ");
        BufferedReader in = new BufferedREader (new InputStreamReader(System.in));
        String input = in.readLine() + ".";
        
        //Add the words to the hash table
        addWords(input);
      }
    }
    
    /**
     * Add the words
     */
     public static void addWords(String phase)
     {
       //put each word into an array
       String[] words = phrase.split(" ");
       
       // Loop through each word, check if it's already added
		   // if its added, then get the suffix vector and add the word
	  	 // if it hasn't been added then add the word to the list
	  	 // if its the first or last word then select the start/end key
	  	 for(int i = 0; i < words.length; i++)
	  	 {
	  	     //Add the start and end words to their own index
	  	     if(i == 0)
	  	     {
	  	        Vector<String> start = markovChain.get("start");
	  	        start.add(words[i]);
	  	        Vector<String> suffix = markovChain.get(words[i]);
	  	        if(suffix == null)
	  	        {
	  	          suffix = new Vector<String>();
	  	          suffix.add(words[i+1]);
	  	          markovChain.put(words[i], suffix);
	  	        }
	  	     }
	  	     else if(i == words.length - 1)
	  	     {
	  	       Vector<String> endWords = markovChain.get("end");
	  	       endWords.add(words[i]);
	  	     }
	  	     else
	  	     {
	  	       Vector<String> suffix = markovChain.get(words[i]);
	  	       if(suffix == null)
	  	       {
	  	         suffix = new Vector<String>();
	  	         suffix.add(words[i+1]);
	  	         markovChain.put(words[i], suffix);
	  	       }
	  	       else
	  	       {
	  	         suffix.add(words[i+1]);
	  	         markovChain.put(words[i], suffix);
	  	       }
	  	     }
	  	 }
	  	 generateSentence();
     }
     
     /**
      * Generate a random phrase based on the writer with Markov chains.
      */
    public static void generateSentence()
    {
      //Vector to hold the phrase
      Vector<String> newVector = new Vector<String>();
      //String to hold the next word
      String nextWord = "":
      //Select the first word in the sentence
      Vector<String> start = markovChain.get("start");
      int startingWordLength = start.size();
      nextWord = start.get(rand.nextInt(startingWordLength));
      newVector.add(nextWord);
      //Loops through the words until the end is reached
      while(nextWord.charAt(nextWord.length() - 1) != '.')
      {
        Vector<String> wordSelection = markovChain.get(nextWord);
        int wordSelectionLength = wordSelection.size();
        nextWord = wordSelection.get(rand.nextInt(wordSelectionLength));
        newVector.add(nextWord);
      }
      System.out.println("New phrase: " + newVector.toString());
    }
}
