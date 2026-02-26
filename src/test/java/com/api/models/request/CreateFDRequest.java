package com.api.models.request;

public class CreateFDRequest {
	
		  private String depositType;
		  private int amount;
		  private int durationMonths;
		  private String nominee_firstname;
		  private String nominee_lastname;
		  private String nominee_address;
		  private String nominee_relationship;
		  /**
 		  * @param depositType
 		  * @param amount
 		  * @param durationMonths
 		  * @param nominee_firstname
 		  * @param nominee_lastname
 		  * @param nominee_address
 		  * @param nominee_relationship
 		  */
		  public CreateFDRequest(String depositType, int amount, int durationMonths, String nominee_firstname,
				String nominee_lastname, String nominee_address, String nominee_relationship) {
			super();
			this.depositType = depositType;
			this.amount = amount;
			this.durationMonths = durationMonths;
			this.nominee_firstname = nominee_firstname;
			this.nominee_lastname = nominee_lastname;
			this.nominee_address = nominee_address;
			this.nominee_relationship = nominee_relationship;
		  }
		  public String getDepositType() {
			  return depositType;
		  }
		  public void setDepositType(String depositType) {
			  this.depositType = depositType;
		  }
		  public int getAmount() {
			  return amount;
		  }
		  public void setAmount(int amount) {
			  this.amount = amount;
		  }
		  public int getDurationMonths() {
			  return durationMonths;
		  }
		  public void setDurationMonths(int durationMonths) {
			  this.durationMonths = durationMonths;
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
		  @Override
		  public String toString() {
			return "CreateFDRequest [depositType=" + depositType + ", amount=" + amount + ", durationMonths="
					+ durationMonths + ", nominee_firstname=" + nominee_firstname + ", nominee_lastname="
					+ nominee_lastname + ", nominee_address=" + nominee_address + ", nominee_relationship="
					+ nominee_relationship + "]";
		  }
		  
		  
	

}
