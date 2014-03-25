package net.vzhang.dao;

import java.util.List;

import net.vzhang.po.Group;

public interface GroupDao {
	
	public Group save(Group group);
	
	public List<Group> load();
	
}
