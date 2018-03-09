package edu.northeastern.cs5200.Model;

public enum Priviledge {
    create(0),
    read(1),
    update(2),
    delete(3);

public int id;
	
	Priviledge(int id) {
		this.id = id;
	}
}