import java.util.*;

public class Node {
	public ArrayList<Node> parent;
	public ArrayList<Node> children;
	public ArrayList<Boolean> visited;
	public int fakeIndex = -1;
	public String val = null;
	
	public Node(String val) {
		this.val = val;
		children = new ArrayList<Node>();
		parent = new ArrayList<Node>();
		visited = new ArrayList<Boolean>();
	}
	
	public int nextUnvisitedNode() {
		
		int min = -1;
		for(int i =0; i < children.size(); i ++) {
			if(visited.get(i) == false) {
				min = i;
				break;
			}
		}
		
		ArrayList<String> edges = new ArrayList<String>();
		for(int i =0; i < children.size(); i ++) {
			edges.add(val + children.get(i).val.charAt(children.get(i).val.length()-1));
		}
		
		for(int i = min; i < edges.size(); i ++) {
			if(edges.get(min).compareTo(edges.get(i)) > 0 && visited.get(i) != true) {
				min = i;
			}
		}
		return min;
	}
	
	 public String getSuffix(String s) {
		 StringBuilder suf = new StringBuilder(s);
		 
		 return suf.substring(1);
	 }
	
	public boolean hasUnvisitedNode(){
		if(visited.contains(false)){
			return true;
		} else{
			return false;
		}
	}
}
