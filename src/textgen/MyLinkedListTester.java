/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
/*
	private Object testGet;
	private Object testAddEnd;
	private Object testAddAtIndex;
	private Object testSize;
	private Object testRemove;
	private Object testSet;
	*/
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//System.out.println("testget ");
		//test empty list, get should throw an exception
		try {
			
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}

		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		//System.out.println("testremove ");
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		// check list1 removal out of bound
		try {
			list1.remove(list1.size());
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		//check emptyList

		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		//check shortList first element ,then out of bounds
		String b = shortList.remove(0);
		assertEquals("Remove: check b is correct ", "A", b);
		assertEquals("Remove: check element 0 is correct ", "B", shortList.get(0));
		assertEquals("Remove: check size is correct ", 1, shortList.size());

		try {
			shortList.remove(shortList.size());
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}


		//check longerList first element ,then out of bounds
		for (int i=0; i<LONG_LIST_LENGTH; i++){
			int c = longerList.remove(0);
			assertEquals("Remove: check c is correct ", i, c);
			//assertEquals("Remove: check element 0 is correct ", (Integer)(i+1), longerList.get(0));
			assertEquals("Remove: check size is correct ", 9-(Integer)i, longerList.size());
		}
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		//System.out.println("testaddend ");
        // TODO: implement this test
		//check list1 size, the last element
		boolean successful_added = list1.add(99);
		int lsize = list1.size();
		assertEquals("AddEnd : check if successfully added ", true, successful_added);
		assertEquals("AddEnd : check the size of list1", 4,list1.size());
		assertEquals("AddEnd : check the last element of list1", (Integer)99, list1.get(lsize-1));
		try{
			list1.add(null);
			fail("Element cannot be null");
		}
		catch(NullPointerException e){
		}
	
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		//System.out.println("testsize ");
		// TODO: implement this test
		assertEquals("Size: check list1 size is correct ", 3, list1.size());
		assertEquals("Size: check emptyList size is correct ", 0, emptyList.size());
		assertEquals("Size: check shortList size is correct ", 2, shortList.size());
		assertEquals("Size: check longerList size is correct ", 10, longerList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		//System.out.println("testaddindex ");
        // TODO: implement this test
		//check list1 size, the last element
		list1.add(2,99);
		assertEquals("Add : check the element just added ", (Integer)99, list1.get(2));
		assertEquals("Add : check the size of list1", 4,list1.size());
		assertEquals("Add : check the next element of which just added", (Integer)42,list1.get(3));
		try{
			list1.add(0,null);
			fail("Element cannot be null");
		}
		catch(NullPointerException e){
		}

	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		//System.out.println("testset ");
	    // TODO: implement this test
		int bereplaced = list1.set(2,77);
		assertEquals("Set : check the next element just be replaced", 42,bereplaced);
		assertEquals("Set : check the element just added ", (Integer)77, list1.get(2));
		assertEquals("Set : check the size of list1", 3,list1.size());
		try{
			list1.set(0,null);
			fail("Element cannot be null");
		}
		catch(NullPointerException e){
		}
		
	    
	}
	
	
	// TODO: Optionally add more test methods.
	

}
