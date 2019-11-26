package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;

public class UserService {
	private static List<User> userDAO;
	static {
		userDAO = new ArrayList<User>();
		userDAO.add(new User("Richard", "Orr", "purple", "rorr", "pass"));
		userDAO.add(new User("Manisha", "Matthew", "green", "Jose", "Andrew"));
		userDAO.add(new User("Ryan", "Torey", "black", "Gerly", "Tristan"));
		userDAO.add(new User("Bader", "Juma", "blue", "bad", "123"));
	}
	
	public User login(String username, String password) {
		for(User u: userDAO) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password))
				return u;
		}
		return null;
	}
	public void addUser(User u) {
		userDAO.add(u);
	}
}
