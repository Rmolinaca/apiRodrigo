package com.users.apirestful.apirest.service;

import com.users.apirestful.apirest.model.User;

public interface UserService {

	User createUser(User user);

	Boolean existMail(String email);

	Boolean isNotValidMail(String email);

	Boolean isNotValidPassword(String passs);

	String createToken(String name, String email);

}
