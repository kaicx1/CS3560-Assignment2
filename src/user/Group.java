package user;

import visitor.*;
import java.util.ArrayList;

import tree.*;

public class Group implements NodeType {
	private long creationTime;
	private String uniqueID;
	private ArrayList<NodeType> children;

	public Group(NodeTreeModel treeModel, String uniqueID) {
		this.uniqueID = uniqueID;
		this.creationTime = System.currentTimeMillis();
		children = new ArrayList<>();
	}

	// getting unique id for group
	public String getUniqueID() {
		return uniqueID;
	}

	// adding children to group
	public void add(NodeType elem) {
		children.add(elem);
	}

	// return child 
	public NodeType getChild(int i) {
		return children.get(i);
	}

	// return the index of child 
	public int getIndexOfChild(NodeType elem) {
		return children.indexOf(elem);
	}

	// return total number of children
	public int getChildCount() {
		return children.size();
	}

	// accept visitor for group
	public void accept(Visitor vis) {
		vis.visit(this);
		for (NodeType elem : children) {
			elem.accept(vis);
		}
	}

	// open users in group recursivly 
	public void openUserView() {
		for (NodeType node: children){
			node.openUserView();
		}
	}

	public long getCreationTime(){
		return creationTime;
	}

}
