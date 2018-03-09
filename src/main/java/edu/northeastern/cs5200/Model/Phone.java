package edu.northeastern.cs5200.Model;

public class Phone {
	private String phone;
	private boolean primary;
	
	public Phone(String phone, boolean primary) {
		this.phone = phone;
		this.primary = primary;
	}
	
	public Phone() {
		super();
	}

	public String getPhone() {
		return phone;
	}
    
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
	public boolean isPrimary() {
		return primary;
	}
    
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
}
