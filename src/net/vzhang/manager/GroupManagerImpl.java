package net.vzhang.manager;

import java.util.List;

import net.vzhang.dao.GroupDao;
import net.vzhang.po.Group;

public class GroupManagerImpl implements GroupManager {
	
	private GroupDao groupDao;
	
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public Group add(Group group) {
		// TODO Auto-generated method stub
		return this.groupDao.save(group);
	}
	
	@Override
	public List<Group> load() {
		// TODO Auto-generated method stub
		return this.groupDao.load();
	}

}
