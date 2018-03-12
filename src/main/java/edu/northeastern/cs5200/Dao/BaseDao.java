package edu.northeastern.cs5200.Dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;

public class BaseDao {
    final static String DRIVER = "com.mysql.jdbc.Driver";
    final static String URL = "jdbc:mysql://cs5200-spring2018-annunziato.cv5mh96frk2l.us-east-2.rds.amazonaws.com/hw3_liu_siyu_spring_2018";
    final static String USERNAME = "liu";
    final static String PASSWORD = "yourPa$$word123";
    
    public static BaseDao instance = null;
    public static BaseDao getInstance() {
        if (instance == null) {
            instance = new BaseDao();
        }
        return instance;
    }
    
    private BaseDao() {};
    
    public void reconstruction(String rec) {
        Connection conn = null;
        BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(rec));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Statement statement = null;
        
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = null;
            while((query = br.readLine()) != null) {
                statement = (Statement) conn.createStatement();
                statement.executeUpdate(query);
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                statement.close();
                conn.close();
                br.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
