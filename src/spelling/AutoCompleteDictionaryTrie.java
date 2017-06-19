package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Pei-Hsuan Chu
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    private TrieNode curr = new TrieNode();
    private LinkedList<TrieNode> isWordList = new LinkedList<>();
    private List<String> completeWord = new LinkedList<>();
    private int count=0;
    private int flagEmpty=0;
    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();	
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		
		curr = root;
		// check if input is null
		if(word.length()==0){
			throw new NullPointerException("Word inserted cannot be empty");
		}
		else{
			char[] newWordChar = word.toLowerCase().toCharArray();
			for(int i=0; i<newWordChar.length;i++){
				TrieNode nextNode = new TrieNode();

				nextNode = curr.insert(newWordChar[i]);
				// insert method in "TrieNode.java"
				if(nextNode!=null){
					curr = nextNode;
				}
				else{
					curr = curr.getChild(newWordChar[i]);	
				}
			}
			// set the isWord flag here, and add the isWord==true TrieNode into a LinkedList
			// to calculate the size and check if it is a word 
			if(curr.getText().equals(word.toLowerCase())){
				int flag=0;
				curr.setEndsWord(true);
				//make sure not to add word into isWordList repeatedly, so I set a flag
				for(TrieNode scan: isWordList){
					
					if(curr.getText()==scan.getText()){
						flag=1;
						return false;
					}
				}
				if(flag==0){
					isWordList.add(curr);
				}			
			}
			else{
				curr.setEndsWord(false);
			}
			return true;
		}
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
		this.size = isWordList.size();
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		
		for(TrieNode scan:isWordList){
			if(scan.getText().equals(s.toLowerCase())){
				return true;
			}
		}
		return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 curr = root;
    	 System.out.println("prefix : "+prefix);
    	 List<String> finallist = new LinkedList<>();
    	 List<String> temp = new LinkedList<>();
    	 if(prefix.length()==0){
    		 System.out.println("prefix.length()==0");
    		 temp = findword(root);
    	 }
    	 else{
    		 System.out.println("prefix.length()!=0");
    		 TrieNode stemNode = findstem(root,prefix);
    		 if(flagEmpty==1){
    			 System.out.println("completeWord.size()==0");
    			 flagEmpty=0;
    			 return completeWord;
    		 }
    		 System.out.println("StemNode: "+stemNode.getText());
    		 temp = findword(stemNode);
    		 
    		 
    		 
    		 

    	 }
    	 
		 if(temp.size()<numCompletions){
			 finallist=new LinkedList<String>(temp);
			 
		 }
		 else{
    		 for(int i=0; i<numCompletions; i++){ 
    	    		//System.out.println(i);
    				finallist.add(temp.get(i));
    				//System.out.println(temp.get(i));
    	    }
		 }
		 
		 
    	int s = completeWord.size();
    	completeWord.clear();
    	 System.out.println("finallist size : "+finallist.size());
		 return finallist;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
 	public List<String> findword(TrieNode input){
 		
 		TrieNode curr = new TrieNode();
 		curr = input;
 		
 		if (curr == null) {
 			System.out.println("curr == null");
 			return null;
 		}
 		// add curr String to LinkedList completeWord	
 		else{
	 		if(curr.endsWord()==true){
	 			if(!completeWord.contains(curr.getText())){
					completeWord.add(curr.getText());
					//System.out.println("1+"+curr.getText());
	 			}		
			}
	 		TrieNode next = null;
	 		for (Character c : curr.getValidNextCharacters()) {			
	 			next = curr.getChild(c);
	 			findword(next);
	 		}
	 		//sort by string length
	 		completeWord.sort(Comparator.comparing(String::length));
	 		//System.out.println("complete word size:"+completeWord.size());
 		}
 		
 		return completeWord;
 	}
 	
 	
 	
 	
 	public TrieNode findstem(TrieNode input,String prefix){
	TrieNode curr = input;
 		if (curr == null) {
 			System.out.println("curr == null");
 			return null;
 		}
 		char[] prechar = prefix.toLowerCase().toCharArray();			
 		for(int i=0; i<prefix.length(); i++){
 			//System.out.println("i = "+i+" prechar[i] =  "+prechar[i]);
 			if(curr.getValidNextCharacters().contains(prechar[i])){
 				curr = curr.getChild(prechar[i]);
 			}
 			else{
 				flagEmpty=1;
 				return null;
 			}
 		}
 		
 		return curr;
 		
 	}
 	
	
}