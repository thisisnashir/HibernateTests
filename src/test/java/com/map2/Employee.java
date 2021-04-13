package com.map2;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Employee {
	@Id
	@Column(name = "question_id")
	private int employeeId;
	private String employeeName;

	@ManyToMany
	@JoinTable(name = "Employe_Project_NameChanged", joinColumns = { @JoinColumn(name = "Eid") }, inverseJoinColumns = {
			@JoinColumn(name = "Pid") }

	)
	private List<Project> projects;

	public Employee(int questionId, String question, List<Project> projects) {
		super();
		this.employeeId = questionId;
		this.employeeName = question;
		this.projects = projects;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}
