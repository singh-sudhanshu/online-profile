package com.wipro.profile.risk.assessment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.profile.risk.assessment.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByEmail(String email);

	User findUserByFirstName(String firstName);

	User findUserByLastName(String lastName);

	User findByMobile(String mobile);

	User findUserByFirstNameAndLastNameAndMobileAndEmail(String firstName, String lastName, String mobile,
			String email);

	@Modifying
	@Transactional
	@Query("update User u set u.firstName = :firstName,u.lastName = :lastName,u.mobile = :mobile,u.houseNo = :houseNo,u.street = :street,u.city = :city,u.pincode = :pincode, u.state = :state, u.country = :country where u.email = :email")
	void updateUserSetForEmail(@Param("firstName")String username, @Param("lastName")String lastname, @Param("mobile")String mobile,@Param("email") String email,@Param("houseNo") String houseNo, @Param("street")String street, @Param("city")String city, @Param("pincode")String pincode,@Param("state") String state,@Param("country") String country);
	
}
