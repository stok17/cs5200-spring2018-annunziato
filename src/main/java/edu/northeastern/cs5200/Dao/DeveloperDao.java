package edu.northeastern.cs5200.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Model.Developer;
import edu.northeastern.cs5200.Model.User;
import edu.northeastern.cs5200.Model.Phone;

public class DeveloperDao{
	public static DeveloperDao instance = null;
	public static DeveloperDao getInstance() {
		if (instance == null) {
			instance = new DeveloperDao();
		}
		return instance;
	}
    
	private DeveloperDao() {};

	public int createDeveloper(Developer developer) {
        Connection conn = null;
        PreparedStatement statement = null;
		int rsperson = 0;
		int rsdev = 0;

		try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query1 = "INSERT INTO person(id, username, password, firstName, lastName, email, dob) VALUES(?,?,?,?,?,?,?)";
			statement = conn.prepareStatement(query1);
            statement.setInt(1, developer.getId());
			statement.setString(2, developer.getUsername());
			statement.setString(3, developer.getPassword());
			statement.setString(4, developer.getFirstName());
			statement.setString(5, developer.getLastName());
			statement.setString(6, developer.getEmail());
			statement.setDate(7, developer.getDob());
			rsperson = statement.executeUpdate();
			statement.close();
			String query2 = "INSERT INTO developer(id, developerKey) VALUES(?,?)";
			statement = conn.prepareStatement(query2);
			statement.setInt(1, developer.getId());
			statement.setString(2, developer.getDeveloperKey());
			rsdev = statement.executeUpdate();
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
		return Math.min(rsperson, rsdev);
	}

    public int createUser(User user) {
        Connection conn = null;
        PreparedStatement statement = null;
        int rsperson = 0;
        int rsuser = 0;

        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
            String query1 = "INSERT INTO person(id, username, password, firstName, lastName, email, dob) VALUES(?,?,?,?,?,?,?)";
            statement = conn.prepareStatement(query1);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getEmail());
            statement.setDate(7, user.getDob());
            rsperson = statement.executeUpdate();
            statement.close();
            String query2 = "INSERT INTO user(id, userAgreement, userKey) VALUES(?,?,?)";
            statement = conn.prepareStatement(query2);
            statement.setInt(1, user.getId());
            statement.setBoolean(2, user.isUserAgreement());
            statement.setString(3, user.getUserKey());
            rsuser = statement.executeUpdate();
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
        return Math.min(rsperson, rsuser);
    }
    
	public Collection<Developer> findAllDevelopers() {
		Collection<Developer> developers = new ArrayList<Developer>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "SELECT p.*, d.developerKey FROM developer d INNER JOIN person p on p.id = d.id";
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			while(rs.next()) {
                Developer developer = new Developer(rs.getInt("id"), rs.getString("username"), rs.getString("password"),rs.getString("firstName"),rs.getString("lastName"), rs.getString("email"),rs.getDate("dob"),rs.getString("developerKey"));
                developer.setWebsites(WebsiteDao.getInstance().findWebsitesForDeveloper(developer.getId()));
                developers.add(developer);
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
		return developers;
	}
	
	public Developer findDeveloperById(int developerId) {
		Developer dev = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "select p.*, d.* from person p inner join developer d on p.id = d.id where p.id = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, developerId);
			rs = statement.executeQuery();
			while(rs.next()) {
				Developer developer = new Developer(rs.getInt("id"), rs.getString("username"), rs.getString("password"),rs.getString("firstName"),rs.getString("lastName"), rs.getString("email"),rs.getDate("dob"),rs.getString("developerKey"));
                developer.setWebsites(WebsiteDao.getInstance().findWebsitesForDeveloper(developer.getId()));
                dev = developer;
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
		return dev;
	}
	
	public Developer findDeveloperByUsername(String username) {
        Developer dev = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "select p.*, d.* from person p inner join developer d on p.id = d.id where p.username = ?";
			statement = conn.prepareStatement(query);
			statement.setString(1, username);
			rs = statement.executeQuery();
			while(rs.next()) {
                Developer developer = new Developer(rs.getInt("id"), rs.getString("username"), rs.getString("password"),rs.getString("firstName"),rs.getString("lastName"), rs.getString("email"),rs.getDate("dob"),rs.getString("developerKey"));
                developer.setWebsites(WebsiteDao.getInstance().findWebsitesForDeveloper(developer.getId()));
                dev = developer;
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
		return dev;
	}
	
	public Developer findDeveloperByCredentials(String username, String password) {
        Developer dev = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "select p.*, d.* from person p inner join developer d on p.id = d.id where p.username = ? and p.password = ?";
			statement = conn.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			rs = statement.executeQuery();
			while(rs.next()) {
                Developer developer = new Developer(rs.getInt("id"), rs.getString("username"), rs.getString("password"),rs.getString("firstName"),rs.getString("lastName"), rs.getString("email"),rs.getDate("dob"),rs.getString("developerKey"));
                developer.setWebsites(WebsiteDao.getInstance().findWebsitesForDeveloper(developer.getId()));
                dev = developer;
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
		return dev;
	}
	
	public int updateDeveloper(int developerId, Developer developer) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "UPDATE person p, developer d SET p.username=?, p.password=?, p.firstName=?, p.lastName=?, p.email=?, p.dob=?, d.developerKey=? WHERE p.id=? AND p.id=d.id";
			statement = conn.prepareStatement(query);
			statement.setString(1, developer.getUsername());
			statement.setString(2, developer.getPassword());
			statement.setString(3, developer.getFirstName());
			statement.setString(4, developer.getLastName());
			statement.setString(5, developer.getEmail());
			statement.setDate(6, developer.getDob());
			statement.setString(7, developer.getDeveloperKey());
			statement.setInt(8, developerId);
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
	
	public int deleteDeveloper(int developerId) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "DELETE p.*, d.* from person p, developer d where p.id = d.id and p.id = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, developerId);
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
	
    public int updatePhoneForDeveloper(int developerId, Phone phone) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			
            String query = "UPDATE phone SET phone = ?, `primary` = ? where `primary` = ? and personId = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, phone.getPhone());
            statement.setBoolean(2, phone.isPrimary());
            statement.setBoolean(3, phone.isPrimary());
            statement.setInt(4, developerId);
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
    
    public int deleteAddressForDeveloper (int developerId, boolean primary) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "DELETE FROM address WHERE personId = ? AND `primary` = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, developerId);
            statement.setBoolean(2, primary);
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
