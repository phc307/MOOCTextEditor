package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;

	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		// TODO: Implement this method
		if (element == null){
			throw new NullPointerException("Last object cannot store null pointers.");
			//return false;
		}

		LLNode<E> n = new LLNode<E>(element);
		//System.out.println(element);
		n.next  = tail;
		n.next.prev.next = n;
		n.prev = n.next.prev;
		n.next.prev = n;

		size+=1;
		return true;
		
		
		
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(this.size == 0){
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
		else{
			if(index > this.size || index < 0){
				throw new IndexOutOfBoundsException("Index is out of bounds");
			}
			
			
			LLNode<E> goal_node = head;
			for(int i=0; i<=index; i++){
				goal_node = goal_node.next;
			}
			
			return goal_node.data;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (element == null){
			throw new NullPointerException("Last object cannot store null pointers.");
			//return false;
		}
		if(index > this.size || index < 0){
			throw new IndexOutOfBoundsException("Index is out of bounds~");
		}
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> pointNode = head;
		for(int i=0; i<=index; i++){
			pointNode = pointNode.next;
		}
		
		newNode.next  = pointNode;
		newNode.next.prev.next = newNode;
		newNode.prev = newNode.next.prev;
		newNode.next.prev = newNode;
		size += 1;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		
		// TODO: Implement this method
		if(this.size() <=0){
			throw new IndexOutOfBoundsException("Index is out of bounds~");
		}
		else{
			if(index >= this.size || index < 0){
				throw new IndexOutOfBoundsException("Index is out of bounds~");
			}
			LLNode<E> pointNode = head;
			for(int i=0; i<=index; i++){
				pointNode = pointNode.next;
			}
			
			pointNode.prev.next = pointNode.next;
			pointNode.next.prev = pointNode.prev;
			pointNode.prev = null;
			pointNode.next = null;
			size-=1;
			return pointNode.data;
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (element == null){
			throw new NullPointerException("The element cannot be null.");
		}
		if(index >= this.size || index < 0){
			throw new IndexOutOfBoundsException("Index is out of bounds yo");
		}
		// TODO: Implement this method
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> pointNode = head;
		for(int i=0; i<=index; i++){
			pointNode = pointNode.next;
		}
		E beplaced = pointNode.data;
		newNode.next = pointNode.next;
		pointNode.next.prev = newNode;
		newNode.prev = pointNode.prev;
		pointNode.prev.next = newNode; 
		pointNode.next = null;
		pointNode.prev = null;
	
		return beplaced;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
