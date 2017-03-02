package com.lumin.gis.service;

import com.lumin.gis.dao.UserDao;
import com.lumin.gis.dao.UserDaoImple;
import com.lumin.gis.vo.*;

public class UserService {
	public User login(User user) {
		UserDao dao = new UserDaoImple();
		return dao.login(user);
	}
}
