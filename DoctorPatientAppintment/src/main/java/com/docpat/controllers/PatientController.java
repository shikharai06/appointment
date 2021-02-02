package com.docpat.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docpat.entities.Appointment;
import com.docpat.entities.Doctor;
import com.docpat.entities.Patient;
import com.docpat.service.IDoctorService;
import com.docpat.service.IPatientService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/patient")
public class PatientController {
	@Autowired 
	private IPatientService patientService;
	@Autowired
	private IDoctorService doctorService;

	public PatientController() {
		System.out.println("in Patient controller constr");
	}

	@PostMapping("/registerPatient")
	public Patient registerPatient(@RequestBody Patient patient) throws Exception {
		if (patient.getEmailId() != null && !"".equals(patient.getEmailId())) {
			Patient doctorObj = patientService.fetchPatientByEmailId(patient.getEmailId());
			if (doctorObj != null) {
				throw new Exception("Patient with this email is already exist");
			}
		}
		Patient registeredPatient=patientService.registerNewPatient(patient);
		if(registeredPatient!=null) {
			patientService.sendAccountRegisteredEmail(registeredPatient);
			return registeredPatient;
		}
		return null;
	}

	@PostMapping("/authenticatePatient")
	public Patient authenticatePatient(@RequestBody Patient patient) throws Exception {
		String tempEmailId = patient.getEmailId();
		String tempPassword = patient.getPassword();
		Patient patientObj = null;
		if (tempEmailId != null && tempPassword != null) {
			patientObj = patientService.authenticatePatient(tempEmailId, tempPassword);
		}
		if (patientObj == null) {
			throw new Exception("Patient not Exist with " + tempEmailId);
		}
		return patientObj;
	}

	@GetMapping("/getDoctorsList")
	public List<Doctor> getDoctorList() {
		System.out.println("in fetch doctors list");
		List<Doctor> doctors = patientService.getAllDoctors();
		return doctors;
	}

	@GetMapping("/getDoctorsListBySpecialization/{specialization}")
	public List<Doctor> getDoctorListBySpecialization(@PathVariable("specialization") String specialization) {
		System.out.println("in fetch doctors list by specialization");
		List<Doctor> doctors = patientService.getAllDoctorsBySpecialization(specialization);
		return doctors;
	}

	@GetMapping("/getDoctorsListByHospitalName/{hospitalName}")
	public List<Doctor> getDoctorListByHospitalName(@PathVariable("hospitalName") String hospitalName) {
		System.out.println("in fetch doctors list by hospital name");
		List<Doctor> doctors = patientService.getAllDoctorsByHospitalName(hospitalName);
		return doctors;
	}

	@GetMapping("/getAllSpecializations")
	public List<String> getAllSpecializations() {
		System.out.println(patientService.getAllSpecializations());
		return patientService.getAllSpecializations();
	}

	@GetMapping("/getAllHospitalNames")
	public List<String> getAllHospitalNames() {
		System.out.println(patientService.getAllHospitalNames());
		return patientService.getAllHospitalNames();
	}

	@PostMapping("/createAppointment/{patientEmailId}/{doctorId}")
	public Appointment createAppointment(@RequestBody Appointment appointment,
			@PathVariable("patientEmailId") String patientEmailId, @PathVariable("doctorId") int doctorId)
			throws Exception {
		System.out.println(appointment.getDate() + " " + doctorId + appointment.getTime());
		Doctor doctor = doctorService.getDoctorById(doctorId);
		if (appointment.getDate() != null && appointment.getTime() != null) {
			Appointment appointmentObj = patientService.fetchAppointmentByDateAndTimeAndDoctor(appointment.getDate(),
					appointment.getTime(), doctor);
			if (appointmentObj != null) {
				throw new Exception("Appointment with this date and time and doctor is already exists");
			}
		}
		Appointment a=patientService.createAppointment(appointment, patientEmailId, doctorId);
		if(a!=null) {
			patientService.sendAppointmentScheduledMail(a);
			return a;
		}
		else
			return null;
	}

	@PostMapping("getAppointments/{patientEmailId}")
	public List<Appointment> getAppointments(@PathVariable("patientEmailId") String patientEmailId) {
		return patientService.fetchAppointmentsByPatientId(patientEmailId);
	}

	@PostMapping("/cancelAppointment")
	public void cancelAppointment(@RequestBody int appointmentId) {
		patientService.cancelAppointment(appointmentId);
	}

	@GetMapping("/getScheduledAppointment/{patientEmailId}")
	public Appointment getScheduledAppointments(@PathVariable("patientEmailId") String patientEmailId) {
		return patientService.fetchCurrentAppointment(patientEmailId);
	}

	@GetMapping("/getAppointmentsByDateAndDoctorId/{date}/{doctorId}")
	public List<Appointment> getAppointmentsByDateAndDoctor(@PathVariable("date") Date date,
			@PathVariable("doctorId") int doctorId) {
		return patientService.fetchAppointmentsByDateAndDoctor(date, doctorId);
	}

	@GetMapping("/getPatientDetails/{patientEmailId}")
	public Patient getPatientDetails(@PathVariable("patientEmailId") String patientEmailId) {
		return patientService.fetchPatientByEmailId(patientEmailId);
	}

	@PostMapping("/updatePatientDetails")
	public Patient updatePatintDetails(@RequestBody Patient patient) {
		return patientService.updatePatientDetails(patient);
	}

	@DeleteMapping("/deletePatientAccount/{patientEmailId}")
	public void deletePatientAccount(@PathVariable("patientEmailId") String patientEmailId) {
		patientService.deletePatientAccount(patientEmailId);
	}

}
