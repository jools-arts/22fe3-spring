package com.qa.intro_project.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.intro_project.data.entity.User;

@RestController
@RequestMapping(path = "/user") // accepts requests at localhost:8080/user
public class UserController {

	// the list of users is just pretending to be a database for now
	private static int COUNTER = 1;
	private List<User> users = new ArrayList<>(List.of(new User(COUNTER++, "Fred"), new User(COUNTER++, "Sarah")));
	
	// GET
	@GetMapping
	public List<User> getUsers() {
		return users;
	}
	
	// GET by id
	// - specify a variable in a path by surrounding it in curly braces
	@GetMapping(path = "/{id}") // localhost:8080/user/3
	public User getUser(@PathVariable(name = "id") int id) {
		for (int i = 0; i < users.size(); i++) {
			if (this.users.get(i).getId() == id) {
				return this.users.get(i);
			}
		}
		return null; // we should return a 404 not found response code
	}
	
	// POST
	@PostMapping // indicates POST requests are accepted at localhost:8080/user
	public User createUser(@RequestBody User user) {
        // @RequestBody tells Spring to retrieve the data in the requests body and then create an instance        // of User (using the default empty constructor) and then calls its mutators (setters) to set its values        user.setId(COUNTER++);
        users.add(user);
        return user;
	}
	
	// PUT
	@PutMapping(path = "/{id}")
	public User updateUser(@RequestBody User user, @PathVariable(name = "id") int id) {
        // Get the user from the list        
		User savedUser = null;
        for (int i = 0; i < users.size(); i++) {
            if (this.users.get(i).getId() == id) {
                savedUser = this.users.get(i);
            }
        }
        // Update that user        
        if (savedUser != null) {
            savedUser.setUsername(user.getUsername());
        }
        // Return that user        
        return savedUser;
	}
	
	// DELETE
	public User deleteUser(@PathVariable(name = "id") int id) {
        // Get the user        
		User user = null;
        for (int i = 0; i < users.size(); i++) {
            if (this.users.get(i).getId() == id) {
                user = this.users.get(i);
            }
        }
	}
}
