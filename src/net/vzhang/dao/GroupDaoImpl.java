package net.vzhang.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import net.vzhang.po.Group;

public class GroupDaoImpl implements GroupDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Group save(Group group) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(group);
		return group;
	}
	
	@Override
	public List<Group> load() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Group").list();
	}

}
