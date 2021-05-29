package com.church.manager.model;


public class ChangePassword {
	
	private Long idUser;
	private String passwordAdmin;
	private String newPassword;
	
	
	
	public ChangePassword(Long idUser, String passwordAdmin, String newPassword) {
		super();
		this.idUser = idUser;
		this.passwordAdmin = passwordAdmin;
		this.newPassword = newPassword;
	}

	public String getPasswordAdmin() {
		return passwordAdmin;
	}
	
	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "ChangePassword [passwordAdmin=" + passwordAdmin + ", newPassword=" + newPassword
				+ ", getPasswordAdmin()=" + getPasswordAdmin() + ", getNewPassword()=" + getNewPassword()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

}
