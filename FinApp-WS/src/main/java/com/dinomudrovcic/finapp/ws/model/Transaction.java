package com.dinomudrovcic.finapp.ws.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="transaction_table")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_transaction", updatable = false, nullable = false)
	private long idTransaction;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", nullable = false)
	private Date createDate;

	@JoinColumn(name = "userid")
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	private User user;
	
	@Column(name = "userid", insertable = false, updatable = false)
	private long idUser;
	
	@Column(name = "operation", nullable = false)
	private String operation;
	
	@Column(name = "amount", nullable = false)
	private double amount;

	@JoinColumn(name = "debtorid")
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	private User debtor;
	
	@Column(name = "debtorid", nullable = true, insertable = false, updatable = false)
	private long idDebtor;
	
	public Transaction() {
		
	}
	
	public Transaction(long idUser, String operation, double amount, long idDebtor) {
		this.idUser = idUser;
		this.operation = operation.toString();
		this.amount = amount;
		this.idDebtor = idDebtor;
	}

	public Transaction(User user, String operation, double amount, User debtor) {
		this.user = user;
		this.operation = operation.toString();
		this.amount = amount;
		this.debtor = debtor;
	}
	
	public long getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(long idTransaction) {
		this.idTransaction = idTransaction;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getDebtor() {
		return debtor;
	}

	public void setDebtor(User debtor) {
		this.debtor = debtor;
	}

	public long getIdDebtor() {
		return idDebtor;
	}

	public void setIdDebtor(long idDebtor) {
		this.idDebtor = idDebtor;
	}
	

	
}
