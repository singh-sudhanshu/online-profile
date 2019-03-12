package com.wipro.profile.risk.assessment.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.profile.risk.assessment.model.AssessmentData;
import com.wipro.profile.risk.assessment.model.InvestmentRecommendation;
import com.wipro.profile.risk.assessment.model.CompositeKey;

@Repository
public interface AssessmentRepository extends JpaRepository<AssessmentData, CompositeKey> {	
	

	@Modifying
	@Transactional
	@Query(value = "insert into assessment_data (user_name, question_number, selected_option) VALUES (:userName, :questionNumber, :selectedOption)" , nativeQuery = true)
	void saveUserResponse(@Param("userName") Optional<String> userName, @Param("questionNumber") String questionNumber, @Param("selectedOption") String selectedOption);
	
	@Query(value = "select q.user_name from assessment_data q where q.user_name = :userName and q.question_number = :questionNumber", nativeQuery = true)
	String findByKey(@Param("userName") Optional<String> userName, @Param("questionNumber") String questionNumber);
	
	//AssessmentData findByKey(CompositeKey key);
	
	@Transactional
	@Query(value = "select t.score from question_table t where t.id = :questionNumber and t.select_options = :selectedOption " , nativeQuery = true)
	Integer profileScore(@Param("questionNumber") int questionNumber, @Param("selectedOption") String selectedOption);

	@Modifying
	@Transactional
	@Query(value = "update assessment_data a set a.user_name = :userName, a.question_number = :questionNumber, a.selected_option = :selectedOption where a.user_name = :userName and a.question_number = :questionNumber" , nativeQuery = true)
	void updateUserResponse(@Param("userName") Optional<String> userName, @Param("questionNumber") String questionNumber, @Param("selectedOption") String selectedOption);

	@Query(value = "select a.user_name, a.question_number, a.selected_option from assessment_data as a where a.user_name = :email", nativeQuery = true)
	List<AssessmentData> getAssessmentDataByEmail(@Param("email") String email);	

}
