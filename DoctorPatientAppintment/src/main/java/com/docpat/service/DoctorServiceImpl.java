package com.docpat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.docpat.dao.AppointmentRepository;
import com.docpat.dao.DoctorRepository;
import com.docpat.dao.PatientRepository;
import com.docpat.entities.Appointment;
import com.docpat.entities.Doctor;
import com.docpat.entities.Patient;

@Service
public class DoctorServiceImpl implements IDoctorService{
	
	@Autowired
	private DoctorRepository doctorRepo;
	@Autowired
	private PatientRepository patientRepo;
	@Autowired
	private AppointmentRepository appointmentRepo;
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public Doctor fetchDoctorByEmailId(String doctorEmailId) {
		return doctorRepo.findByEmailId(doctorEmailId); 
	}

	@Override
	public Doctor fetchDoctorByPassword(String password) {
		return doctorRepo.findByPassword(password);
	}
	@Override
	public Doctor authenticateDoctor(String emailId, String password) {
		return doctorRepo.findByEmailIdAndPassword(emailId, password);
	}

	@Override
	public Doctor registerNewDoctor(Doctor d) {
		doctorRepo.save(d);
		return d;
	}

	@Override
	public List<Doctor> listDoctors() {
		return doctorRepo.findAll();
	}

	@Override
	public List<Appointment> listPatientsAppointed(String doctorEmailId) {
		Doctor d=doctorRepo.findByEmailId(doctorEmailId);
		return appointmentRepo.getAllByDoctor(d.getDoctorId());
	}

	@Override
	public Doctor getDoctorById(int id) {
		return doctorRepo.findById(id).get();
	}

	@Override
	public void cancelAppointment(int appointmentId) {
		Appointment a=appointmentRepo.findById(appointmentId).get();
		SimpleMailMessage mailPatient = new SimpleMailMessage();
		mailPatient.setTo(a.getPatApt().getEmailId());
		mailPatient.setFrom("projectdoctorappointment@gmail.com");
		mailPatient.setSubject("Appointment cancelled by doctor "+a.getDoctor().getName());
		mailPatient.setText("Appointment cancelled successfully your appointment id: " + a.getAppointmentId()+"\n"
				+ "with doctor: " + a.getDoctor().getName()+"\n"
				+" Doctor EmailId: "+a.getDoctor().getEmailId()+"\n"
				+" Doctor Contact Number: "+a.getDoctor().getPhoneNumber()+"\n"
				+ " Date: " + a.getDate()+"\n"
				+ " Time: " + a.getTime());

		javaMailSender.send(mailPatient);
		
		SimpleMailMessage mailDoctor = new SimpleMailMessage();
		mailDoctor.setTo(a.getDoctor().getEmailId());
		mailDoctor.setFrom("projectdoctorappointment@gmail.com");
		mailDoctor.setSubject("Appointment cancelled by doctor "+a.getPatApt().getName());
		mailDoctor.setText("Appointment cancelled appointment id: " + a.getAppointmentId()+"\n"
				+ "with patient: " + "" + a.getPatApt().getName() +"\n"
				+" Patient EmailId: "+a.getPatApt().getEmailId()+"\n"
				+ " Patient Contact Number: "+a.getPatApt().getPhoneNumber()+"\n"
				+ " Date: " + a.getDate()+"\n"
				+ " Time: " + a.getTime());
		javaMailSender.send(mailDoctor);
		Patient p=a.getPatApt();
		p.setDoctor(null);
		patientRepo.save(p);
		a.setDoctor(null);
		a.setPatApt(null);
		appointmentRepo.save(a);
		appointmentRepo.deleteById(appointmentId);
	}

	@Override
	public Doctor updateDoctorDetails(Doctor doctor) {
		doctorRepo.save(doctor);
		return doctor;
	}

	@Override
	public Doctor deleteDoctorAccount(String doctorEmailId) {
		Doctor doctor=doctorRepo.findByEmailId(doctorEmailId);
		doctorRepo.delete(doctor);
		return doctor;
	}

	@Override
	public void sendAccountRegisteredEmail(Doctor doctor) throws MailException {
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo(doctor.getEmailId());
		mail.setFrom("projectdoctorappointment@gmail.com");
		mail.setSubject("Account Registration as a doctor "+doctor.getName());
		mail.setText("Account registered successfully your "+"\n"
				+ "Account id: "+doctor.getDoctorId()+"\n"
				+"password: "+doctor.getPassword());
		javaMailSender.send(mail);
	}







}
