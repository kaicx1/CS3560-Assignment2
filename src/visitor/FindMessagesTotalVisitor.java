package visitor;

import user.*;

public class FindMessagesTotalVisitor implements Visitor {

	// finds messages total by adding amount of tweets
	public int total;

	public void visit(User temp) {
		total += temp.getTweets().size();
	}

	public void visit(Group temp) {
	}
}
