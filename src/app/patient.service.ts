import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointment } from './appointment.model';
import { Doctor } from './doctor.model';
import { Patient } from './patient.model';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  url = "http://localhost:8086/patient";
  constructor(private http: HttpClient) { }

  public loginPatient(patient: Patient): Observable<any> {
    return this.http.post<any>(this.url + "/authenticatePatient", patient);
  }
  public registerPatient(patient: Patient):Observable<any>{
    return this.http.post<any>(this.url + "/registerPatient", patient);
  }
  public getDoctorsList(): Observable<any> {
    return this.http.get<any>(this.url + "/getDoctorsList");
  }
  public getAllSpecializations(): Observable<any> {
    return this.http.get<any>(this.url + "/getAllSpecializations");
  }
  public getAllHospitalNames(): Observable<any> {
    return this.http.get<any>(this.url + "/getAllHospitalNames");
  }
  public getDoctorsListBySpecialization(specialization: string): Observable<any> {
    return this.http.get<any>(this.url + "/getDoctorsListBySpecialization/" + specialization);
  }
  public getDoctorsListByHospitalName(hospitalName: string): Observable<any> {
    return this.http.get<any>(this.url + "/getDoctorsListByHospitalName/" + hospitalName);
  }
  public createAppointment(appointment: Appointment, patientEmailId: string, doctorId: number):Observable<any>{
    return this.http.post<any>(this.url + "/createAppointment/" + patientEmailId + "/" + doctorId, appointment);
  }
  public cancelAppointment(appointmentId: number): Observable<any>{
    return this.http.post<any>(this.url + "/cancelAppointment",appointmentId);
  }
  public getScheduledAppointment(patientEmailId: string): Observable<any> {
    return this.http.get<any>(this.url + "/getScheduledAppointment/" + patientEmailId);
  }
  public getAppointmentsByDateAndDoctor(date: Date, doctorId:number): Observable<any> {
    return this.http.get<any>(this.url + "/getAppointmentsByDateAndDoctorId/" + date + "/" + doctorId);
  }
  getAppointments(patientEmailId: string):Observable<any> {
    return this.http.get<any>(this.url+"/getAppointments/"+patientEmailId);
  }
  getPatientDetails(patientEmailId:string):Observable<any>{
    return this.http.get<any>(this.url+"/getPatientDetails/"+patientEmailId);
  }
  updatePatientDetails(patient:Patient):Observable<any> {
    return this.http.post<any>(this.url+"/updatePatientDetails",patient);
  }
  deletePatientAccount(patientEmailId:string):Observable<any> {
    return this.http.delete<any>(this.url+"/deletePatientAccount/"+patientEmailId);
  }
}