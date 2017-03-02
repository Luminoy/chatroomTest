package com.lumin.gis.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.lumin.gis.utility.JDBCUtils;
import com.lumin.gis.vo.User;

public class UserDaoImple implements UserDao {
	public User login(User user) {
		User existUser;
		try {
			QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from user where username = ? and userpwd = ?";
			
			System.out.println(user.getUsername()+","+user.getUserpwd());
			
			existUser = queryRunner.query(sql, new BeanHandler<User>(User.class), user.getUsername(), user.getUserpwd());
		}catch(SQLException e) {
			System.out.print("hehe!!!");
			e.printStackTrace();
			throw new RuntimeException("ÓÃ»§µÇÂ¼Ê§°Ü!");
		}
		return existUser;
	}
	
}
