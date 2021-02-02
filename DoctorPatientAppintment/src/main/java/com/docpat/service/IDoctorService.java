package com.docpat.service;

import java.util.List;

import com.docpat.entities.Appointment;
import com.docpat.entities.Doctor;

public interface IDoctorService {
	Doctor fetchDoctorByEmailId(String doctorEmailId);

	Doctor fetchDoctorByPassword(String password);

	Doctor authenticateDoctor(String emailId, String password);

	Doctor registerNewDoctor(Doctor d);

	List<Doctor> listDoctors();
	
	List<Appointment> listPatientsAppointed(String doctorEmailId);

	Doctor getDoctorById(int doctorId);
	
	void cancelAppointment(int patientId);
	
	Doctor updateDoctorDetails(Doctor doctor);
	
	Doctor deleteDoctorAccount(String doctorEmailId);
	
	void sendAccountRegisteredEmail(Doctor doctor);
	
}
