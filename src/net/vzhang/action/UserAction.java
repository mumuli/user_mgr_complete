package net.vzhang.action;

import java.util.List;

import net.vzhang.manager.GroupManager;
import net.vzhang.manager.UserManager;
import net.vzhang.po.Group;
import net.vzhang.po.User;
import net.vzhang.util.PageModel;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	private static final String LIST = "list";
	private static final String LIST_LOAD = "list_load";
	private static final String LOAD = "load";
	private static final String LOAD_LOAD = "load_load";
	
	private User user;
	
	private UserManager userMgr;
	private GroupManager groupMgr;
	
	private List<User> userList;
	
	//页码数，主要是为了获取页面传递的参数
	private int page;
	
	//每页记录数，主要是为了获取页面传递的参数
	private int pageSize;
	
	private PageModel pageModel;
	
	private int[] uids;
	
	private String msg;
	
	private List<Group> groupList;
	
	private String queryUserName;
	
	private String queryGroupName;
	
	public UserAction() {
		// TODO Auto-generated constructor stub
		this.page = 1;
		this.pageSize = 20;
	}
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		this.user = new User();
		return this.user;
	}
	
	public void setUserMgr(UserManager userMgr) {
		this.userMgr = userMgr;
	}
	
	public void setGroupMgr(GroupManager groupMgr) {
		this.groupMgr = groupMgr;
	}

	public User getUser() {
		return this.user;
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageSize() {
		return this.pageSize;
	}
	
	public int getPage() {
		return this.page;
	}
	
	public PageModel getPageModel() {
		return pageModel;
	}

	public void setUids(int[] uids) {
		this.uids = uids;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
	
	public List<Group> getGroupList() {
		return groupList;
	}

	public String getQueryUserName() {
		return queryUserName;
	}

	public void setQueryUserName(String queryUserName) {
		this.queryUserName = queryUserName;
	}

	public String getQueryGroupName() {
		return queryGroupName;
	}

	public void setQueryGroupName(String queryGroupName) {
		this.queryGroupName = queryGroupName;
	}

	public String load() throws Exception {
		this.groupList = this.groupMgr.load();
		if(this.uids != null) {
			System.out.println("uid: " + this.uids[0]);
			this.user = this.userMgr.load(uids[0]);
		}
		return LOAD;
	}
	
	public String list() throws Exception {
		this.pageModel = new PageModel();
		pageModel.setPage(this.page);
		pageModel.setPageSize(this.pageSize);
		/*
		this.userList = this.userMgr.list(this.pageModel);
		*/
		System.out.println("queryUserName: " + this.queryUserName);
		System.out.println("queryGroupName: " + this.queryGroupName);
		this.userList = this.userMgr.list(pageModel, this.queryUserName, this.queryGroupName);
		return LIST;
	}
	
	public String delete() throws Exception {
		System.out.println("UserAction UserAction delete...");
		/*
		System.out.println("uids: ");
		for(int i = 0; i < this.uids.length; i++) {
			System.out.println("uids: " + uids[i]);
		}
		*/
		System.out.println("page: " + this.page);
		System.out.println("pageSize: " + this.pageSize);
		int count = this.userMgr.delete(this.uids);
		if(count > 0) {
			this.msg = "删除成功，删除了" + count + "条数据！";
		} else {
			this.msg = "删除失败！";
		}
		return LIST_LOAD;
	}
	
	public String modify() throws Exception {
		System.out.println("UserAction UserAction modify...");
		System.out.println("user.id: " + this.user.getId());
		System.out.println("user.name: " + this.user.getName());
		System.out.println("user.birthday: " + this.user.getBirthday());
		System.out.println("user.hiredate: " + this.user.getHiredate());
		System.out.println("user.group.id: " + this.user.getGroup().getId());
		this.user =  this.userMgr.modify(user);
		return LOAD_LOAD;
	}
	
	public String add() throws Exception {
		String resultCode = INPUT;
		if(this.user.getName() != null) {
			this.userMgr.add(user);
			resultCode = LOAD_LOAD;
		} else {
			this.groupList = this.groupMgr.load();
		}
		return resultCode;
	}
}
