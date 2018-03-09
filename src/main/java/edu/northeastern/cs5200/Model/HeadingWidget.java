package edu.northeastern.cs5200.Model;

public class HeadingWidget extends Widget {
	private int size;

	public HeadingWidget(int id, String name, int width, int height, String text, int order, int size) {
		this(id, 0, name, width, height, "", "", text, order, size);
	}
    public HeadingWidget(int id, int pageId, String name, int width, int height, String cssClass, String cssStyle, String text, int order, int size) {
        super(id, pageId, name, width, height, cssClass, cssStyle, text, order);
        super.type = "heading";
        this.size = size;
    }

	public int getSize() {
		return size;
	}
    
	public void setSize(int size) {
		this.size = size;
    }
}
