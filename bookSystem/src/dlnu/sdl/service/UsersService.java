package dlnu.sdl.service;

import java.util.ArrayList;

import dlnu.sdl.domain.Users;
import dlnu.sdl.utils.*;

public class UsersService {
	@SuppressWarnings("unchecked")
	public boolean checkUser(Users user) {
		String sql = "select * from users where id=? and password=?";
		String paras[] = { user.getId() + "", user.getPassword() };
		@SuppressWarnings({"static-access" })
		ArrayList al = new SqlHelper().executeQuery3(sql, paras);
		if (al.size() == 0) {
			return false;
		} else {
			Object[] object = (Object[]) al.get(0);
			// 把对象数组封装到Users对象
			user.setName(object[1].toString());
			user.setEmail(object[3].toString());
			user.setGrade(Integer.parseInt(object[5].toString()));
			return true;
		}
	}
	@SuppressWarnings("unchecked")
	public boolean checkadmin(Users user) {
		String sql = "select * from admin where id=? and password=?";
		String paras[] = { user.getId() + "", user.getPassword() };
		@SuppressWarnings({ "static-access" })
		ArrayList al = new SqlHelper().executeQuery3(sql, paras);
		if (al.size() == 0) {
			return false;
		} else {
			Object[] object = (Object[]) al.get(0);
			// 把对象数组封装到Users对象
			user.setName(object[1].toString());
			user.setEmail(object[3].toString());
			user.setGrade(Integer.parseInt(object[5].toString()));
			return true;
		}
	}
	//查询所有用户
	@SuppressWarnings({"static-access", "unchecked" })
	public ArrayList getAllUsers(){
		
		String sql = "select * from users where 1=?";
		String paras [] = {"1"};
		ArrayList al = new SqlHelper().executeQuery3(sql, paras);
		ArrayList<Users> newAl = new ArrayList<Users>();
		for(int i=0;i<al.size();i++){
			Object []object = (Object[]) al.get(i);
			Users user = new Users(Integer.parseInt(object[0].toString()),object[2].toString());
			user.setName(object[1].toString());
			user.setEmail(object[3].toString());
			user.setTelephone(object[4].toString());
			user.setGrade(Integer.parseInt(object[5].toString()));
			newAl.add(user);
		}
		
		return newAl;
	}
	//根据用户名查询用户
	@SuppressWarnings({"unchecked" })
	public ArrayList searchUser(String name) {
		String sql = " select * from users where name like ?";
		String paras[] = { "%"+name+"%" };
		@SuppressWarnings({ "static-access" })
		ArrayList al = new SqlHelper().executeQuery3(sql, paras);
		ArrayList<Users> newAl = new ArrayList<Users>();
		for(int i=0;i<al.size();i++){
			Object []object = (Object[]) al.get(i);
			Users user = new Users(Integer.parseInt(object[0].toString()),object[2].toString());
			user.setName(object[1].toString());
			user.setEmail(object[3].toString());
			user.setTelephone(object[4].toString());
			user.setGrade(Integer.parseInt(object[5].toString()));
			newAl.add(user);
		}
		return newAl;
	}
	
	//根据ID名查询用户
		@SuppressWarnings({ "unchecked" })
		public ArrayList searchIDUser(int id) {
			String sql = " select * from users where id = ?";
			String paras[] = { id+"" };
			@SuppressWarnings({ "static-access" })
			ArrayList al = new SqlHelper().executeQuery3(sql, paras);
			ArrayList<Users> newAl = new ArrayList<Users>();
			for(int i=0;i<al.size();i++){
				Object []object = (Object[]) al.get(i);
				Users user = new Users(Integer.parseInt(object[0].toString()),object[2].toString());
				user.setName(object[1].toString());
				user.setEmail(object[3].toString());
				user.setTelephone(object[4].toString());
				user.setGrade(Integer.parseInt(object[5].toString()));
				newAl.add(user);
			}
			return newAl;
		}
	
	//删除用户，返回是是否删除成功
	@SuppressWarnings("static-access")
	public boolean deleteUser(int[] id) {
		String sql = "delete from users where id = ?";
		boolean bo = false;
		String[] paras = new String[id.length];
		for (int i = 0; i < id.length; i++) {
			paras[i] = String.valueOf(id[i]);
		}
		bo = new SqlHelper().executeUpdate(sql, paras);
		return bo;
	}
	//添加用户
	@SuppressWarnings("static-access")
	public boolean adduser(Users user){
		boolean bo = false;
		String sql = "insert into users values(?,?,?,?,?,?)";
		String paras[] = { user.getId() + "", user.getName(),user.getPassword(),user.getEmail(),user.getTelephone(),user.getGrade()+"" };
		bo = new SqlHelper().executeUpdate(sql, paras);
		return bo;
	}
	//修改用户
	@SuppressWarnings("static-access")
	public boolean updateUser(Users user){
		boolean bo = false;
		String sql = "update users set name = ? ,password = ? ,email = ? ,telephone = ?, grade = ? where id = ?;";
		String paras[] = {user.getName(),user.getPassword(),user.getEmail(),user.getTelephone(),user.getGrade()+"",user.getId() + "",};
		bo = new SqlHelper().executeUpdate(sql, paras);
		return bo;
	}
}
