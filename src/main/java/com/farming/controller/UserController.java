package com.farming.controller;
import java.util.HashMap;
import java.util.Map;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.farming.models.User;
import com.farming.repository.UserRepository;
import com.farming.service.UserService;


//import com.farming.service.UserService;
@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;

	    @PostMapping("/register")
	    public ResponseEntity<Object> registerUser(@RequestBody User user) {

	        if (userService.isUserWithEmailExists(user.getEmail())) {
	            return ResponseEntity.ok().build(); // Return empty response for existing user
	        }
	        // Register the user
	        userService.registerUser(user);
	        // Return success message
	        return ResponseEntity.ok().body("User register successfully");
	    }
	 // Endpoint to handle user login
	    @PostMapping("/login")
	    public ResponseEntity<Object> loginUser(@RequestBody User userData) {
	        // Extract email and password from user data
	        String email = userData.getEmail();
	        String password = userData.getPassword();
	        String userRole = userService.getUserRole(email, password);
	        if (userRole != null) {
	            // Perform redirection based on the user's role
	            if (userRole.equals("customer")) {
	                return ResponseEntity.ok().body("/dashboard-customer.html");
	            } else
	            {
	                return ResponseEntity.ok().body("/dashboard-farmer.html");
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        }
	    }
}
