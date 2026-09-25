package Chapter_2_Linked_Lists;

import java.util.HashSet;
// Method to remove duplicates from an unsorted linked list
public class RemoveDupes {

	public void deleteDupes(LinkedListNode n) {
		
		HashSet<Integer> set=new HashSet<Integer>();
		
		LinkedListNode previous=null;
		while(n!=null) {
			if(set.contains(n.data)) {
					previous.next=n.next;
			}
			else {
				set.add(n.data);
				previous =n;
			}
			n=n.next;
		}
		
	}
}
