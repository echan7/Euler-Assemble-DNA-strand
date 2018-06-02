import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;


public class euler_assemble {
	public static void main (String args[]) {
	/*	ArrayList<String> arr = null;
		try {
			arr = loadkMersFromFile(args[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Node> nodeArr = new ArrayList<Node>();
		for(String s: arr) {
			String pre = getPrefix(s);
			String suf = getSuffix(s);
			
			int preIndex = containsNode(pre, nodeArr);
			int sufIndex = containsNode(suf, nodeArr);
			Node preNode;
			Node sufNode;
			if(preIndex == -1) {
				preNode = new Node(pre);
				nodeArr.add(preNode);
			} else {
				preNode = nodeArr.get(preIndex);
			}
			
			if(sufIndex == -1) {
				sufNode = new Node(suf);
				nodeArr.add(sufNode);
			} else {
				sufNode = nodeArr.get(sufIndex);
			}
			preNode.children.add(sufNode);
			preNode.visited.add(false);
			sufNode.parent.add(preNode);
		}
		Collections.sort(nodeArr, new lexiComparator());
		if(!isBalanced(nodeArr)) {
			operationFake(nodeArr);
		}
		eulerLinkedList<Node> list = findEulCyc(nodeArr);
		list = checkForFake(list);
		eulerLinkedListIterator<Node> itr = list.iterator();
		StringBuilder finalString = new StringBuilder();
		finalString.append(itr.next().val);
		while(itr.hasNext()){
			Node curr = itr.next();
			finalString.append(curr.val.charAt(curr.val.length()-1));
		}
		
		System.out.println(finalString.toString()); */
		
		File fileToRead = new File(args[0]);
		File fileToRead2 = new File(args[1]);
		String a = "";
		String b = "";
		 try(Scanner fileScanner = new Scanner(fileToRead)){
			  while(fileScanner.hasNextLine()){
				  a+=fileScanner.nextLine();
			  }
		  } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 try(Scanner fileScanner2 = new Scanner(fileToRead2)){
			  while(fileScanner2.hasNextLine()){
				  b+=fileScanner2.nextLine();
			  }
		  } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 System.out.println(a.length());
		 System.out.println(b.length());
		 List<Integer> diffIndex = findDiffIndexes(a, b);
		 for(int i =0; i < diffIndex.size(); i ++) {
			 System.out.print(diffIndex.get(i) + 1 + " ");
		 }
		 System.out.println();
		 for(int i =0; i < diffIndex.size(); i ++) {
			 System.out.print(a.charAt(diffIndex.get(i)) + " ");
		 }
		 System.out.println();
		 for(int i =0; i < diffIndex.size(); i ++) {
			 System.out.print(b.charAt(diffIndex.get(i)) + " ");
		 }
		 
		 List<String> strings = new ArrayList<String>();
		 for(int i =0; i < b.length(); i +=3){
			 strings.add(b.substring(i, i+3));
			 
		 }
		 
		 for(int i =0; i < strings.size(); i ++){
			 System.out.println(strings.get(i));
		 }
		 System.out.println(strings.size());
		 
		 List<String> strings2 = new ArrayList<String>();
		 for(int i =0; i < a.length(); i +=3){
			 strings2.add(a.substring(i, i+3));
			 
		 }
		 
		 for(int i =0; i < strings2.size(); i ++){
			 System.out.println(strings2.get(i));
		 }
		 System.out.println(strings2.size());
		 
		 System.out.println();
		 System.out.println(strings.get(1092/3));
		 System.out.println(strings2.get(1092/3));
		 
		 System.out.println();
		 System.out.println(strings.get(9024/3));
		 System.out.println(strings2.get(9024/3));
		 

	}
	
	public static List<Integer> findDiffIndexes(String s1, String s2 ) {
	    List<Integer> indexes = new ArrayList<Integer>();
	    for( int i = 0; i < s1.length() && i < s2.length(); i++ ) {
	        if( s1.charAt(i) != s2.charAt(i) ) {
	            indexes.add( i );
	        }
	    }
	    return indexes;
	}
	
	public static eulerLinkedList<Node> checkForFake(eulerLinkedList<Node> list) {
		eulerLinkedListIterator<Node> itr = list.iterator();
		while(itr.hasNext()) {
			Listnode<Node> currLN = itr.getListNode();
			if(currLN.getFake() == true) {
				Listnode<Node> prevLN = currLN.getPrev();
				prevLN.setNext(null);
				while(prevLN.getPrev().getData()!= null) {
					prevLN = prevLN.getPrev();
				}
				
				eulerLinkedList<Node> subList1 = new eulerLinkedList<Node>();
				subList1.setHead(prevLN);
				
				currLN.setPrev(null);
				list.setHead(currLN);
				
				eulerLinkedListIterator<Node> startListItr = list.iterator();
				eulerLinkedListIterator<Node> subListItr = subList1.iterator();
				Listnode<Node> listLastLN = startListItr.getLastListNode();
				Listnode<Node> subListFirstLN = subListItr.getListNode();
				listLastLN.setNext(subListFirstLN.getNext());
				if(subListFirstLN.getNext() != null) {
					subListFirstLN.getNext().setPrev(listLastLN);
				}
				break;
			}
			itr.next();
		}
		list.updateNum();
		return list;
	}
	
	public static void operationFake(ArrayList<Node> nodeArr) {
		Node startNode = null;
		Node endNode = null;
		for(int i =0; i < nodeArr.size(); i ++) {
			if(nodeArr.get(i).parent.size() - nodeArr.get(i).children.size() == -1) {
				startNode = nodeArr.get(i);
			} else if(nodeArr.get(i).parent.size() - nodeArr.get(i).children.size() == 1) {
				endNode = nodeArr.get(i);
			}
		}
		
		startNode.parent.add(endNode);
		if(endNode.children.contains(startNode)) {
			int index = -1;
			for(int i =0; i < endNode.children.size(); i ++) {
				if(endNode.children.get(i) == startNode) {
					index = i;
				}
			}
			endNode.children.add(index, startNode);
			endNode.visited.add(index, false);
			endNode.fakeIndex = index;
		} else {
			endNode.children.add(startNode);
			endNode.visited.add(false);
			endNode.fakeIndex = endNode.children.size()-1;
		}
	}
	
	// Helper function to determine whether node already exist
	 public static int containsNode(String s, ArrayList<Node> arr) {
		 for(int i = 0; i < arr.size(); i ++) {
			 if(arr.get(i).val.equals(s)){
				 return i;
			 }
		 }
		return -1;
	 }
	 
	 public static boolean isBalanced(ArrayList<Node> nodeArr) {
		 for(Node n:nodeArr) {
			 if(Math.abs(n.children.size() - n.parent.size()) != 0){
				 return false;
			 }
		 }
		 return true;
	 }
	
	 public static ArrayList<String> loadkMersFromFile(String filepath) throws FileNotFoundException {
		 // TODO
	  File fileToRead = new File(filepath);
	  ArrayList<String> kMers = new ArrayList<String>();
	  try(Scanner fileScanner = new Scanner(fileToRead)){
		  while(fileScanner.hasNextLine()){
			 kMers.add(fileScanner.nextLine());
		  }
	  }
	  return kMers;
	 }
	 
	 public static String getPrefix(String s) {
		 StringBuilder pre = new StringBuilder(s);
		 
		 return pre.substring(0, s.length()-1);
	 }
	 
	 public static String getSuffix(String s) {
		 StringBuilder suf = new StringBuilder(s);
		 
		 return suf.substring(1);
	 }
	 
	 public static eulerLinkedList<Node> findEulCyc(ArrayList<Node> nodeArr) {
		 eulerLinkedList<Node> cycle = new eulerLinkedList<Node>();
		 Node startNode = null;
		 Node currNode = null;
		 while(!hasEulerian(nodeArr)) {
			 if(cycle.isEmpty()) {
				 startNode = nodeArr.get(0);
				 currNode = startNode;
				 cycle.add(startNode);
				 while(currNode.hasUnvisitedNode()){
					 int nextNodeIndex = currNode.nextUnvisitedNode();
					 Node nextNode = currNode.children.get(nextNodeIndex);
					 cycle.add(nextNode);
					 if(nextNodeIndex == currNode.fakeIndex) {
						eulerLinkedListIterator<Node> fItr = cycle.iterator();
						fItr.getLastListNode().setFake();
					 }
					 currNode.visited.set(nextNodeIndex, true);
					 if(nextNode == startNode) {
						 break;
					 }
					 currNode = nextNode;
				 }
			 } else {
				 eulerLinkedListIterator<Node> itr = cycle.iterator();
				 while(itr.hasNext()) {
					 currNode = itr.next();
					 startNode = currNode;
					 if(currNode.hasUnvisitedNode()) {
						 break;
					 }
				 }
				 eulerLinkedList<Node> newCycle = new eulerLinkedList<Node>();
				 newCycle.add(startNode);
				 while(currNode.hasUnvisitedNode()) {
					 int nextNodeIndex = currNode.nextUnvisitedNode();
					 Node nextNode = currNode.children.get(nextNodeIndex);
					 newCycle.add(nextNode);
					 if(nextNodeIndex == currNode.fakeIndex) {
							eulerLinkedListIterator<Node> fItr = newCycle.iterator();
							fItr.getLastListNode().setFake();
					 }
					 currNode.visited.set(nextNodeIndex, true);
					 if(nextNode == startNode) {
						 itr = cycle.iterator();
						 Node newCycleFirstNode = newCycle.get(0);
						 while(itr.hasNext()) {
							 eulerLinkedListIterator<Node> itr2 = newCycle.iterator();
							 Listnode<Node> ogCycleLN = itr.getListNode();
							 Listnode<Node> newCycleFirstLN = itr2.getListNode();
							 Listnode<Node> newCycleLastLN = itr2.getLastListNode();
							 Node curr = itr.next();
							 if(curr == newCycleFirstNode) {
								 newCycleLastLN.setNext(ogCycleLN.getNext());
								 if(ogCycleLN.getNext() != null) {
									 ogCycleLN.getNext().setPrev(newCycleLastLN);
								 }
								 if(ogCycleLN.getPrev()!=null) {
									 newCycleFirstLN.setPrev(ogCycleLN.getPrev());
									 ogCycleLN.getPrev().setNext(newCycleFirstLN);
									 break;
								 }
								 
								 cycle = newCycle;
								 break;
							 }
							 
						 }
						 break;
					 }
					 currNode = nextNode;
				 }
				 
			 }
		 }
		 cycle.updateNum();
		 
		 return cycle;
	 }
	 
	 public static boolean hasEulerian(ArrayList<Node> nodeArr) {
		 for(Node n: nodeArr) {
			 if(n.visited.contains(false)){
				 return false;
			 }
		 }
		 return true;
	 }
	 
	 
}

class lexiComparator implements Comparator<Node> {
	public int compare(Node a, Node b){
		return a.val.compareTo(b.val);
	}
}