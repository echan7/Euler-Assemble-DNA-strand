/**
 * A Doubly-linked linkedlist with a "dumb" header node (no data in the node),
 * with a tail node. It implements ListADT&lt;E&gt; and returns
 * eulerLinkedListIterator when requiring a iterator.
 * 
 */
public class eulerLinkedList<E> implements ListADT<E> {
	// TODO: add your fields here
	private Listnode<E> head;
	private int numItems;

	/**
	 * Constructs a empty PacketLinkedList
	 */
	public eulerLinkedList() {
		// TODO
		head = new Listnode<E>(null);
		numItems = 0;
	}
	
	public void setHead(Listnode<E> firstNode) {
		head.setNext(firstNode);
	}
	
	public void updateNum() {
		
		Listnode<E> curr = head;
		int count = 0;
		while(curr.getNext()!=null) {
			curr = curr.getNext();
			count++;
		}
		
		numItems = count;
	}

	@Override
	public void add(E item) {
		// TODO
		if(item == null){
			throw new IllegalArgumentException();
		}
		Listnode<E> newNode = new Listnode<E>(item);
		Listnode<E> curr = head;
		while(curr.getNext() != null){
			curr = curr.getNext();
		}
		curr.setNext(newNode);
		newNode.setPrev(curr);
		numItems ++;
	}

	@Override
	public void add(int pos, E item) {
		// TODO
		if(pos < 0 || pos > numItems){
			throw new IndexOutOfBoundsException();
		}
		if(item == null){
			throw new IllegalArgumentException();
		}
		Listnode<E> newNode = new Listnode<E>(item);
		Listnode<E> curr = head;
		if(pos == numItems){
			add(item);
			return;
		}
		
		for(int i=0; i<pos; i++){
			curr = curr.getNext();
		}
		newNode.setNext(curr.getNext());
		curr.getNext().setPrev(newNode);
		curr.setNext(newNode);
		newNode.setPrev(curr);
		numItems++;
	}

	@Override
	public boolean contains(E item) {
		// TODO
		Listnode<E> curr = head;
		while(curr.getNext() != null){
			if(curr.getNext().getData().equals(item)){
				return true;
			}
			curr = curr.getNext();
		}
		return false;
	}

	@Override
	public E get(int pos) {
		// TODO
		if(pos < 0 || pos >= numItems){
			throw new IndexOutOfBoundsException();
		}
		
		Listnode<E> curr = head.getNext();
		for(int i=0; i<pos;i++){
			curr = curr.getNext();
		}
		return curr.getData();
	}

	@Override
	public boolean isEmpty() {
		// TODO
		if(numItems == 0){
			return true;
		}
		return false;
	}

	@Override
	public E remove(int pos) {
		// TODO
		if(pos<0 || pos >=numItems){
			throw new IndexOutOfBoundsException();
		}
		
		Listnode<E> curr = head;
		for(int i =0; i < pos;i++){
			curr = curr.getNext();
		}
		E data = curr.getNext().getData();
		
		curr.setNext(curr.getNext().getNext());
		numItems--;
		return data;
	}

	@Override
	public int size() {
		// TODO
		return numItems;
	}

	@Override
	public eulerLinkedListIterator<E> iterator() {
		// TODO
		return new eulerLinkedListIterator<E>(head.getNext());
	}

}