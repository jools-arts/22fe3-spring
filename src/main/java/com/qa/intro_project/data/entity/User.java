package com.qa.intro_project.data.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Spring has a 'spring-boot-starter-validation' module which transitively includes
// the 'hibernate-validator' dependency. This can be used to apply validation annotations
// to data transfer or domain objects.
// 
// The preferred method is to annotate your fields with validation attributes, but you
// can annotate methods. Do not do both to avoid unnecessarily validating twice.
//
// Validation must be triggered via the @Valid annotation, see `UserController -> createUser()`.
//
// Validation annotations include:
// - @Email            Applies strict email validation according to the Bean validation provider
// - @NotBlank         The field must not be null and contain at least 1 non-whitespace character
// - @Min @Max         Used to specify the minimum and maximum ranges of a numeric value respectively (inclusive)
// - @Past @Future     Used to specify that a date should be in the past or the future respectively
// - @PastOrPresent    Specifies that the date should be in the past or now
// - @FutureOrPresent  Specifies that the date should be in the future or now
// - @Pattern          Use to provide a regex pattern

public class User {

	private int id;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 16, message = "Username must have at least 2 characters, but no more than 16")
	private String username;
	@NotNull
	@NotBlank
	@Size(min = 5, max = 20, message = "Email must have at least 5 characters, but no more than 20")
	private String email;
	@NotNull
	@NotBlank
	@Size(min = 4, max = 30, message = "Address must have at least 4 characters, but no more than 30")
	private String address;
	@NotNull
	@NotBlank
	@Size(min = 5, max = 13, message = "Mobile Number must have atl least 5 characters, but no more than 13")
	private int mobile_number;
	
	// TODO: Add 3 new fields to the User class, with appropriate validation annotations applied to each
	
	public User() {
		
	}

	public User(int id, String username, String email, String address, int mobile_number) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.address = address;
		this.mobile_number = mobile_number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(int mobile_number) {
		this.mobile_number = mobile_number;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", address=" + address
				+ ", mobile_number=" + mobile_number + "]";
	}
	
}
