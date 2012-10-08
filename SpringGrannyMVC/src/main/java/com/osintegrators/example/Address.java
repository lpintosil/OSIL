package com.osintegrators.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({@NamedQuery(name="Address.findAll",query="select a from Address a"),
	           @NamedQuery(name="Address.findByName",query="select a from Address a where a.name = ?1")})
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;


	private String name;
	private String email;
	private String phone;
	private String address;
	
	public Address() {}

	public Address(String name, String address, String phone, String email) {
		this.name = name;
		this.address=address;
		this.phone=phone;
		this.email=email;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name=name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
