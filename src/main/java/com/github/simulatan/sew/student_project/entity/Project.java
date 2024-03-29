package com.github.simulatan.sew.student_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@NamedQuery(name=Project.QUERY_FIND_ALL, query = "SELECT p FROM Project p ORDER BY p.name ASC")
public class Project {
	public static final String QUERY_FIND_ALL = "Project.findAll";

	@Id
	private Long id;
	private String name;
	private String description;
	@ManyToMany
	@JoinTable(
		name="project_teacher",
		joinColumns = @JoinColumn(name="project_id"),
		inverseJoinColumns = {
			@JoinColumn(name="teacher_id", referencedColumnName = "id"),
		}
	)
	@JsonIgnoreProperties({"projects"})
	private Set<Teacher> teachers;
	@JsonIgnoreProperties({"project", "student"})
	@OneToMany(mappedBy = "project")
	private Set<Task> tasks;
	@JsonIgnoreProperties({"projects", "tasks"})
	@ManyToMany
	@JoinTable(
		name="task",
		joinColumns = {
			@JoinColumn(name="id")
		},
		inverseJoinColumns = {
			@JoinColumn(name="student_clazz", referencedColumnName = "clazz"),
			@JoinColumn(name="student_catalog_number", referencedColumnName = "catalog_number")
		}
	)
	private Set<Student> students;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
