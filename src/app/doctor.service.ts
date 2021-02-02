import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Doctor } from './doctor.model';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  url = "http://localhost:8086/doctor";
  constructor(private http: HttpClient) { }
  public loginDoctor(doctor: Doctor): Observable<any> {
    return this.http.post<any>(this.url + "/authenticateDoctor", doctor);
  }
  public registerDoctor(doctor: Doctor): Observable<any> {
    return this.http.post<any>(this.url + "/registerDoctor", doctor);
  }
  public getAppointedPatientsList(doctorEmailId: string): Observable<any> {
    return this.http.get<any>(this.url + "/getAppointedPatientsList/" + doctorEmailId);
  }
  public cancelAppointment(appointmentId: number): Observable<any> {
    return this.http.post<any>(this.url + "/cancelAppointment",appointmentId);
  }
  getDoctorDetails(doctorEmailId: string): Observable<any> {
    return this.http.get<any>(this.url + "/getDoctorDetails/" + doctorEmailId);
  }
  updateDoctorDetails(doctor: Doctor): Observable<any> {
    return this.http.post<any>(this.url + "/updateDoctorDetails", doctor);
  }
  deleteDoctorAccount(doctorEmailId: string): Observable<any> {
    return this.http.delete<any>(this.url + "/deleteDoctorAccount/" + doctorEmailId);
  }
}
