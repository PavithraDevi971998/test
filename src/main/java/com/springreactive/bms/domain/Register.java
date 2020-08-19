package com.springreactive.bms.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document

 public class Register{
	@Id
	private int id;
	
	@Size(min=2,message="Name should have atleast 3 characters")
	private String username;
	private String password;
	@Size(min=3,message="Address should have atleast 4 characters")
	private String address;
	private String state;
	private String country;
	@Email(message = "Email should be valid")
	private String email;
	private long pan;
	private long contactno;
	private String dob;
	@NotNull(message = "Accounttype cannot be null")
	private String accounttype;
	@NotNull(message = "Account Number cannot be null")
	private long accountno;
	
	public Register() {
		
	}

	public Register(int id, String username, String password, String address, String state, String country,
			String email, long pan, long contactno, String dob, String accounttype,long accountno) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.address = address;
		this.state = state;
		this.country = country;
		this.email = email;
		this.pan = pan;
		this.contactno = contactno;
		this.dob = dob;
		this.accounttype = accounttype;
		this.accountno=accountno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPan() {
		return pan;
	}

	public void setPan(long pan) {
		this.pan = pan;
	}

	public long getContactno() {
		return contactno;
	}

	public void setContactno(long contactno) {
		this.contactno = contactno;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public long getAccountno() {
		return accountno;
	}

	public void setAccountno(long accountno) {
		this.accountno = accountno;
	}

	@Override
	public String toString() {
		return "Register [id=" + id + ", username=" + username + ", password=" + password + ", address=" + address
				+ ", state=" + state + ", country=" + country + ", email=" + email + ", pan=" + pan + ", contactno="
				+ contactno + ", dob=" + dob + ", accounttype=" + accounttype + ", accountno=" + accountno + "]";
	}

	
	
}