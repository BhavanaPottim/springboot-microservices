package com.example.demo.model.request;

import javax.validation.constraints.NotNull;

public class UpdateUserDetailsRequestModel {
	@NotNull(message = "cannot be null")
	private String firstName;
	@NotNull(message = "cannot be null")
	private String lastName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
