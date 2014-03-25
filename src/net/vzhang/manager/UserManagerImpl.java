package net.vzhang.manager;

import java.util.Date;
import java.util.List;

import net.vzhang.dao.UserDao;
import net.vzhang.po.User;
import net.vzhang.util.PageModel;

public class UserManagerImpl implements UserManager {
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User add(User user) {
		// TODO Auto-generated method stub
		System.out.println("UserManagerImpl add...");
		user.setHiredate(new Date());
		this.userDao.save(user);
		return user;
	}
	
	@Override
	public User load(int uid) {
		// TODO Auto-generated method stub
		return this.userDao.load(uid);
	}
	
	@Override
	public List<User> listAll() {
		// TODO Auto-generated method stub
		
		return this.userDao.loadAll();
	}
	
	@Override
	public List<User> list(PageModel pageModel) {
		// TODO Auto-generated method stub
		return this.userDao.load(pageModel);
	}
	
	@Override
	public List<User> list(PageModel pageModel, String username,
			String groupname) {
		// TODO Auto-generated method stub
		if(username == null) {
			username = "";
		}
		if(groupname == null) {
			groupname = "";
		}
		return this.userDao.load(pageModel, username, groupname);
	}
	
	@Override
	public int delete(int[] ids) {
		// TODO Auto-generated method stub
		return this.userDao.delete(ids);
	}
	
	@Override
	public User modify(User user) {
		// TODO Auto-generated method stub
		User oldUser = this.userDao.load(user.getId());
		oldUser.setName(user.getName());
		oldUser.setBirthday(user.getBirthday());
		oldUser.setGroup(user.getGroup());
		return this.userDao.update(oldUser);
	}
}
