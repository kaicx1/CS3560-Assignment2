package visitor;

import user.*;

public class FindGroupTotalVisitor implements Visitor {

	// finds group total by adding whenever it visits an group
	public int total;

	public void visit(User temp) {
	}

	public void visit(Group temp) {
		total++;
	}
}
