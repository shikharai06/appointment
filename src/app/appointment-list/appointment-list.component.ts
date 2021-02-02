import { Component, OnInit } from '@angular/core';
import { Appointment } from '../appointment.model';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent implements OnInit {
  appointment:Appointment;
  patientEmailId=sessionStorage.getItem('patientLogged');
  constructor(private patientService:PatientService) { }

  ngOnInit(): void {
    this.patientService.getScheduledAppointment(this.patientEmailId).subscribe(
      data=>{
        console.log("Response received");
        this.appointment=data;
      },
      error=>{
        console.log("Exception occurred");
      }
    )
  }

  cancelAppointment(appointmentId:number){
    this.patientService.cancelAppointment(appointmentId).subscribe(
      data=>{
        console.log("Response received");
        window.location.reload();
      },
      error=>{
        console.log("Exception occurred");
      }
    )
    }
}