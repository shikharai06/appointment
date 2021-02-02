package com.docpat.controllers;

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
import com.docpat.service.IDoctorService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/doctor")
public class DoctorController {

	
	@Autowired // auto wiring by type
	private IDoctorService doctorService;


	public DoctorController()
	{
		System.out.println("in Doctor controller constr");
	}
	
	@PostMapping("/registerDoctor")
	public Doctor registerDoctor(@RequestBody Doctor doctor) throws Exception {
		System.out.println("in register doctor");
		if (doctor.getEmailId() != null && !"".equals(doctor.getEmailId())) {
			Doctor doctorObj = doctorService.fetchDoctorByEmailId(doctor.getEmailId());
			if (doctorObj != null) {
				throw new Exception("Doctor with this email is already exist");
			}
		}
		Doctor registeredDoctor=doctorService.registerNewDoctor(doctor);
		if(registeredDoctor!=null)
			doctorService.sendAccountRegisteredEmail(registeredDoctor);
		return null;	
	}

	@PostMapping("/authenticateDoctor")
	public Doctor authenticateDoctor(@RequestBody Doctor doctor) throws Exception {
		System.out.println("in authenticate doctor");
		String tempEmailId = doctor.getEmailId();
		String tempPassword =doctor.getPassword();
		Doctor doctorObj = null;
		if (tempEmailId != null && tempPassword != null) {
			doctorObj = doctorService.authenticateDoctor(tempEmailId, tempPassword);
		}
		if (doctorObj == null) {
			throw new Exception("Doctor not Exist with " + tempEmailId);
		}
		return doctorObj;
	}
		
	@GetMapping("/getAppointedPatientsList/{doctorEmailId}")
	public List<Appointment> getAppointedPatientsList(@PathVariable("doctorEmailId") String doctorEmailId) {
		System.out.println("in get patients list");
		List<Appointment> patients = doctorService.listPatientsAppointed(doctorEmailId);
		return patients;
	}
	
	@PostMapping("/cancelAppointment")
	public void cancelAppointment(@RequestBody int appointmentId) {
		doctorService.cancelAppointment(appointmentId);
	
}
	@GetMapping("/getDoctorDetails/{doctorEmailId}")
	public Doctor getDoctorDetails(@PathVariable("doctorEmailId") String doctorEmailId) {
		return doctorService.fetchDoctorByEmailId(doctorEmailId);
	}
	
	
	@PostMapping("/updateDoctorDetails")
	public Doctor updateDoctorDetails(@RequestBody Doctor doctor) {
		return doctorService.updateDoctorDetails(doctor);
	}
	
	@DeleteMapping("/deleteDoctorAccount/{doctorEmailId}")
	public void deleteDoctorAccount(@PathVariable("doctorEmailId") String doctorEmailId)
	{
		doctorService.deleteDoctorAccount(doctorEmailId);
	}
	

}
