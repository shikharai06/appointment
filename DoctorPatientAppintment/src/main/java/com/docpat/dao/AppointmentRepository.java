package com.docpat.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.docpat.entities.Appointment;
import com.docpat.entities.Doctor;
import com.docpat.entities.Patient;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	Appointment findByDateAndTimeAndDoctor(Date date, Time time, Doctor doctor);

	Appointment findByPatApt(Patient patient);
	
	Appointment findByDateAndTimeAndPatApt(Date date,Time time,Patient patApt);
	
	@Query(value = "select * from appointments a where a.doctor_doctor_id=?",nativeQuery = true)
	List<Appointment> getAllByDoctor(int doctorId);

	@Query(value = "select * from doctors d RIGHT JOIN appointments a on d.doctor_id=a.doctor_doctor_id where a.appointment_date=? and"
			+ " a.appointment_time=? and d.doctor_id=?", nativeQuery = true)
	Doctor getDoctorByDateAndTimeAndDoctorId(Date date, Time time, int doctorId);
	
	List<Appointment> findByDateAndDoctor(Date date, Doctor doctor);

	@Query(value = "select * from appointments a RIGHT JOIN patients p on a.appointment_id=p.p_apt_appointment_id where p.email_id=?", nativeQuery = true)
	List<Appointment> getAllAppointmentsByPatientId(String patientEmailId);
	
	@Query(value = "select count(a.appointment_id) from appointments a where a.doctor_doctor_id=? and a.appointment_date=?", nativeQuery = true)
	int getDoctorCountByDate(int doctorId, Date date);
}
