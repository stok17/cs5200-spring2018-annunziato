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
            String query = "INSERT INTO widget (id, pageId, type, name, width, height, cssClass, cssStyle, text, `order`, url, sharable, expandable, src, size, html) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			statement = conn.prepareStatement(query);
            statement.setInt(1, widget.getId());
            statement.setInt(2, pageId);
            statement.setString(3, widget.getType());
            statement.setString(4, widget.getName());
            statement.setInt(5, widget.getWidth());
            statement.setInt(6, widget.getHeight());
            statement.setString(7, widget.getCssClass());
            statement.setString(8, widget.getCssStyle());
            statement.setString(9, widget.getText());
            statement.setInt(10, widget.getOrder());
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
                int id = rs.getInt("id");
                int pageId = rs.getInt("pageId");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                String cssClass = rs.getString("cssClass");
                String cssStyle = rs.getString("cssStyle");
                String text = rs.getString("text");
                int order = rs.getInt("order");
                String url = rs.getString("url");
                boolean sharable = rs.getBoolean("sharable");
                boolean expendable = rs.getBoolean("expandable");
                String src = rs.getString("src");
                int size = rs.getInt("size");
                String html = rs.getString("html");
                Widget widget = null;
                switch(type) {
                    case "heading":
                        widget = new HeadingWidget(id, pageId, name, width, height, cssClass, cssStyle, text, order, size);
                        break;
                    case "html":
                        widget = new HtmlWidget(id, pageId, name, width, height, cssClass, cssStyle, text, order, html);
                        break;
                    case "youtube":
                        widget = new YouTubeWidget(id, pageId, name, width, height, cssClass, cssStyle, text, order, src, sharable, expendable);
                        break;
                    case "image":
                        widget = new ImageWidget(id, pageId, name, width, height, cssClass, cssStyle, text, order, url);
                        break;
                    default:
                        widget = new Widget(id, pageId, name, width, height, cssClass, cssStyle, text, order);
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
                int id = rs.getInt("id");
                int pageId = rs.getInt("pageId");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                String cssClass = rs.getString("cssClass");
                String cssStyle = rs.getString("cssStyle");
                String text = rs.getString("text");
                int order = rs.getInt("order");
                String url = rs.getString("url");
                boolean sharable = rs.getBoolean("sharable");
                boolean expendable = rs.getBoolean("expandable");
                String src = rs.getString("src");
                int size = rs.getInt("size");
                String html = rs.getString("html");
                switch(type) {
                    case "heading":
                        widget = new HeadingWidget(id, pageId, name, width, height, cssClass, cssStyle, text, order, size);
                        break;
                    case "html":
                        widget = new HtmlWidget(id, pageId, name, width, height, cssClass, cssStyle, text, order, html);
                        break;
                    case "youtube":
                        widget = new YouTubeWidget(id, pageId, name, width, height, cssClass, cssStyle, text, order, src, sharable, expendable);
                        break;
                    case "image":
                        widget = new ImageWidget(id, pageId, name, width, height, cssClass, cssStyle, text, order, url);
                        break;
                    default:
                        widget = new Widget(id, pageId, name, width, height, cssClass, cssStyle, text, order);
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
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                String cssClass = rs.getString("cssClass");
                String cssStyle = rs.getString("cssStyle");
                String text = rs.getString("text");
                int order = rs.getInt("order");
                String url = rs.getString("url");
                boolean sharable = rs.getBoolean("sharable");
                boolean expendable = rs.getBoolean("expandable");
                String src = rs.getString("src");
                int size = rs.getInt("size");
                String html = rs.getString("html");
                Widget widget = null;
                switch(type) {
                    case "heading":
                        widget = new HeadingWidget(id, pageId, name, width, height, cssClass, cssStyle, text, order, size);
                        break;
                    case "html":
                        widget = new HtmlWidget(id, pageId, name, width, height, cssClass, cssStyle, text, order, html);
                        break;
                    case "youtube":
                        widget = new YouTubeWidget(id, pageId, name, width, height, cssClass, cssStyle, text, order, url, sharable, expendable);
                        break;
                    case "image":
                        widget = new ImageWidget(id, pageId, name, width, height, cssClass, cssStyle, text, order, src);
                        break;
                    default:
                        widget = new Widget(id, pageId, name, width, height, cssClass, cssStyle, text, order);
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
			String query = "UPDATE widget SET pageId = ?, type = ?, name = ?, width = ?, height = ?, cssClass = ?, cssStyle = ?, text = ?, `order` =?,  url = ?, sharable = ?, expandable = ?, src = ?, size = ?, html = ? WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, widget.getPageId());
            statement.setString(2, widget.getType());
            statement.setString(3, widget.getName());
            statement.setInt(4, widget.getWidth());
            statement.setInt(5, widget.getHeight());
            statement.setString(6, widget.getCssClass());
            statement.setString(7, widget.getCssStyle());
            statement.setString(8, widget.getText());
            statement.setInt(9, widget.getOrder());
            if(widget instanceof YouTubeWidget) {
                YouTubeWidget inst = (YouTubeWidget) widget;
                statement.setString(10, inst.getUrl());
                statement.setBoolean(11, inst.issharable());
                statement.setBoolean(12, inst.isExpandable());
            } else {
                statement.setString(10, "");
                statement.setBoolean(11, false);
                statement.setBoolean(12, false);
            }
            if(widget instanceof ImageWidget) {
                ImageWidget inst = (ImageWidget) widget;
                statement.setString(13, inst.getSrc());
            } else {
                statement.setString(13, "");
            }
            if(widget instanceof HeadingWidget) {
                HeadingWidget inst = (HeadingWidget) widget;
                statement.setInt(14, inst.getSize());
            } else {
                statement.setInt(14, 0);
            }
            if(widget instanceof HtmlWidget) {
                HtmlWidget inst = (HtmlWidget) widget;
                statement.setString(15, inst.getHtml());
            } else {
                statement.setString(15, "");
            }
            statement.setInt(16, widgetId);
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
