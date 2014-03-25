package net.vzhang.manager;

import java.util.List;

import net.vzhang.po.User;
import net.vzhang.util.PageModel;

public interface UserManager {
	
	public User add(User user);
	
	public User load(int uid);
	
	public List<User> listAll();
	
	public List<User> list(PageModel pageModel);
	
	public List<User> list(PageModel pageModel, String username, String groupname);
	
	public int delete(int[] ids);
	
	public User modify(User user);

}
