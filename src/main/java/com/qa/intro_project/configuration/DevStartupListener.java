package com.qa.intro_project.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.qa.intro_project.data.entity.User;
import com.qa.intro_project.data.entity.UserProfile;
import com.qa.intro_project.data.repository.UserProfileRepository;
import com.qa.intro_project.data.repository.UserRepository;

@Profile("dev") // Only runs on the dev profile
@Configuration // Indicates this file contains bean candidates for component scanning
public class DevStartupListener implements ApplicationListener<ApplicationReadyEvent> {
	
	private UserRepository userRepository;
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	public DevStartupListener(UserRepository userRepository, UserProfileRepository userProfileRepository) {
		this.userRepository = userRepository;
		this.userProfileRepository = userProfileRepository;
	}

	// Waits until the app is truly ready before saving the users
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		List<User> users = userRepository.saveAll(List.of(
				new User("Fred", "fred@mail.com"),
				new User("Sarah", "sarah@mail.com")
		));
		
		User user = users.stream().filter(u -> u.getEmail().equals("fred@mail.com")).findFirst().orElse(null);
		UserProfile profile = userProfileRepository.save(new UserProfile("fred", "daly", 22, "male", null, user));

	}

}
