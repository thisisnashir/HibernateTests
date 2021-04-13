package com.tut.LifeSucks;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	private int id;
	private String name;
	private String city;

	private Certificate cerr; // adding another memeber who will bring more data into
	// the table from another class;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String name, String city, Certificate cerr) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.cerr = cerr;
	}

	public Certificate getCerr() {
		return cerr;
	}

	public void setCerr(Certificate cerr) {
		this.cerr = cerr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id + " : " + this.name + " : " + this.city;
	}

}
