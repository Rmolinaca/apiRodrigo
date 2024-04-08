package com.users.apirestful.apirest.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.users.apirestful.apirest.model.User;

public class UserDTO {

	private String userId;

	private String name;

	private String email;

	private String password;

	private List<PhoneDTO> phones;

	private String token;

	private Date created;

	private Date modified;

	private Date lastLogin;

	private Boolean isactive;

	public UserDTO(User user) {
		super();

		if (user.getUserId() != null) {
			this.userId = user.getUserId().toString();
		}
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.token = user.getToken();
		this.created = user.getCreated();
		this.modified = user.getModified();
		this.lastLogin = user.getLastLogin();
		this.isactive = user.getIsactive();

		if (user.getPhones() == null || user.getPhones().isEmpty()) {
			return;
		}

		List<PhoneDTO> phones = new ArrayList<PhoneDTO>();
		user.getPhones().forEach(ph -> phones.add(new PhoneDTO(ph)));
		this.phones = phones;

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PhoneDTO> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

}
