package edu.northeastern.cs5200.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;

// Class for person
public class Person {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Date dob;
	private Collection<Phone> phones = new ArrayList<Phone>();
    private Collection<Address> addresses = new ArrayList<Address>();

    public Person(int id, String username, String password, String firstName, String lastName, String email, Date dob) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
	}
	
	public Person() {
		super();
	}
	
	public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
    
	public String getUsername() {
		return username;
	}
    
	public void setUsername(String username) {
		this.username = username;
	}
    
	public String getPassword() {
		return password;
	}
    
	public void setPassword(String password) {
		this.password = password;
	}
    
	public String getFirstName() {
		return firstName;
	}
    
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
    
	public String getLastName() {
		return lastName;
	}
    
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
	public String getEmail() {
		return email;
	}
    
	public void setEmail(String email) {
		this.email = email;
	}
    
	public Date getDob() {
		return dob;
	}
    
	public void setDob(Date dob) {
		this.dob = dob;
	}
    
    public Collection<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Collection<Phone> phones) {
		this.phones = phones;
	}
    
    public Collection<Address> getaddresses() {
        return addresses;
    }
    
    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }
    
	public void addPhone(Phone phone) {
		this.phones.add(phone);
	}
	
	public void removePhone(Phone phone) {
		this.phones.remove(phone);
	}
	
	public void addAddress(Address address) {
		this.addresses.add(address);
	}
	
	public void removeAddress(Address address) {
		this.addresses.remove(address);
	}
}
