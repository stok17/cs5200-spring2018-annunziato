package edu.northeastern.cs5200.Model;

public class ImageWidget extends Widget{
	private String src;
	
	public ImageWidget(int id, String name, int width, int height, String text, int order, String src) {
		this(id, 0, name, width, height, "", "", text, order, src);
	}
    public ImageWidget(int id, int pageId, String name, int width, int height, String cssClass, String cssStyle, String text, int order, String src) {
        super(id, pageId, name, width, height, cssClass, cssStyle, text, order);
        super.type = "image";
        this.src = src;
    }
	
	public String getSrc() {
		return src;
	}
    
	public void setSrc(String src) {
		this.src = src;
	}
}
