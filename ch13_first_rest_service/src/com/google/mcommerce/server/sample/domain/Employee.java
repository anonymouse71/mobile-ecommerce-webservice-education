package com.google.mcommerce.server.sample.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
public class Employee {
	private int ID;
	private String firstName;
	private String lastName;
	private int age;

	public Employee() {
	}

	public Employee(int id, String firstName, String lastName, int age) {
		this.ID = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	@XmlElement
	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	@XmlElement
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@XmlElement
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}