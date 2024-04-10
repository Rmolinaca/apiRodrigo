package com.users.apirestful.apirest.mapper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.users.apirestful.apirest.dto.PhoneDTO;
import com.users.apirestful.apirest.dto.UserDTO;
import com.users.apirestful.apirest.dto.UserResponseDTO;
import com.users.apirestful.apirest.model.Phone;
import com.users.apirestful.apirest.model.User;

@Component
public class UserMapper {

	public User entryUserToEntity(UserDTO userDTO) {

		List<Phone> userPhones = Optional.ofNullable(userDTO.getPhones()).orElse(Collections.emptyList()).stream()
				.map(phoneDto -> new Phone(null, phoneDto.getNumber(), phoneDto.getCitycode(), phoneDto.getContrycode(),
						null))
				.collect(Collectors.toList());

		User user = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userPhones);

		Date currentDate = new Date();
		user.setCreated(currentDate);
		user.setModified(currentDate);
		user.setLastLogin(currentDate);
		user.setIsactive(Boolean.TRUE);
		userPhones.forEach(phone -> phone.setUser(user));

		return user;
	}

	public UserResponseDTO entityToRespDTO(User user) {

		UserResponseDTO userResponseDTO = new UserResponseDTO();

		Optional.ofNullable(user.getUserId()).map(Object::toString).ifPresent(userResponseDTO::setUserId);
		userResponseDTO.setName(user.getName());
		userResponseDTO.setEmail(user.getEmail());
		userResponseDTO.setPassword(user.getPassword());
		userResponseDTO.setToken(user.getToken());
		userResponseDTO.setCreated(user.getCreated());
		userResponseDTO.setModified(user.getModified());
		userResponseDTO.setLastLogin(user.getLastLogin());
		userResponseDTO.setIsactive(user.getIsactive());

		List<PhoneDTO> userPhones = Optional.ofNullable(user.getPhones()).orElse(Collections.emptyList()).stream()
				.map(phoneDto -> new PhoneDTO(phoneDto.getNumber(), phoneDto.getCitycode(), phoneDto.getContrycode()))
				.collect(Collectors.toList());

		userResponseDTO.setPhones(userPhones);

		return userResponseDTO;
	}

}