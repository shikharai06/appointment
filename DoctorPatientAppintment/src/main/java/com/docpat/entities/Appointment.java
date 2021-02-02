package com.docpat.entities;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Integer appointmentId;
	@Column(name = "appointment_date")
	private Date date;
	@Column(name = "appointment_Time")
	private Time time;
	@ManyToOne
	@JoinColumn(referencedColumnName = "doctor_id")
	private Doctor doctor;
	@OneToOne
	@JoinColumn(referencedColumnName = "patient_id")
	private Patient patApt;

	public Appointment() {
		super();
		System.out.println("In Appointment constructor");
	}

	public Appointment(Integer appointmentId, Date date, Time time, Doctor doctor, Patient patApt) {
		super();
		this.appointmentId = appointmentId;
		this.date = date;
		this.time = time;
		this.doctor = doctor;
		this.patApt = patApt;
	}

	
	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatApt() {
		return patApt;
	}

	public void setPatApt(Patient patApt) {
		this.patApt = patApt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointmentId == null) ? 0 : appointmentId.hashCode());
		result = prime * result + ((patApt == null) ? 0 : patApt.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (appointmentId == null) {
			if (other.appointmentId != null)
				return false;
		} else if (!appointmentId.equals(other.appointmentId))
			return false;
		if (patApt == null) {
			if (other.patApt != null)
				return false;
		} else if (!patApt.equals(other.patApt))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

}
