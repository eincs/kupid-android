package com.eincs.android.kupid.model;

public class KCredentialModel implements IModel {

	public String id;
	
	public String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
