package chapter_2_linked_lists;

//helper method to print List
public class Main {
	 static void printList(LinkedListNode head) {
	while(head!=null) {
		System.out.println(head.data+" ->");
		head=head.next;
	}
	System.out.println("null");
	}
// Create linked list: 1 -> 2 -> 3 -> 2 -> 1 -> 4 
public static void main(String[] args) {
	

	LinkedListNode head=new LinkedListNode(1);
	
	head.next=new LinkedListNode(2);
	
	head.next.next=new LinkedListNode(3);
	
	head.next.next.next=new LinkedListNode(2);
	
	head.next.next.next.next=new LinkedListNode(1);
	
	head.next.next.next.next.next=new LinkedListNode(4);
	
	System.out.println("Before removing Duplicates");
	
	printList(head);
	
	RemoveDupes res=new RemoveDupes();
	
	res.deleteDupes(head);
	
	System.out.println("After Removing Duplicates");
	printList(head);
	
}

}
