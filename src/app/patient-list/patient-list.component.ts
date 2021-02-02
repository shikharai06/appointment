import { Component, OnInit } from '@angular/core';
import { AppointmentListComponent } from '../appointment-list/appointment-list.component';
import { Appointment } from '../appointment.model';
import { DoctorService } from '../doctor.service';
import { Patient } from '../patient.model';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  appointedPatientsList: Appointment[];
  doctorEmailId = sessionStorage.getItem('doctorLogged');
  constructor(private doctorService: DoctorService) { }

  ngOnInit(): void {
    this.doctorService.getAppointedPatientsList(this.doctorEmailId).subscribe(
      data => {
        console.log("Response recieved")
        this.appointedPatientsList = data;
      },
      error => console.log("Exception occurred")
    )
  }
  cancelAppointment(appointmentId: number) {
    this.doctorService.cancelAppointment(appointmentId).subscribe(
      data => {
        console.log("Respose received");
        window.location.reload();
      },
      error => console.log("Exception occured")
    )
  }

}
