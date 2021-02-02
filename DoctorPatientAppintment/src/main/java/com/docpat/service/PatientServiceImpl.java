package com.docpat.service;

import java.sql.Date;
import java.sql.Time;
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
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepository patientRepo;
	@Autowired
	private DoctorRepository doctorRepo;
	@Autowired
	private AppointmentRepository appointmentRepo;
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public Patient fetchPatientByEmailId(String patientEmailId) {
		return patientRepo.findByEmailId(patientEmailId);
	}

	@Override
	public Patient authenticatePatient(String emailId, String password) {
		Patient p = patientRepo.findByEmailIdAndPassword(emailId, password);
		return p;
	}

	@Override
	public Patient registerNewPatient(Patient p) {
		patientRepo.save(p);
		return p;
	}

	@Override
	public List<Patient> listPatientsByDoctorId(int doctorId) {
		return patientRepo.findAll();
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorRepo.findAll();
	}

	@Override
	public List<Doctor> getAllDoctorsBySpecialization(String specialization) {
		return doctorRepo.findAllBySpecialization(specialization);
	}

	@Override
	public Appointment createAppointment(Appointment appointment, String patientEmailID, int doctorId)
			throws Exception {
		Doctor d = doctorRepo.findById(doctorId).get();
		Patient p = patientRepo.findByEmailId(patientEmailID);
		if (appointmentRepo.getDoctorCountByDate(doctorId, appointment.getDate()) >= 8)
			throw new Exception("All slots are booked for today you can schedule to another date");
		appointment.setDoctor(d);
		p.setDoctor(d);
		p.setpApt(appointment);
		appointment.setPatApt(p);
		patientRepo.save(p);
		return appointment;
	}

	@Override
	public Doctor updateDId(Doctor d, Appointment a, Patient p) {
		p.setDoctor(d);
		p.setpApt(a);
		patientRepo.save(p);
		return d;
	}

	@Override
	public Appointment fetchAppointmentByDateAndTimeAndDoctor(Date date, Time time, Doctor doctor) {
		return appointmentRepo.findByDateAndTimeAndDoctor(date, time, doctor);
	}

	@Override
	public void cancelAppointment(int appointmentId) {
		Appointment a = appointmentRepo.findById(appointmentId).get();
		SimpleMailMessage mailPatient = new SimpleMailMessage();
		mailPatient.setTo(a.getPatApt().getEmailId());
		mailPatient.setFrom("projectdoctorappointment@gmail.com");
		mailPatient.setSubject("Appointment cancelled by patient "+a.getDoctor().getName());
		mailPatient.setText("Appointment cancelled successfully your\n"
				+ "Appointment id: " + a.getAppointmentId()+"\n"
				+ "with doctor: " +a.getDoctor().getName()+"\n"
				+" Doctor EmailId: "+a.getDoctor().getEmailId()+"\n"
				+" Doctor Contact Number: "+a.getDoctor().getPhoneNumber()+"\n"
				+ " Date: " + a.getDate()+"\n"
				+ " Time: " + a.getTime());

		javaMailSender.send(mailPatient);
		
		SimpleMailMessage mailDoctor = new SimpleMailMessage();
		mailDoctor.setTo(a.getDoctor().getEmailId());
		mailDoctor.setFrom("projectdoctorappointment@gmail.com");
		mailDoctor.setSubject("Appointment cancelled by patient "+a.getPatApt().getName());
		mailDoctor.setText("Appointment cancelled "
				+ "Appointment id: " + a.getAppointmentId()+"\n"
				+ "with patient: " + "" + a.getPatApt().getName() 
				+ " Patient EmailId: "+a.getPatApt().getEmailId()+"\n"
				+ " Patient Contact Number: "+a.getPatApt().getPhoneNumber()+"\n"
				+ " Date: " + a.getDate()+"\n"
				+ " Time: " + a.getTime());
		javaMailSender.send(mailDoctor);
		Patient p = a.getPatApt();
		p.setDoctor(null);
		patientRepo.save(p);
		a.setPatApt(null);
		a.setDoctor(null);
		appointmentRepo.save(a);
		appointmentRepo.deleteById(appointmentId);
	}

	@Override
	public Appointment fetchCurrentAppointment(String patientEmailId) {
		Patient p = patientRepo.findByEmailId(patientEmailId);
		if (p.getpApt().getDate().equals(java.util.Calendar.getInstance().getTime())
				|| p.getpApt().getDate().after((java.util.Calendar.getInstance().getTime())))
			return p.getpApt();
		return null;
	}

	@Override
	public List<String> getAllSpecializations() {
		return doctorRepo.findAllSpecializations();
	}

	@Override
	public List<Doctor> getAllDoctorsByHospitalName(String hospitalName) {
		return doctorRepo.findAllByHospitalName(hospitalName);
	}

	@Override
	public List<String> getAllHospitalNames() {
		return doctorRepo.findAllHospitalNames();
	}

	@Override
	public List<Appointment> fetchAppointmentsByDateAndDoctor(Date date, int doctorId) {
		Doctor d = doctorRepo.findById(doctorId).get();
		return appointmentRepo.findByDateAndDoctor(date, d);
	}

	@Override
	public List<Appointment> fetchAppointmentsByPatientId(String patientEmailId) {
		return appointmentRepo.getAllAppointmentsByPatientId(patientEmailId);
	}

	@Override
	public Patient updatePatientDetails(Patient patient) {
		patientRepo.save(patient);
		return patient;
	}

	@Override
	public void deletePatientAccount(String patientEmailId) {
		Patient patient = patientRepo.findByEmailId(patientEmailId);
		patientRepo.delete(patient);
	}

	@Override
	public void sendAccountRegisteredEmail(Patient patient) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(patient.getEmailId());
		mail.setFrom("projectdoctorappointment@gmail.com");
		mail.setSubject("Account Registration as a patient "+patient.getName());
		mail.setText("Account registered successfully your \n Account id: " + patient.getPatientId() + "password: "
				+ patient.getPassword());
		javaMailSender.send(mail);
	}

	@Override
	public void sendAppointmentScheduledMail(Appointment appointment) {
		SimpleMailMessage mailPatient = new SimpleMailMessage();
		mailPatient.setTo(appointment.getPatApt().getEmailId());
		mailPatient.setFrom("projectdoctorappointment@gmail.com");
		mailPatient.setSubject("Appointment scheduled");
		mailPatient.setText("Appointment scheduled successfully your appointment id: " + appointment.getAppointmentId()+"\n"
				+ "with doctor: " + "" + appointment.getDoctor().getName()+" Doctor EmailId: "+appointment.getDoctor().getEmailId()+"\n"
				+" Doctor Contact Number: "+appointment.getDoctor().getPhoneNumber()+"\n"
				+ " Date: " + appointment.getDate()+"\n"
				+ " Time: " + appointment.getTime());

		javaMailSender.send(mailPatient);
		
		SimpleMailMessage mailDoctor = new SimpleMailMessage();
		mailDoctor.setTo(appointment.getDoctor().getEmailId());
		mailDoctor.setFrom("projectdoctorappointment@gmail.com");
		mailDoctor.setSubject("Appointment scheduled");
		mailDoctor.setText("Appointment scheduled appointment id: " + appointment.getAppointmentId()+"\n"
				+ "with patient: " + appointment.getPatApt().getName() +"\n"
				+" Patient EmailId: "+appointment.getPatApt().getEmailId()+"\n"
				+ " Patient Contact Number: "+appointment.getPatApt().getPhoneNumber()+"\n"
				+ " Date: " + appointment.getDate()+"\n"
				+ " Time: " + appointment.getTime());
		javaMailSender.send(mailDoctor);

	}

}
