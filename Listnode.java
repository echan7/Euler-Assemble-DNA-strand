/**
 * The internal node structure of {@link eulerLinkedList}.
 *
 * @param <E>
 *            the generic type of the data content stored in the list
 * 
 */
public class Listnode<E> {
	private E data; // data to be stored
	private Listnode<E> next; // connection to next node
	private Listnode<E> prev; // connection to the previous node
	private boolean fake; 

	/**
	 * Constructs a new list nodes with no links to neighboring nodes.
	 * 
	 * @param data
	 *            the data to be stored in this node
	 */
	Listnode(E data) {
		this(data, null, null);
		this.fake = false;
	}
	
	public boolean getFake() {
		return fake;
	}
	
	void setFake() {
		this.fake = true;
	}

	/**
	 * Constructs a new list node with links to neighboring nodes.
	 * 
	 * @param data
	 *            the data to be stored in this node
	 * @param next
	 *            the node after this one
	 */
	Listnode(E data, Listnode<E> next, Listnode<E> prev) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	}

	/**
	 * Returns the current data.
	 * 
	 * @return the current data
	 */
	E getData() {
		return data;
	}

	/**
	 * Returns the current next node.
	 * 
	 * @return the current next node
	 */
	Listnode<E> getNext() {
		return next;
	}
	
	/**
	 * Returns the current previous node.
	 * 
	 * @return the current previous node
	 */
	Listnode<E> getPrev() {
		return prev;
	}


	/**
	 * Sets the data to the given new value.
	 * 
	 * @param data
	 *            the new data
	 */
	void setData(E data) {
		this.data = data;
	}

	/**
	 * Sets the next node to the given new value.
	 * 
	 * @param next
	 *            the new next node
	 */
	void setNext(Listnode<E> next) {
		this.next = next;
	}


	/**
	 * Sets the previous node to the given new value.
	 * 
	 * @param prev
	 *            the new next node
	 */
	void setPrev(Listnode<E> prev) {
		this.prev = prev;
	}
}