package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the authorities database table.
 * 
 */
@Entity
@Table(name="authorities")
@NamedQuery(name="Authority.findAll", query="SELECT a FROM Authority a")
public class Authority implements Serializable {
	private static final long serialVersionUID = 1L;

	private String authority;

	//bi-directional many-to-one association to BaelUser
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="username")
	private AppUser baelUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
	
	
	public Authority() {
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public AppUser getBaelUser() {
		return this.baelUser;
	}

	public void setBaelUser(AppUser baelUser) {
		this.baelUser = baelUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}