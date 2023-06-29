package user;

import visitor.*;

public interface NodeType {

	public String getUniqueID();

	public void add(NodeType node);

	public NodeType getChild(int i);

	public int getIndexOfChild(NodeType node);

	public int getChildCount();

	public void accept(Visitor vis);

	public void openUserView();

	public long getCreationTime();
}
