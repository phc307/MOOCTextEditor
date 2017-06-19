package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 * @author Pei-Hsuan Chu
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		
		String prevWord = "";
		String[] textArray = sourceText.split(" +");
		starter = textArray[0];	
		prevWord = starter;	
		int positionSameWord = 0;
		int noFlag = 0;

		for(int i=1; i<textArray.length; i++){
			int yesFlag = 0;
			ListNode preWordNode = new ListNode(prevWord);
			String w = textArray[i];
			//cannot revise the wordList while iterating, so just set the flags
			for (ListNode scan:wordList){
				if(scan.getWord().equals(preWordNode.getWord())){
					positionSameWord = wordList.indexOf(scan);
					yesFlag+=1;
				}
				else{
					noFlag+=1;
				}					
			}
			//so I revice the wordList here
			if(yesFlag == 1){
				wordList.get(positionSameWord).addNextWord(w);	
			}
			else if(yesFlag > 1){
				System.out.println("Something wrong here!");
			}
			else{
				ListNode creatAnode = new ListNode(prevWord);
				wordList.add(creatAnode);
				creatAnode.addNextWord(w);
			}	
			prevWord = w;
		}
		ListNode LastNode = new ListNode(textArray[textArray.length-1]);
		int check = 0;
		int i = 0;
		for(i=0; i<wordList.size(); i++){
			if(wordList.get(i).getWord().equals(textArray[textArray.length-1])){
				check +=1;
				positionSameWord = i;
			}
		}
		if(check==0){
			wordList.add(LastNode);
			LastNode.addNextWord(textArray[0]);
		}
		else{
			wordList.get(positionSameWord).addNextWord(starter);
		}

//		for(ListNode trytry:wordList){
//			System.out.println(trytry.toString());
//		}

	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		String currWord = starter;
		String output = "";
		String w = "";
		output = output+currWord;
		int outputSize = 1;
		if(numWords==0){
			return "";
		}
		while(outputSize < numWords){
			for(ListNode scan:wordList){
				if(scan.getWord().equals(currWord)){
					w = scan.getRandomNextWord(rnGenerator);
				}
			}
			output = output+" "+w;
			outputSize+=1;
			currWord = w;		
		}
		return output;
		
		
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		int len = wordList.size();
		for(int i=0 ; i<len ; i++){
			wordList.remove(0);
		}
		this.train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString0 = "Hello. Hahaha there. Pegggy";
		//String textString1 = "Hello. Hahaha there. Pegggy";
		//String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		//System.out.println(textString);
		gen.train(textString0);
		//gen.train(textString1);
		//System.out.print(gen.wordList.get(0).getWord());
		//System.out.print(gen.wordList.size());
		System.out.println(gen);
		//System.out.println(gen.generateText(25));
		System.out.println("--------------");
		
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		
		//System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		//System.out.println(gen.generateText(0));
		
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int NumOfNext=0;
		NumOfNext = this.nextWords.size();
		int ran = generator.nextInt(Integer.MAX_VALUE);
		int choose = ran % NumOfNext;
		String ChosenWord = this.nextWords.get(choose);
	    return ChosenWord;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


