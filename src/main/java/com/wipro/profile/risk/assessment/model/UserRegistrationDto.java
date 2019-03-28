package com.wipro.profile.risk.assessment.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.wipro.profile.risk.assessment.validation.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
    @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserRegistrationDto {
	
	@NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;
    
	@NotEmpty
    private String mobile;
	
	 @Email
	 @NotEmpty
	 private String email;
	 
	 @NotEmpty
	    private String houseNo;
	 
	 @NotEmpty
	    private String street;
	 
	 @NotEmpty
	    private String city;
	 
	 @NotEmpty
	    private String pincode;
	 
	 @NotEmpty
	    private String state;
	 
	 @NotEmpty
	    private String country;
	 
	 @NotEmpty
	    private String user;
	 
    @NotEmpty
    private String password;
    
    @NotEmpty
    private String confirmPassword;
  

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getMobile() {
  		return mobile;
  	}

  	public void setMobile(String mobile) {
  		this.mobile = mobile;
  	}
  	
  	 public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }
  	
    public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
