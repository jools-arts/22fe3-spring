package com.qa.intro_project.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name = "post")
public class Post {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
	@Size(min = 3, max = 32, message = "Title must have at least 3 characters, but no more than 32")
	private String title;
	
	@NotNull
	@NotBlank
	private String content;
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	// - `optional` set to true indicates this is not a required relationship (a user may not have any posts)
	// - setting to EAGER fetch otherwise serialisation error occurs due to the proxy object Hibernate uses
	// - fetch type can be set to lazy when using DTOs
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	// - `name` is the name of the column in the Post table that stores the user id
	// - `referencedColumnName` is the name of the primary key field in the User table
	@JsonIgnore
	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tagged_post", // name of the join table
	           joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"), // name of the key of the domain which owns the relationship
	           inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")) // inverse of above
	private List<Tag> tags;
	
	protected Post() {
		super();
		this.tags = new ArrayList<>();
	}

	public Post(String title, String content) {
		super();
		this.title = title;
		this.content = content;
		this.tags = new ArrayList<>();
	}

	public Post(String title, String content, User user) {
		super();
		this.title = title;
		this.content = content;
		this.user = user;
		this.tags = new ArrayList<>();
	}
	
	public Post(String title, String content, User user, List<Tag> tags) {
		super();
		this.title = title;
		this.content = content;
		this.user = user;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + "]";
	}

}
