package visitor;

import java.util.List;
import java.util.ArrayList;

import user.Group;
import user.User;

public class FindIDVerification implements Visitor{

	public List<String> nonUniqueID = new ArrayList<>();
	public boolean IDboolean = true; 

	public void visit(User temp) {
		String checkStr = temp.getUniqueID();
		if ( checkStr.contains(" ")){
			IDboolean = false;
		} else if (nonUniqueID.contains(checkStr)){
			IDboolean = false;
		}
	}

	public void visit(Group temp) {
		String checkStr = temp.getUniqueID();
		if ( checkStr.contains(" ")){
			IDboolean = false;
		} else if (nonUniqueID.contains(checkStr)){
			IDboolean = false;
		}
	}
	
}
