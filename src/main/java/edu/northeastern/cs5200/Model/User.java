package edu.northeastern.cs5200.Model;

import java.sql.Date;

public class User extends Person{

	private boolean userAgreement;
	private String userKey;

	public User(int id, String username, String password, String firstName, String lastName, String email, Date dob, boolean userAgreement, String userKey) {
		super(id, username, password, firstName, lastName, email, dob);
		this.userAgreement = userAgreement;
		this.userKey = userKey;
	}
	
    public User() {
        super();
    }
	
	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public boolean isUserAgreement() {
		return userAgreement;
	}
	public void setUserAgreement(boolean userAgreement) {
		this.userAgreement = userAgreement;
	}
}
