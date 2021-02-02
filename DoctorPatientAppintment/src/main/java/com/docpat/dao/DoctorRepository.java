package com.docpat.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.docpat.entities.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	Doctor findByEmailIdAndPassword(String emailId, String password);

	Doctor findByEmailId(String emailId);

	Doctor findByPassword(String password);

	@Query("select distinct d.specialization from Doctor d")
	List<String> findAllSpecializations();

	List<Doctor> findAllBySpecialization(String specialization);
	
	@Query("select distinct d.hospitalName from Doctor d")
	List<String> findAllHospitalNames();
	
	List<Doctor> findAllByHospitalName(String hospitalName);
}
