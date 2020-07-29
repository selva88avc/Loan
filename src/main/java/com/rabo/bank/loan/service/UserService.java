package com.rabo.bank.loan.service;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rabo.bank.loan.exception.UserNotFoundException;
import com.rabo.bank.loan.model.User;

@Service
public class UserService {
	@Autowired
	private  RestTemplate restTemplate;

	public User[] findUsers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		User[] users = restTemplate.exchange("http://localhost:8083/users", HttpMethod.GET, entity, User[].class).getBody();
		if(users==null) {
			throw new UserNotFoundException("User details not found");
		}
		return users;
	}
	public User findByUserId(int userId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		User user = restTemplate.exchange("http://localhost:8083/users/"+userId, HttpMethod.GET, entity, User.class).getBody();
		if(user==null) {
			throw new UserNotFoundException("User details not found");
		}
		return user;
	}

}
