package com.users.apirestful.apirest.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.apirestful.apirest.model.Phone;
import com.users.apirestful.apirest.model.User;
import com.users.apirestful.apirest.repository.PhoneRepository;
import com.users.apirestful.apirest.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	private final static String TOKEN_SECRET = "TcG2Wb7odhTfh+7Fzt/4UxgW5Wic/0CQnHeYtLmu7";

	private final static String FILE_CONFIG = "src/main/resources/application.properties";

	private final static String PASS_REGEX_COD = "key.regex.password";

	public User createUser(User user) {
		Date date = new Date();

		user.setCreated(date);
		user.setModified(date);
		user.setLastLogin(date);
		user.setIsactive(Boolean.TRUE);
		user.setToken(createToken(user.getName(), user.getEmail()));

		User savedUser = userRepository.save(user);

		if (!user.getPhones().isEmpty()) {
			for (Phone ph : user.getPhones()) {
				ph.setUser(savedUser); 
				phoneRepository.save(ph); 
			}
		}
		return savedUser;
	}

	public Boolean existMail(String email) {
		return userRepository.existsByEmail(email);
	}

	public Boolean isNotValidMail(String email) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches()) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;

	}

	public Boolean isNotValidPassword(String passs) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(FILE_CONFIG));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String regex = (String) properties.get(PASS_REGEX_COD);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(passs);

		if (matcher.matches()) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;

	}

	public String createToken(String name, String email) {

		Map<String, Object> extraData = new HashMap<>();
		extraData.put("name", name);

		return Jwts.builder().signWith(Keys.hmacShaKeyFor(TOKEN_SECRET.getBytes())).setSubject(email)
				.addClaims(extraData).compact();
	}

}
