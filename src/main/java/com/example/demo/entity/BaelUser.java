package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.google.common.base.CaseFormat;

import java.util.List;


/**
 * The persistent class for the bael_users database table.
 * 
 */
@Entity
@Table(name="bael_users")
@NamedQuery(name="BaelUser.findAll", query="SELECT b FROM BaelUser b")
public class BaelUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String email;

	private byte enabled;

	private String password;

	//bi-directional many-to-one association to Authority
	@OneToMany(mappedBy="baelUser", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Authority> authorities;

	public BaelUser() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Authority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public Authority addAuthority(Authority authority) {
		getAuthorities().add(authority);
		authority.setBaelUser(this);

		return authority;
	}

	public Authority removeAuthority(Authority authority) {
		getAuthorities().remove(authority);
		authority.setBaelUser(null);

		return authority;
	}

}