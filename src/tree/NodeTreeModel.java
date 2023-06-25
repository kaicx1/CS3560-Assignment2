package tree;

import user.*;
import visitor.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.*;
import javax.swing.tree.*;


public class NodeTreeModel implements TreeModel {

	private NodeType root;
	private List<TreeModelListener> listeners;


	public NodeTreeModel(NodeType root) {
		this.root = root;
		listeners = new ArrayList<>();
	}

	// required by treemodel
	public Object getRoot() {
		return root;
	}

	public Object getChild(Object parent, int index) {
		return ((NodeType) (((NodeType) parent).getChild(index)));
	}

	public int getChildCount(Object parent) {
		return (((NodeType) parent).getChildCount());
	}

	public boolean isLeaf(Object node) {
		return (((NodeType) node).getChildCount() == 0);
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public int getIndexOfChild(Object parent, Object child) {
		return (((NodeType) parent).getIndexOfChild(((NodeType) child)));
	}

	public void addTreeModelListener(TreeModelListener l) {
		listeners.add(l);
	}
	
	public void removeTreeModelListener(TreeModelListener l) {
		listeners.remove(l);
	}

	// not required

	public NodeType findNodeByID(NodeType start, String id) {
		FindNodeVisitor vis = new FindNodeVisitor(id);
		start.accept(vis);
		return vis.result;
	}

	private void fireTreeStructureChanged() {
		Object[] o = { root };
		TreeModelEvent e = new TreeModelEvent(this, o);
		for (TreeModelListener l : listeners) {
			l.treeStructureChanged(e);
		}
	}

	public void addNode(NodeType parent, NodeType elem) {
		parent.add(elem);
		fireTreeStructureChanged();
	}
}
