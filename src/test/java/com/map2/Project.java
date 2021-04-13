package com.map2;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Project {

	@Id
	private int projectId;
	private String projectName;

	@ManyToMany
	private List<Employee> employees;

	public Project(int projectid, String projectname, List<Employee> employees) {
		super();
		projectId = projectid;
		this.projectName = projectname;
		this.employees = employees;
	}

	public int getProjectid() {
		return projectId;
	}

	public void setProjectid(int projectid) {
		projectId = projectid;
	}

	public String getProjectname() {
		return projectName;
	}

	public void setProjectname(String projectname) {
		this.projectName = projectname;
	}

	public List<Employee> getEmployee() {
		return this.employees;
	}

	public void setEmployee(List<Employee> employees) {
		this.employees = employees;
	}

}
