package com.docpat.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Doctors")
public class Doctor{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private Integer doctorId;
	@Column(length = 20)
	private String name;
	@Column(length = 20)
	private String gender;
	@Column(length = 20)
	private String qualification;
	@Column(length = 20)
	private String specialization;
	@Column(length = 80)
	private String address;
	@Column(length = 10, unique = true)
	private String phoneNumber;
	@Column(length = 50, unique = true)
	private String emailId;
	@Column(length = 10)
	private String password;
	@Column(length = 80, name = "hospital_name")
	private String hospitalName;
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Patient> listofPatient = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private List<Appointment> listofApt = new ArrayList<>();

	public Doctor() {
		System.out.println("In Docter constructor");
	}

	public Doctor(Integer doctorId, String name, String gender, String qualification, String specialization,
			String address, String phoneNumber, String emailId, String password, String hospitalName) {
		super();
		this.doctorId = doctorId;
		this.name = name;
		this.gender = gender;
		this.qualification = qualification;
		this.specialization = specialization;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.password = password;
		this.hospitalName = hospitalName;
	}

	public Doctor(Integer doctorId, String name, String gender, String qualification, String specialization,
			String address, String phoneNumber, String emailId, String password, String hospitalName,
			List<Patient> listofPatient, List<Appointment> listofApt) {
		super();
		this.doctorId = doctorId;
		this.name = name;
		this.gender = gender;
		this.qualification = qualification;
		this.specialization = specialization;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.password = password;
		this.hospitalName = hospitalName;
		this.listofPatient = listofPatient;
		this.listofApt = listofApt;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}


	public List<Patient> getListofPatient() {
		return listofPatient;
	}

	public void setListofPatient(List<Patient> listofPatient) {
		this.listofPatient = listofPatient;
	}

	public List<Appointment> getListofApt() {
		return listofApt;
	}

	public void setListofApt(List<Appointment> listofApt) {
		this.listofApt = listofApt;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", name=" + name + ", gender=" + gender + ", qualification="
				+ qualification + ", specialization=" + specialization + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", emailId=" + emailId + ", listofPatient=" + listofPatient + ", listofApt=" + listofApt
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((doctorId == null) ? 0 : doctorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((listofApt == null) ? 0 : listofApt.hashCode());
		result = prime * result + ((listofPatient == null) ? 0 : listofPatient.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((qualification == null) ? 0 : qualification.hashCode());
		result = prime * result + ((specialization == null) ? 0 : specialization.hashCode());
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
		Doctor other = (Doctor) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (doctorId == null) {
			if (other.doctorId != null)
				return false;
		} else if (!doctorId.equals(other.doctorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (listofApt == null) {
			if (other.listofApt != null)
				return false;
		} else if (!listofApt.equals(other.listofApt))
			return false;
		if (listofPatient == null) {
			if (other.listofPatient != null)
				return false;
		} else if (!listofPatient.equals(other.listofPatient))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (qualification == null) {
			if (other.qualification != null)
				return false;
		} else if (!qualification.equals(other.qualification))
			return false;
		if (specialization == null) {
			if (other.specialization != null)
				return false;
		} else if (!specialization.equals(other.specialization))
			return false;
		return true;
	}

}
