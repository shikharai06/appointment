package com.docpat.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.docpat.entities.Appointment;
import com.docpat.entities.Doctor;
import com.docpat.entities.Patient;

public interface IPatientService {

	Patient authenticatePatient(String emailId, String password);

	Patient registerNewPatient(Patient P);

	List<Patient> listPatientsByDoctorId(int doctorId);

	List<Doctor> getAllDoctors();

	Doctor updateDId(Doctor d, Appointment a, Patient p);

	Appointment fetchAppointmentByDateAndTimeAndDoctor(Date date, Time time,Doctor doctor);

	Patient fetchPatientByEmailId(String patientEmailId);
	
	Appointment fetchCurrentAppointment(String patientEmailId);

	Appointment createAppointment(Appointment a, String patientEmailID, int doctorID) throws Exception;

	void cancelAppointment(int appointmentId);

	List<Doctor> getAllDoctorsBySpecialization(String specialization);
	
	List<Doctor> getAllDoctorsByHospitalName(String hospitalName);
	
	List<String> getAllSpecializations();
	
	List<String> getAllHospitalNames();
	
	List<Appointment> fetchAppointmentsByDateAndDoctor(Date date,int doctorId);
	
	List<Appointment> fetchAppointmentsByPatientId(String patientEmailId);
	
	Patient updatePatientDetails(Patient patient);
	
	void deletePatientAccount(String patientEmailId);
	
	void sendAccountRegisteredEmail(Patient patient);
	
	void sendAppointmentScheduledMail(Appointment appointment);
	
}
