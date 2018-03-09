package edu.northeastern.cs5200.Dao;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Model.Website;

public class WebsiteDao{
	public static WebsiteDao instance = null;
	public static WebsiteDao getInstance() {
		if (instance == null) {
			instance = new WebsiteDao();
		}
		return instance;
	}
	private WebsiteDao() {};
	
	public int createWebsiteForDeveloper(int developerId, Website website) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "INSERT INTO website (id, name, description, created, updated, visits, developerId) VALUES(?, ?, ?, ?, ?, ?, ?)";
			statement = conn.prepareStatement(query);
            statement.setInt(1, website.getId());
			statement.setString(2, website.getName());
			statement.setString(3, website.getDescription());
			statement.setDate(4, website.getCreated());
			statement.setDate(5, website.getUpdated());
			statement.setInt(6, website.getVisits());
            statement.setInt(7, developerId);
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
	
	public Collection<Website> findAllWebsites() {
        Collection<Website> websites = new ArrayList<Website>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "SELECT * FROM website";
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				Date created = rs.getDate("created");
				Date updated = rs.getDate("updated");
				int visits = rs.getInt("visits");
                int developerId = rs.getInt("developerId");
				Website website = new Website(id, name, description, created, updated, visits, developerId);
				website.setPages(PageDao.getInstance().findPagesForWebsite(id));
                websites.add(website);
			}
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
		return websites;
	}
	
	public Collection<Website> findWebsitesForDeveloper(int developerId) {
        Collection<Website> websites = new ArrayList<Website>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "SELECT * FROM website where developerId = ?)";
			statement = conn.prepareStatement(query);
			statement.setInt(1,developerId);
			rs = statement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Date created = rs.getDate("created");
                Date updated = rs.getDate("updated");
                int visits = rs.getInt("visits");
                Website website = new Website(id, name, description, created, updated, visits, developerId);
                website.setPages(PageDao.getInstance().findPagesForWebsite(id));
                websites.add(website);
            }
		
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
		return websites;
	}
	
	public Website findWebsiteById(int websiteId) {
		Website web = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "SELECT * FROM website where id = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1,websiteId);
			rs = statement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				Date created = rs.getDate("created");
				Date updated = rs.getDate("updated");
				int visits = rs.getInt("visits");
                int developerId = rs.getInt("developerId");
				web = new Website(id, name, description, created, updated, visits, developerId);
				web.setPages(PageDao.getInstance().findPagesForWebsite(id));
			}
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
		return web;
	}
	
	public int updateWebsite(int websiteId, Website website) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "UPDATE website SET name = ?, description = ?, created = ?, updated = ?, visits = ? where id = ? ";
			statement = conn.prepareStatement(query);
			statement.setString(1, website.getName());
			statement.setString(2, website.getDescription());
			statement.setDate(3, website.getCreated());
			statement.setDate(4, website.getUpdated());
			statement.setInt(5, website.getVisits());
			statement.setInt(6, websiteId);
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
	
	public int deleteWebsite(int websiteId) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "DELETE FROM website where id = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, websiteId);
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
