package com.boot.demo.model;

import org.hibernate.validator.constraints.NotBlank;

public class AddUpdateUserCriteria {

    @NotBlank(message = "username can't empty!")
    String username;

	@NotBlank(message = "email can't empty!")
    String email;
    
    String first;
    
    String last;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}
}