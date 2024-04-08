package com.users.apirestful.apirest.dto;

import com.users.apirestful.apirest.model.Phone;

public class PhoneDTO {

	private Integer number;

	private Integer citycode;

	private Integer contrycode;

	public PhoneDTO(Phone phone) {
		super();
		this.number = phone.getNumber();
		this.citycode = phone.getCitycode();
		this.contrycode = phone.getContrycode();
	}

	public PhoneDTO() {
		super();
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

}
