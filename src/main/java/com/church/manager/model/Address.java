package com.church.manager.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name="christian_id", referencedColumnName = "id")
	@JsonBackReference
	private Christian christian;
	
	@OneToOne
	@JoinColumn(name="church_id", referencedColumnName = "id")
	private Church church;
	
	private String city;
	private String district;
	private String street;
	private String number;

	public Address(Long id, Christian christian, Church church, String city, String district, String street,
			String number) {
		super();
		this.id = id;
		this.christian = christian;
		this.church = church;
		this.city = city;
		this.district = district;
		this.street = street;
		this.number = number;
	}

	public Address() {
		super();
	}
	
	public Church getChurch() {
		return church;
	}

	public void setChurch(Church church) {
		this.church = church;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Christian getChristian() {
		return christian;
	}
	public void setChristian(Christian christian) {
		this.christian = christian;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

}
