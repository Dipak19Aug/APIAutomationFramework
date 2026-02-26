package com.api.testdata;
import com.api.models.request.CreateFDRequest;
import com.api.models.request.RegisterRequest;
import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDataFactory {
	
	private static Faker faker = new Faker();
	
	 public static RegisterRequest getRegisterData() {

	        String firstName = faker.name().firstName();
	        String lastName = faker.name().lastName();
	        String middleName = faker.name().firstName();

	        String username = firstName + faker.number().digits(3);
	        String pan = faker.regexify("[A-Z]{5}[0-9]{4}[A-Z]");
	        String aadhaar = faker.number().digits(12);
	        String mobile = "9" + faker.number().digits(9);

	        Date dobDate = faker.date().birthday(18, 40);
	        String dob = new SimpleDateFormat("yyyy-MM-dd").format(dobDate);

	        String gender = faker.options().option("male", "female");

	        return new RegisterRequest(
	                firstName,
	                username,
	                middleName,
	                lastName,
	                pan,
	                aadhaar,
	                mobile,
	                dob,
	                gender
	        );
	    }
        
	 
	 public static CreateFDRequest getFDData() {

		    String depositType = "Fixed";
		    int amount = faker.number().numberBetween(10000, 100000);
		    int durationMonths = faker.number().numberBetween(6, 60);
		    String nomineeFirstName = faker.name().firstName();
		    String nomineeLastName = faker.name().lastName();
		    String nomineeAddress = faker.address().city();
		    String relationship = faker.options().option("Father","Mother","Spouce","Brother", "Sister","Son","Daughter","Other");

		    return new CreateFDRequest(
		            depositType,
		            amount,
		            durationMonths,
		            nomineeFirstName,
		            nomineeLastName,
		            nomineeAddress,
		            relationship
		    );
		}

}
