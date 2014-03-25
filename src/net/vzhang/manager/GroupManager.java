package net.vzhang.manager;

import java.util.List;

import net.vzhang.po.Group;

public interface GroupManager {
	
	public Group add(Group group);
	
	public List<Group> load();

}
