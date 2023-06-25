package visitor;

import user.*;

public class FindUserTotalVisitor implements Visitor {

	// finds user total by adding whenever it visits an user
	public int total;

	public void visit(User temp) {
		total++;
	}

	public void visit(Group temp) {
	}
}
