package com.appsrox.messenger.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String regId;
	
	public Contact() {}
	
	public Contact(String email, String regId) {
		this.email = email;
		this.regId = regId;
	}
	
	public static Contact find(String email, EntityManager em) {
		Query q = em.createQuery("select c from Contact c where c.email = :email");
		q.setParameter("email", email);
		List<Contact> result = q.getResultList();
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	public Long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	
}
