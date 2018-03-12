package edu.northeastern.cs5200.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;

public class Developer extends Person{
	private String developerKey;
	private Collection<Website> websites = new ArrayList<Website>();
	
    public Developer(int id, String username, String password, String firstName, String lastName, String email, Date dob, String developerKey) {
        super(id, username, password, firstName, lastName, email, dob);
        this.developerKey = developerKey;
    }
    
    public Developer() {
        super();
    }
    
	public String getDeveloperKey() {
		return developerKey;
	}
	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}

	public Collection<Website> getWebsites() {
		return websites;
	}

	public void setWebsites(Collection<Website> websites) {
		this.websites = websites;
	}
	
	public void addWebsite(Website website) {
		this.websites.add(website);
	}
	
	public void removeWebsite(Website website) {
		this.websites.remove(website);
	}
}
