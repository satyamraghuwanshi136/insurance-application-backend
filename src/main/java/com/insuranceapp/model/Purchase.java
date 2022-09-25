package com.insuranceapp.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="purchase")
public class Purchase 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int purchaseId;
	private LocalDateTime dop;
	private LocalDateTime dom;
	private String payCycle;
	private long premium;
	private String status;
	
	//mapping with user
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name="userID")
		private User user;
		
		//mapping with policy
		@ManyToOne
		@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
		@JoinColumn(name="policy_id")
		private Policy policy; 
		
		//mapping with Customer
		@ManyToOne
		@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
		@JoinColumn(name="customer_id")
		private Customer customer;
		
		//mapping with Nominee
		@ManyToOne
		@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
		@JoinColumn(name="nominee_id")
		private Nominee nominee;

	public Nominee getNominee() {
			return nominee;
		}

		public void setNominee(Nominee nominee) {
			this.nominee = nominee;
		}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public LocalDateTime getDop() {
		return dop;
	}

	public void setDop(LocalDateTime dop) {
		this.dop = dop;
	}

	public LocalDateTime getDom() {
		return dom;
	}

	public void setDom(LocalDateTime dom) {
		this.dom = dom;
	}

	public String getPayCycle() {
		return payCycle;
	}

	public void setPayCycle(String payCycle) {
		this.payCycle = payCycle;
	}

	public long getPremium() {
		return premium;
	}

	public void setPremium(long premium) {
		this.premium = premium;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Purchase(int purchaseId, int custId, int policyId, int userId, LocalDateTime dop, LocalDateTime dom, String payCycle,
			long premium, String status) {
		super();
		this.purchaseId = purchaseId;
		this.dop = dop;
		this.dom = dom;
		this.payCycle = payCycle;
		this.premium = premium;
		this.status = status;
	}

	public Purchase() {
		super();
		
	}
	

	
	

	
}
