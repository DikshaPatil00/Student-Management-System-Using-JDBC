package com.entityOJO;

public class Student {
	private int id;
	private String name;
	private double marks;
	private int rollnum;
	private String grade ;
	private String city ;
	
	public Student( int id, String name, double marks, int rollnum, String grade, String city) {
		this.id = id;
		this.name = name;
		this.marks = marks;
		this.rollnum = rollnum;
		this.grade = grade;
		this.city = city;
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

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	public int getRollnum() {
		return rollnum;
	}

	public void setRollnum(int rollnum) {
		this.rollnum = rollnum;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ",name=" + name + ", marks=" + marks + ", rollnum=" + rollnum + ", grade=" + grade
				+ ", city=" + city + "]";
	}
	
	
	
}
