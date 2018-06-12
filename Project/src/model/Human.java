package model;

import java.io.Serializable;

public class Human implements Serializable {

	private String name;
	private int age;

	public Human() {

	}

	public Human(String name,int age) {
		this.name = name;
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

}
