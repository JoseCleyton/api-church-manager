package com.church.manager.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class, 
		property = "id")
public class Church implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="church_seq", sequenceName="church_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="church_seq")
	private Long id;
	private String name;
	private String phone;
	private String email;
	private String city;
	private String district;
	private String street;
	private String number;
	private String responsible;
	private String numberOfTithers;

	@OneToMany(mappedBy = "church")
	@JsonBackReference
	private List<Christian> christians;

	@OneToOne(mappedBy = "church", cascade = CascadeType.ALL)
	@JsonManagedReference
	private User user;


	public Church() {
		super();
	}


	public List<Christian> getChristians() {
		return christians;
	}


	public void setChristians(List<Christian> christians) {
		this.christians = christians;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumberOfTithers() {
		return numberOfTithers;
	}

	public void setNumberOfTithers(String numberOfTithers) {
		this.numberOfTithers = numberOfTithers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Church [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", city=" + city
				+ ", district=" + district + ", street=" + street + ", number=" + number + ", responsible="
				+ responsible + ", numberOfTithers=" + numberOfTithers + ", user=" + user + "]";
	}


}
