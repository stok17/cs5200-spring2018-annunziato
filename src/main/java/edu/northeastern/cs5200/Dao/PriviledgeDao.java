package edu.northeastern.cs5200.Dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PriviledgeDao {
	public static PriviledgeDao instance = null;
	public static PriviledgeDao getInstance() {
		if (instance == null) {
			instance = new PriviledgeDao();
		}
		return instance;
	}
	private PriviledgeDao() {};
	
	public int assignWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "INSERT INTO priviledge (developerId, websiteId, priviledge), VALUES(?,?,?)";
			statement = conn.prepareStatement(query);
			statement.setInt(1, developerId);
			statement.setInt(2, websiteId);
            statement.setInt(3, priviledgeId);
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
	
	public int assignPagePriviledge(int developerId, int pageId, int priviledgeId) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "INSERT INTO priviledge (developerId, pageId, priviledge), VALUES(?,?,?)";
			statement = conn.prepareStatement(query);
			statement.setInt(1, developerId);
			statement.setInt(2, pageId);
			statement.setInt(3, priviledgeId);
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
	
	public int deleteWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "DELETE FROM priviledge where developerId = ? AND websiteId = ? AND priviledge = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, developerId);
			statement.setInt(2, websiteId);
            statement.setInt(3, priviledgeId);
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
	
	public int deletePagePriviledge(int developerId, int pageId, int priviledgeId) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "DELETE FROM priviledge where developerId = ? AND pageId = ? AND priviledge = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, developerId);
			statement.setInt(2, pageId);
            statement.setInt(3, priviledgeId);
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
