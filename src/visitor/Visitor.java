package visitor;

import user.*;

public interface Visitor {
	public void visit(User temp);

	public void visit(Group temp);
}
