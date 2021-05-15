package com.church.manager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tithing implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private double value;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="christian_id", referencedColumnName = "id")
	private Christian christian;

	@OneToOne
	@JoinColumn(name="church_id", referencedColumnName = "id")
	private Church church;

	@Temporal(TemporalType.DATE)
	private Date date;

	public Tithing(Long id, double value, Christian christian, Church church, Date date) {
		super();
		this.id = id;
		this.value = value;
		this.christian = christian;
		this.church = church;
		this.date = date;
	}

	public Tithing() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Christian getChristian() {
		return christian;
	}

	public void setChristian(Christian christian) {
		this.christian = christian;
	}

	public Church getChurch() {
		return church;
	}

	public void setChurch(Church church) {
		this.church = church;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
