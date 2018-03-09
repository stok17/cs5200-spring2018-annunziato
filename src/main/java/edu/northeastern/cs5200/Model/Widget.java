package edu.northeastern.cs5200.Model;

public class Widget {
    private int id;
    private int pageId;
    protected String type;
    private String name;
    private int width;
    private int height;
    private String cssClass;
    private String cssStyle;
    private String text;
    private int order;
    
    public Widget(int id, int pageId, String name, int width, int height, String cssClass, String cssStyle, String text, int order) {
        super();
        this.id = id;
        this.pageId = pageId;
        this.name = name;
        this.width = width;
        this.height = height;
        this.cssClass = cssClass;
        this.cssStyle = cssStyle;
        this.text = text;
        this.order = order;
    }
	
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getPageId() {
        return pageId;
    }
    
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public String getCssClass() {
        return cssClass;
    }
    
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
    
    public String getCssStyle() {
        return cssStyle;
    }
    
    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public int getOrder() {
        return order;
    }
    
    public void setOrder(int order) {
        this.order = order;
    }
}
