package com.dinomudrovcic.finapp.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_user", updatable = false, nullable = false)
	private long idUser;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Column(name = "account_status")
	private Double accountStatus;
	
	
	public User() {
		
	}
	
	public User(long id) {
		this.idUser = id;
	}
	
	public User(String name, String surname, Double accStatus) {
		this.name = name;
		this.surname = surname;
		this.accountStatus = accStatus;
	}
	
	public User(Long idUser, String name, String surname, Double accStatus) {
		this.idUser = idUser;
		this.name = name;
		this.surname = surname;
		this.accountStatus = accStatus;
	}
	
	
	


	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Double getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Double accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	
	@Override
	public String toString() {
		return "User - id = " + idUser + ", name = " + name + ", surname = " + surname + ", account status = " + accountStatus + ".";
	}
}
