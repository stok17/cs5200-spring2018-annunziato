package edu.northeastern.cs5200.Model;

public class YouTubeWidget extends Widget{
	private String url;
	private boolean sharable;
	private boolean expandable;
	
	public YouTubeWidget(int id, String name, int width, int height, int order, String url, 
			boolean sharable, boolean expendable) {
		this(id, name, width, height, "", "", "", order, url, sharable, expendable);
	}
	
    public YouTubeWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order, String url, boolean sharable, boolean expendable) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
        super.type = Type.youtube;
        this.url = url;
        this.sharable = sharable;
        this.expandable = expendable;
    }

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean issharable() {
		return sharable;
	}
	
	public void setsharable(boolean sharable) {
		this.sharable = sharable;
	}
	
	public boolean isExpandable() {
		return expandable;
	}
	
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
}
	
	

