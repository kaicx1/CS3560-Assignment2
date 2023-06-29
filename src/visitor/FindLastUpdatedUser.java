package visitor;

import user.*;

public class FindLastUpdatedUser implements Visitor {
	
	public String lastUpdated;
	private int mostRecent = 0;

	public void visit(User temp) {
		long timeStamp = temp.getLastUpdateTime();
		if ( timeStamp > mostRecent){
			lastUpdated = temp.getUniqueID();
		}

	}

	public void visit(Group temp) {
	}
}