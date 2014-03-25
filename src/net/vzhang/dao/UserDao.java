package net.vzhang.dao;

import java.awt.print.Pageable;
import java.util.List;

import net.vzhang.po.User;
import net.vzhang.util.PageModel;

public interface UserDao {
	
	public User save(User user);
	
	public User load(int uid);
	
	public List<User> loadAll();
	
	public List<User> load(PageModel pageModel);
	
	public List<User> load(PageModel pageModel, String username, String groupname);
	
	public int delete(int[] ids);
	
	public User update(User user);

}
