package section_7.data;

import java.util.ArrayList;
import java.util.List;

public class Student {

	// Variables
	private String name;
	private int gradeLevel;
	private double gpa;
	private String gender;
	List<String> activities = new ArrayList<>();

	// Constructor
	public Student(String name, int gradeLevel, double gpa, String gender, List<String> activities) {
		this.name = name;
		this.gradeLevel = gradeLevel;
		this.gpa = gpa;
		this.gender = gender;
		this.activities = activities;
	}

	// Getters & Setters
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(int gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public List<String> getActivities() {
		return activities;
	}

	public void setActivities(List<String> activities) {
		this.activities = activities;
	}

	// toString()
	@Override
	public String toString() {
		return "Student{" + "name='" + name + '\'' + ", gradeLevel=" + gradeLevel + ", gpa=" + gpa + ", gender='"
				+ gender + '\'' + ", activities=" + activities + '}';
	}

}
