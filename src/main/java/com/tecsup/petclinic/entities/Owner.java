package com.tecsup.petclinic.entities;

import javax.persistence.*;

import lombok.Data;

@Entity(name="owners")
@Data
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String address;
	private String city;
	private String telephone;
	
	public Owner() {
		
	}
	
	public Owner(Integer id, String first_name, String last_name, String address, String city, String telephone) {
		super();
		this.id = id;
		this.firstName = first_name;
		this.lastName = last_name;
		this.address = address;
		this.city = city;
		this.telephone = telephone;
	}
	
	public Owner(String first_name, String last_name, String address, String city, String telephone) {
		super();
		this.firstName = first_name;
		this.lastName = last_name;
		this.address = address;
		this.city = city;
		this.telephone = telephone;
	}
}
