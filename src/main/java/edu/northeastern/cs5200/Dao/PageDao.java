package edu.northeastern.cs5200.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Model.Page;

public class PageDao {
	public static PageDao instance = null;
	public static PageDao getInstance() {
		if (instance == null) {
			instance = new PageDao();
		}
		return instance;
	}
    
	private PageDao() {};

	public int createPageForWebsite(int websiteId, Page page) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "INSERT INTO page (id, title, description, views, created, updated, websiteId) VALUES(?, ?, ?, ?, ?, ?, ?)";
			statement = conn.prepareStatement(query);
			statement.setInt(1, page.getId());
			statement.setString(2, page.getTitle());
			statement.setString(3, page.getDescription());
			statement.setInt(4, page.getViews());
			statement.setDate(5, page.getCreated());
			statement.setDate(6, page.getUpdated());
			statement.setInt(7, websiteId);
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
	
	public Collection<Page> findAllPages() {
        Collection<Page> pages = new ArrayList<Page>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "SELECT * FROM page";
			statement = conn.prepareStatement(query);
            rs = statement.executeQuery();            
            while(rs.next()) {
            		Page page = new Page(rs.getInt("id"), rs.getString("title"), rs.getString("description"),rs.getDate("created"),rs.getDate("updated"), rs.getInt("views"));
            		page.setWidgets(WidgetDao.getInstance().findWidgetsForPage(page.getId()));
            		pages.add(page);
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
		return pages;
	}
	
	public Page findPageById(int pageId) {
		Page page = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "SELECT * FROM page where id = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, pageId);
            rs = statement.executeQuery();
            while(rs.next()) {
            		page = new Page(rs.getInt("id"), rs.getString("title"), rs.getString("description"),rs.getDate("created"),rs.getDate("updated"), rs.getInt("views"));
            		page.setWidgets(WidgetDao.getInstance().findWidgetsForPage(page.getId()));
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
		return page;
	}
	
	public Collection<Page> findPagesForWebsite(int websiteId) {
		Collection<Page> pages = new ArrayList<Page>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "SELECT * FROM page where websiteId = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, websiteId);
			rs = statement.executeQuery();
			while(rs.next()) {
				Page page = new Page(rs.getInt("id"), rs.getString("title"), rs.getString("description"),rs.getDate("created"),rs.getDate("updated"), rs.getInt("views"));
        			page.setWidgets(WidgetDao.getInstance().findWidgetsForPage(page.getId()));
        			pages.add(page);
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
		return pages;
	}
	
	public int updatePage(int pageId, Page page) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "UPDATE page SET title = ?, description = ?, views = ?, created = ?, updated = ? WHERE id = ? ";
			statement = conn.prepareStatement(query);
			statement.setString(1, page.getTitle());
			statement.setString(2, page.getDescription());
			statement.setInt(3, page.getViews());
			statement.setDate(4, page.getCreated());
			statement.setDate(5, page.getUpdated());
			statement.setInt(6, pageId);
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
	
	public int deletePage(int pageId) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "DELETE FROM page WHERE id = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, pageId);
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
