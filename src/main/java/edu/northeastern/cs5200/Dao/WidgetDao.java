package edu.northeastern.cs5200.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Model.HeadingWidget;
import edu.northeastern.cs5200.Model.HtmlWidget;
import edu.northeastern.cs5200.Model.ImageWidget;
import edu.northeastern.cs5200.Model.Widget;
import edu.northeastern.cs5200.Model.YouTubeWidget;

public class WidgetDao {
	public static WidgetDao instance = null;
	public static WidgetDao getInstance() {
		if (instance == null) {
			instance = new WidgetDao();
		}
		return instance;
	}
    
	private WidgetDao() {};

	public int createWidgetForPage(int pageId, Widget widget) {
		int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
		
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
            String query = "INSERT INTO widget (id, name, width, height, cssClass, cssStyle, text, `order`, pageId, type, url, sharable, expandable, src, size, html) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			statement = conn.prepareStatement(query);
            statement.setInt(1, widget.getId());  
            statement.setString(2, widget.getName());
            statement.setInt(3, widget.getWidth());
            statement.setInt(4, widget.getHeight());
            statement.setString(5, widget.getCssClass());
            statement.setString(6, widget.getCssStyle());
            statement.setString(7, widget.getText());
            statement.setInt(8, widget.getOrder());
            statement.setInt(9, pageId);
            statement.setString(10, widget.getType().name());
            if(widget instanceof YouTubeWidget) {
                YouTubeWidget inst = (YouTubeWidget) widget;
                statement.setString(11, inst.getUrl());
                statement.setBoolean(12, inst.issharable());
                statement.setBoolean(13, inst.isExpandable());
            } else {
                statement.setString(11, "");
                statement.setBoolean(12, false);
    				statement.setBoolean(13, false);
            }
            if(widget instanceof ImageWidget) {
                ImageWidget inst = (ImageWidget) widget;
                statement.setString(14, inst.getSrc());
            } else {
                statement.setString(14, "");
            }
            if(widget instanceof HeadingWidget) {
                HeadingWidget inst = (HeadingWidget) widget;
                statement.setInt(15, inst.getSize());
            } else {
    			statement.setInt(15, 0);
            }
            if(widget instanceof HtmlWidget) {
                HtmlWidget inst = (HtmlWidget) widget;
                statement.setString(16, inst.getHtml());
            } else {
                statement.setString(16, "");
            }
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
	
	public Collection<Widget> findAllWidgets() {
		Collection<Widget> widgets = new ArrayList<Widget>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "SELECT * FROM widget";
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
            while(rs.next()) {  
                String type = rs.getString("type");
                Widget widget = null;
                switch(type) {
                    case "heading":
                        widget = new HeadingWidget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"), rs.getInt("size"));
                        break;
                    case "html":
                        widget = new HtmlWidget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"), rs.getString("html"));
                        break;
                    case "youtube":
                        widget = new YouTubeWidget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"), rs.getString("src"), rs.getBoolean("sharable"), rs.getBoolean("expandable"));
                        break;
                    case "image":
                        widget = new ImageWidget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"), rs.getString("url"));
                        break;
                    default:
                        widget = new Widget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"));
                        break;
                }
                widgets.add(widget);
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
		return widgets;
	}
	
	public Widget findWidgetById(int widgetId) {
		Widget widget = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
            String query = "SELECT * FROM widget WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, widgetId);
            rs = statement.executeQuery();
            while(rs.next()) {
            	String type = rs.getString("type");
                switch(type) {
                    case "heading":
                        widget = new HeadingWidget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"), rs.getInt("size"));
                        break;
                    case "html":
                        widget = new HtmlWidget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"), rs.getString("html"));
                        break;
                    case "youtube":
                        widget = new YouTubeWidget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"), rs.getString("src"), rs.getBoolean("sharable"), rs.getBoolean("expandable"));
                        break;
                    case "image":
                        widget = new ImageWidget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"), rs.getString("url"));
                        break;
                    default:
                        widget = new Widget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"));
                        break;
                }
            }
        }
        catch (Exception e) {
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
        return widget;
	}
	
	public Collection<Widget> findWidgetsForPage(int pageId) {
		Collection<Widget> widgets = new ArrayList<Widget>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "SELECT * FROM widget WHERE pageId = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, pageId);
			rs = statement.executeQuery();
            while(rs.next()) {
            	String type = rs.getString("type");
                Widget widget = null;
                switch(type) {
                    case "heading":
                        widget = new HeadingWidget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"), rs.getInt("size"));
                        break;
                    case "html":
                        widget = new HtmlWidget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"), rs.getString("html"));
                        break;
                    case "youtube":
                        widget = new YouTubeWidget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"), rs.getString("src"), rs.getBoolean("sharable"), rs.getBoolean("expandable"));
                        break;
                    case "image":
                        widget = new ImageWidget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"), rs.getString("url"));
                        break;
                    default:
                        widget = new Widget(rs.getInt("id"), rs.getString("name"), rs.getInt("width"), rs.getInt("height"), rs.getString("cssClass"), rs.getString("cssStyle"), rs.getString("text"), rs.getInt("order"));
                        break;
                }
                widgets.add(widget);
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
		return widgets;
	}
	
	public int updateWidget(int widgetId, Widget widget) {
		int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "UPDATE widget SET name = ?, width = ?, height = ?, cssClass = ?, cssStyle = ?, text = ?, `order` =?, type = ?, url = ?, sharable = ?, expandable = ?, src = ?, size = ?, html = ? WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, widget.getName());
            statement.setInt(2, widget.getWidth());
            statement.setInt(3, widget.getHeight());
            statement.setString(4, widget.getCssClass());
            statement.setString(5, widget.getCssStyle());
            statement.setString(6, widget.getText());
            statement.setInt(7, widget.getOrder());
            statement.setString(8, widget.getType().name());
            if(widget instanceof YouTubeWidget) {
                YouTubeWidget inst = (YouTubeWidget) widget;
                statement.setString(9, inst.getUrl());
                statement.setBoolean(10, inst.issharable());
                statement.setBoolean(11, inst.isExpandable());
            } else {
                statement.setString(9, "");
                statement.setBoolean(10, false);
                statement.setBoolean(11, false);
            }
            if(widget instanceof ImageWidget) {
                ImageWidget inst = (ImageWidget) widget;
                statement.setString(12, inst.getSrc());
            } else {
                statement.setString(12, "");
            }
            if(widget instanceof HeadingWidget) {
                HeadingWidget inst = (HeadingWidget) widget;
                statement.setInt(13, inst.getSize());
            } else {
                statement.setInt(13, 0);
            }
            if(widget instanceof HtmlWidget) {
                HtmlWidget inst = (HtmlWidget) widget;
                statement.setString(14, inst.getHtml());
            } else {
                statement.setString(14, "");
            }
            statement.setInt(15, widgetId);
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
	
	public int deleteWidget(int widgetId) {
        int rs = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName(BaseDao.DRIVER);
            conn = DriverManager.getConnection(BaseDao.URL, BaseDao.USERNAME, BaseDao.PASSWORD);
			String query = "DELETE FROM widget WHERE id = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, widgetId);
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
