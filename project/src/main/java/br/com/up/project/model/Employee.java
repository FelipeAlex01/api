package br.com.up.project.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "employee")
public class Employee  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "registration", updatable = false, nullable = false)
	private Long registration;

	@Column(name = "name", nullable = false)
	private String name;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date_birth", nullable = false)
	private Date dateBirth;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "hiring_date", nullable = false)
	private Date hiringDate;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "number", nullable = true)
	private String number;

	@Column(name = "complement", nullable = true)
	private String complement;

	@Column(name = "neighborhood", nullable = false)
	private String neighborhood;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;

	@ManyToOne
	@JoinColumn(name = "team")
	private Team team;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	public Long getRegistration() {
		return registration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Date getHiringDate() {
		return hiringDate;
	}

	public void setHiringDate(Date hiringDate) {
		this.hiringDate = hiringDate;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (!password.isEmpty()) {
			this.password = convertStringToMd5(password);
		} else {
			this.password = password;
		}
	}

	public static String convertStringToMd5(String pPassword) {
		String passwordMd5 = "";

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(pPassword.getBytes(), 0, pPassword.length());
			passwordMd5 = new BigInteger(1, messageDigest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return passwordMd5;
	}

}
