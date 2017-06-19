package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 * @author Pei-Hsuan Chu
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
    // TODO: Add a constructor
	public DictionaryLL(){
		dict = new LinkedList<String>();
	}

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	
    	if(word==null){
        	System.out.println("uuu");
			throw new NullPointerException("Word inserted cannot be empty");
    	}
    	else if(dict.contains(word.toLowerCase())){
    		return false;
    	}
    	else{
    		dict.add(word.toLowerCase());
    		//System.out.println("insert: "+word.toLowerCase());
    		return true;
    	}
    	
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODO: Implement this method
    	//System.out.println("size is : "+dict.size());
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method
    	int i = 0;
    	for(String scan: dict){
    		i+=1;
    		System.out.println("scan:"+scan+" "+i);
    		
    		if(s.toLowerCase().equals(scan))
    			return true;
    	}
        return false;
    }

    
}
