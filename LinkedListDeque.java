public class LinkedListDeque<T> {
	private Node back;
	private int size;
	private Node front;

	public class Node {
		public Node pre;
		public T item;
		public Node next;

		public Node(Node p, T i, Node n) {
			pre = p;
			item = i;
			next = n;
		}
	}
	//create empty linked list
	public LinkedListDeque() { 
		size = 0;
		front = new Node(null, null, null);// front = null, front = curr, back = next
		back = new Node(null, null, null);
		front.next = back;  // ? circular & have back point to back
		//back.pre = front;
	}

	public void addFirst(T x) {
		Node oldFront_Next = front.next;
		Node TempNode = new Node(null, x, oldFront_Next);
		oldFront_Next.pre = TempNode;
		front.next = TempNode;
		size += 1;
	}

	public void addLast(T x) {
		Node Temp = back.pre;
		Node newNode = new Node(null, x, null);
		back.pre = newNode;
		Temp.next = newNode;
		size += 1;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	// print all items from first to last, separated by a space
	public void printDeque() {
		if(front.next == null){
			return;
		}
		Node p = front.next;
		while(p != null) {
			System.out.print(p.item + " ");
			p = p.next;
		}
	}

	public T removeFirst() {
		size -= 1;
		if(size == 0){
			return null;
		} else {
			T temp = front.next.item;
			front.next = front.next.next;
			//front.next.pre = front;
			return temp;
		} 
	}

	public T removeLast() {
		size -= 1;
		if(size == 0){
			return null;
		} else {
			T temp = back.pre.item;
			back.pre = back.pre.pre;
			//back.pre.next = back;
			return temp;
		}
	}
	public T get(int index) {
		if(size == 0){
			return null;
		}
		if(index == 0){
			return front.next.item;
		}
		Node ptr = front.next;
		while(index != 0 && ptr != null){
			ptr = ptr.next;
			index -= 1;
		}
		return ptr.item;
	}

	// get recursive, need some work here!
	public T getRecursiveHelper(Node A, int index) {
		if(size == 0){
			return null;
		} else if(index == 0){
			return A.item;
		} else {
			index -= 1;
			return getRecursiveHelper(A.next, index - 1);
		}
	}
	public T getRecursive(int index){
		 return getRecursiveHelper(front.next, index);
	}
}







