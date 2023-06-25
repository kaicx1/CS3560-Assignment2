package visitor;

import user.*;

public class FindNodeVisitor implements Visitor {

	//Finds node at id

	public NodeType result;
	private String uniqueID;

	public FindNodeVisitor(String searchID) {
		uniqueID = searchID.toLowerCase();
	}

	public void visit(User temp) {
		if (temp.getUniqueID().toLowerCase().equals(uniqueID)) {
			result = temp;
		}
	}

	public void visit(Group temp) {
		if (temp.getUniqueID().toLowerCase().equals(uniqueID)) {
			result = temp;
		}
	}

}
