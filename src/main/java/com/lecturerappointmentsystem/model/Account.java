package com.lecturerappointmentsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author GustTech
 */
@Entity
@Table(name = "account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "id")
	private long id;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "username")
	private String username;

	@Column(name = "year")
	private Integer year;

	@Size(max = 50)
	@Column(name = "reg_number")
	private String regNumber;

	@JoinColumn(name = "program_id", referencedColumnName = "id")
	@ManyToOne
	private Programs program;

	@JoinColumn(name = "role_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Roles role;

	public Account() {
	}

	public Account(Integer id) {
		this.id = id;
	}

	public Account(Integer id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public Programs getProgram() {
		return program;
	}

	public void setProgram(Programs program) {
		this.program = program;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static Account fromCommand(Account request) {

		if (request == null) {
			return null;
		}

		Account account = new Account();
		account.setFirstName(request.getFirstName());
		account.setLastName(request.getLastName());
		account.setUsername(request.getUsername());
		account.setRegNumber(request.getRegNumber());
		account.setYear(request.getYear());
		account.setProgram(request.getProgram());
		account.setRole(request.getRole());

		return account;
	}

	public void update(Account updateRequest) {
		this.setFirstName(updateRequest.getFirstName());
		this.setLastName(updateRequest.getLastName());
		this.setUsername(updateRequest.getUsername());
		this.setRegNumber(updateRequest.getRegNumber());
		this.setYear(updateRequest.getYear());
		this.setProgram(updateRequest.getProgram());
		this.setRole(updateRequest.getRole());
	}

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", username='" + username + '\'' +
				", year=" + year +
				", regNumber='" + regNumber + '\'' +
				", program=" + program +
				", role=" + role +
				'}';
	}
}
