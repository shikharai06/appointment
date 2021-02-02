package com.docpat.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.docpat.entities.Doctor;
import com.docpat.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	Patient findByEmailIdAndPassword(String emailId, String password);

	Patient findByEmailId(String emailId);

	Patient findByPassword(String password);

	List<Patient> findByDoctor(Doctor doctor);
}
