package com.qa.intro_project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name = "user_profile")
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String forename;
	private String surname;
	
	@Min(18)
	private int age;
	
	private String biologicalSex;
	
	private String gender;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id") // name of column in the UserProfile table to store id of User
	@JsonIgnore // don't serialise or deserialise JSON 
				// (don't create a user object when a user profile object is posted to our controller and deserialised)
				// (don't create a user object when serialising a user profile into JSON)
	private User user;
	
	// When using a LAZY fetch type, until the actual data is requested it is represented
	// by a proxy object
	// - trying to serialise this object as JSON will result in an error
	
	// UserProfile table
	// - id
	// - forename
	// - surname
	// - age
	// - biologicalSex
	// - gender
	// - user_id
	
	protected UserProfile() {
		super();
	}

	public UserProfile(String forename, String surname, int age, String biologicalSex, String gender, User user) {
		super();
		this.forename = forename;
		this.surname = surname;
		this.age = age;
		this.biologicalSex = biologicalSex;
		this.gender = gender;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBiologicalSex() {
		return biologicalSex;
	}

	public void setBiologicalSex(String biologicalSex) {
		this.biologicalSex = biologicalSex;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
