package com.users.apirestful.apirest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "phones")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPhone;

	private Integer number;

	private Integer citycode;

	private Integer contrycode;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	
	public Phone(Long idPhone, Integer number, Integer citycode, Integer contrycode, User user) {
		super();
		this.idPhone = idPhone;
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
		this.user = user;
	}
	
	public Phone() {
		super();
	}
	

	public Long getIdPhone() {
		return idPhone;
	}

	public void setIdPhone(Long idPhone) {
		this.idPhone = idPhone;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getCitycode() {
		return citycode;
	}

	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}

	public Integer getContrycode() {
		return contrycode;
	}

	public void setContrycode(Integer contrycode) {
		this.contrycode = contrycode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
