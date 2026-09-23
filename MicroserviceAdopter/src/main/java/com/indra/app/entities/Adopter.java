package com.indra.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="adopters")
public class Adopter {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Autoincrement
	private long id;
	
	@Column(name="first_name", length=50, nullable=false)
	private String firstName;
	
	@Column(name="last_name", length=50, nullable=false)
	private String lastName;

	@Column(length=120, unique=true)
	private String email;
	
	@Column(length=50, nullable=false)
	private String phone;
		
	@Column(length=150, nullable=false)
	private String address;

	
	//Getter and setter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
