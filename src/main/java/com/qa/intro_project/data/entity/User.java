package com.qa.intro_project.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity 
@Table(name = "user")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 16, message = "Username must have at least 2 characters, but no more than 16")
	private String username;
	
	@NotNull
	@Email
	private String email;
	
	@OneToOne(mappedBy = "user", // mappedBy is the name of the field in the userProfile
			  optional = false, // is the relationship optional
			  cascade = {CascadeType.ALL}, // cascade is used to cascade operations, for example if I delete a user, their user profile will also automatically be deleted
			  targetEntity = UserProfile.class, // the type of the class on the other side of the relationship
			  fetch = FetchType.EAGER)
	@JsonProperty(access = Access.READ_WRITE)
	private UserProfile userProfile;
	
	// fetch says how data should be retrieved from the database
	// - EAGER fetching means the data will be retrieved immediately
	// - LAZY fetching means the data will only be requested when it is needed/used
	
	// @OneToMany signifies a one to many relationship between user and posts where User is the 
	// parent of the relationship. Post owns the relationship as it stores the id of the user
	// - mappedBy signifies the name of the field in Post.class which owns the relationship
	// - targetEntity specifies the class that is being mapped
	@OneToMany(mappedBy = "user", targetEntity = Post.class, fetch = FetchType.EAGER)
	@JsonIgnore // stops this field from being serialised or deserialised during a request
	private List<Post> posts;
		
	protected User() {
		super();
		this.posts = new ArrayList<>();
	}
	
	public User(String username, String email) {
		super();
		this.username = username;
		this.email = email;
		this.posts = new ArrayList<>();
	}

	public User(int id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.posts = new ArrayList<>();
	}
	
	public User(String username, String email, UserProfile userProfile) {
		super();
		this.username = username;
		this.email = email;
		this.posts = new ArrayList<>();
		this.userProfile = userProfile;
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

	public List<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + "]";
	}
	
}
