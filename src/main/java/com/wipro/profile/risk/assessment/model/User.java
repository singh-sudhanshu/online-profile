package com.wipro.profile.risk.assessment.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name= "user")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "email")
    private String email;

	@Column(name = "firstname")
    private String firstName;
	
	@Column(name = "lastname")
    private String lastName;	
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "active")
	private int active;
	
	@Column(name = "mobile")
    private String mobile;

	@Column(name = "houseno")
    private String houseNo;
	
	@Column(name = "street")
    private String street;
	
	@Column(name = "city")
    private String city;
	
	@Column(name = "pincode")
    private String pincode;
	
	@Column(name = "state")
    private String state;
	
	@Column(name = "country")
    private String country;
	
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public User() {
    }

   

    public User( String email, String firstName, String lastName, String password, int active, String mobile,
			String houseNo, String street, String city, String pincode, String state, String country) {
	
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.active = active;
		this.mobile = mobile;
		this.houseNo = houseNo;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.country = country;
	}

	
    public User(String email, String firstName, String lastName, String password, int active, String mobile,
			String houseNo, String street, String city, String pincode, String state, String country, Collection<Role> roles) {
		
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.active = active;
		this.mobile = mobile;
		this.houseNo = houseNo;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.country = country;
		this.roles = roles;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", active=" + active + ", mobile=" + mobile + ", houseNo=" + houseNo
				+ ", street=" + street + ", city=" + city + ", pincode=" + pincode + ", country=" + country + ", roles="
				+ roles + "]";
	}
	
	

}
