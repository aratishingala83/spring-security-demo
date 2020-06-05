package com.example.demo.security;

public enum ApplicationUserPermission {
	
	CUSTOMER_READ("customer:read"),
	CUSTOMER_WRITE("customer:write"),
	ACCOUNT_READ("account:read"),
	ACCOUNT_WRITE("account:write");
	
	private final String permission;
	
	ApplicationUserPermission(String permission){
		this.permission = permission;
	}
	
	public String getPermission() {
		return this.permission;
	}

}
