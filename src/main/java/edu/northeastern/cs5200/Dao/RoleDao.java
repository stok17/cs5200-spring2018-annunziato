package edu.northeastern.cs5200.Dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RoleDao {
	public static RoleDao instance = null;
	public static RoleDao getInstance() {
		if (instance == null) {
			instance = new RoleDao();
		}
		return instance;
	}
	private RoleDao() {};

	public int assignWebsiteRole(int developerId, int websiteId, int roleId) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "INSERT INTO role (developerId, websiteId, Role) VALUES(?,?,?)";
			statement = conn.prepareStatement(query);
			statement.setInt(1, developerId);
			statement.setInt(2, websiteId);
            statement.setInt(3, roleId);
			rs = statement.executeUpdate();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
		
	}
	
	public int assignPageRole(int developerId, int pageId, int roleId) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "INSERT INTO role (developerId, pageId, Role) VALUES(?,?,?)";
			statement = conn.prepareStatement(query);
			statement.setInt(1, developerId);
			statement.setInt(2, pageId);
            statement.setInt(3, roleId);
			rs = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return rs;
	}
	
	public int deleteWebsiteRole(int developerId, int websiteId, int roleId) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "DELETE FROM role where developerId = ? AND websiteId = ? AND Role = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, developerId);
			statement.setInt(2, websiteId);
			statement.setInt(3, roleId);
            rs = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public int deletePageRole(int developerId, int pageId, int roleId) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "DELETE FROM role where developerId = ? AND pageId = ? AND Role = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, developerId);
			statement.setInt(2, pageId);
			statement.setInt(3, roleId);
			rs = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public int swapPageRole(int dev1Id, int dev2Id, int pageId){
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "UPDATE role " + 
					"SET `developerId` = " + 
					"CASE " + 
					"	WHEN `role`.developerId = ? THEN ?  " + 
					"	WHEN `role`.developerId = ? THEN ?  " + 
					"	ELSE `role`.developerId  " + 
					"END WHERE pageId = ?;";
            statement = conn.prepareStatement(query);
            statement.setInt(1, dev1Id);
            statement.setInt(2, dev2Id);
            statement.setInt(3, dev2Id);
            statement.setInt(4, dev1Id);
            statement.setInt(5, pageId);
            rs = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}	
}
