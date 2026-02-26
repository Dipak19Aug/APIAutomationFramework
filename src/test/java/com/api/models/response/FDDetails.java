package com.api.models.response;

public class FDDetails { // Inner class of GetFdDetailResponse
	
	private String fd_id;
	private String user_id;
	private String deposit_type;
	private Double amount; // Vaues e.g 5000.00 arahi hai isliye
	private int duration_months;
    private String nominee_firstname;
    private String nominee_lastname;
    private String nominee_address;
    private String nominee_relationship;
    private String created_at;
	
	public FDDetails(String fd_id, String user_id, String deposit_type, Double amount, int duration_months,
			String nominee_firstname, String nominee_lastname, String nominee_address, String nominee_relationship,
			String created_at) {
		super();
		this.fd_id = fd_id;
		this.user_id = user_id;
		this.deposit_type = deposit_type;
		this.amount = amount;
		this.duration_months = duration_months;
		this.nominee_firstname = nominee_firstname;
		this.nominee_lastname = nominee_lastname;
		this.nominee_address = nominee_address;
		this.nominee_relationship = nominee_relationship;
		this.created_at = created_at;
	}

	public String getFd_id() {
		return fd_id;
	}

	public void setFd_id(String fd_id) {
		this.fd_id = fd_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDeposit_type() {
		return deposit_type;
	}

	public void setDeposit_type(String deposit_type) {
		this.deposit_type = deposit_type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public int getDuration_months() {
		return duration_months;
	}

	public void setDuration_months(int duration_months) {
		this.duration_months = duration_months;
	}

	public String getNominee_firstname() {
		return nominee_firstname;
	}

	public void setNominee_firstname(String nominee_firstname) {
		this.nominee_firstname = nominee_firstname;
	}

	public String getNominee_lastname() {
		return nominee_lastname;
	}

	public void setNominee_lastname(String nominee_lastname) {
		this.nominee_lastname = nominee_lastname;
	}

	public String getNominee_address() {
		return nominee_address;
	}

	public void setNominee_address(String nominee_address) {
		this.nominee_address = nominee_address;
	}

	public String getNominee_relationship() {
		return nominee_relationship;
	}

	public void setNominee_relationship(String nominee_relationship) {
		this.nominee_relationship = nominee_relationship;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "FDDetails [fd_id=" + fd_id + ", user_id=" + user_id + ", deposit_type=" + deposit_type + ", amount="
				+ amount + ", duration_months=" + duration_months + ", nominee_firstname=" + nominee_firstname
				+ ", nominee_lastname=" + nominee_lastname + ", nominee_address=" + nominee_address
				+ ", nominee_relationship=" + nominee_relationship + ", created_at=" + created_at + "]";
	}

}
