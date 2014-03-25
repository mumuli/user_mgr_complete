package net.vzhang.init;

import java.util.Calendar;

import net.vzhang.manager.GroupManager;
import net.vzhang.manager.UserManager;
import net.vzhang.po.Group;
import net.vzhang.po.User;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitData {
	
	public static void main(String[] args) {
		ApplicationContext applcationContext = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		System.out.println(applcationContext);
		
		UserManager userMgr = applcationContext.getBean("userMgr", UserManager.class);
		
		GroupManager groupMgr = applcationContext.getBean("groupMgr", GroupManager.class);
		System.out.println("userMgr: " + userMgr);
		System.out.println("groupMgr: " + groupMgr);
		
		Group g1 = new Group();
		g1.setName("½²Ê¦");
		groupMgr.add(g1);
		
		Group g2 = new Group();
		g2.setName("¶½µ¼");
		groupMgr.add(g2);
		
		Calendar c = Calendar.getInstance();
		for(int i = 0; i < 100; i++) {
			c.set(1980, 1, 1);
			int monthRand = (int)(Math.random() * 240);
			c.add(Calendar.MONTH, monthRand);
			User user = new User();
			user.setName("well_" + (i + 1));
			user.setBirthday(c.getTime());
			c.add(Calendar.MONTH, 240);
			user.setHiredate(c.getTime());
			if(monthRand % 2 == 0) {
				user.setGroup(g1);
			} else {
				user.setGroup(g2);
			}
			userMgr.add(user);
		}
		
	}
}
