package net.vzhang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import net.vzhang.po.User;
import net.vzhang.util.PageModel;

public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		System.out.println("UserDaoImpl save...");
		Session session = this.sessionFactory.getCurrentSession();
		session.save(user);
		return user;
	}
	
	@Override
	public User load(int uid) {
		// TODO Auto-generated method stub
		User user = (User)this.sessionFactory.getCurrentSession().load(User.class, uid);
		//System.out.println("user.name: " + user.getName());
		return user;
	}
	
	@Override
	public List<User> loadAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<User> users = session.createQuery("from User").list();
		return users;
	}
	
	@Override
	public List<User> load(PageModel pageModel) {
		// TODO Auto-generated method stub
		String hqlCount = "select count(*) from User";
		Session session = this.sessionFactory.getCurrentSession();
		long count = (Long)session.createQuery(hqlCount).list().get(0);
		pageModel.setCount(count);
		int page = pageModel.getPage();
		int pageCount = pageModel.getPageCount();
		int pageSize = pageModel.getPageSize();
		
		if(page > pageCount) {
			page = pageCount;
			pageModel.setPage(page);
		}
		int startIndex =  (page - 1) * pageSize;
		List<User> users = session.createQuery("from User")
			.setFirstResult(startIndex)
			.setMaxResults(pageSize)
			.list();
		return users;
	}
	
	@Override
	public List<User> load(PageModel pageModel, String username,
			String groupname) {
		// TODO Auto-generated method stub
		StringBuffer sb1 = new StringBuffer("select count(*) from User u");
		
		StringBuffer sbConditions = new StringBuffer(" where u.name like ? and u.group.name like ?");
		
		Session session = this.sessionFactory.getCurrentSession();
		long count = (Long)session.createQuery(sb1.append(sbConditions).toString())
			.setParameter(0, "%" + username + "%")
			.setParameter(1, "%" + groupname + "%")
			.list().get(0);
		pageModel.setCount(count);
		int page = pageModel.getPage();
		int pageCount = pageModel.getPageCount();
		int pageSize = pageModel.getPageSize();
		
		if(page > pageCount) {
			page = pageCount;
			pageModel.setPage(page);
		}
		int startIndex =  (page - 1) * pageSize;
		StringBuffer sb2 = new StringBuffer("select u from User u");
		
		List<User> users = session.createQuery(sb2.append(sbConditions).toString())
			.setParameter(0, "%" + username + "%")
			.setParameter(1, "%" + groupname + "%")
			.setFirstResult(startIndex)
			.setMaxResults(pageSize)
			.list();
		return users;
	}
	
	@Override
	public int delete(int[] ids) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		int count = 0;
		for(int i = 0; i < ids.length; i++) {
			User user = new User();
			user.setId(ids[i]);
			session.delete(user);
			count++;
		}
		return count;
	}
	
	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
		return user;
	}
}
