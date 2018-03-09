package edu.northeastern.cs5200.Model;

public enum Role {
    owner(0),
    admin(1),
    writer(2),
    editor(3),
    reviewer(4);

public int id;
	
	Role(int id) {
		this.id = id;
	}
}