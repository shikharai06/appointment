import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Appointment } from '../appointment.model';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-slot-booking',
  templateUrl: './slot-booking.component.html',
  styleUrls: ['./slot-booking.component.css']
})
export class SlotBookingComponent implements OnInit {
  currentDate=new Date();
  date: Date;
  doctorId: number;
  appointments: Appointment[];
  patientEmailId = sessionStorage.getItem('patientLogged');

  times = new Array();
  appointmentTimes = new Array();
  message:string;
  constructor(private patientService: PatientService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        this.doctorId = Number(params['doctorId']);
        console.log(this.doctorId);
      }
    );

  }
  getAppointmentsByDateAndDoctor(date: Date) {
    while (this.times.length) { this.times.pop() };
    while (this.appointmentTimes.length) { this.appointmentTimes.pop() };
    this.patientService.getAppointmentsByDateAndDoctor(date, this.doctorId).subscribe(
      data => {
        console.log("in get appointments by date and doctor" + date);
        console.log("Response received");
        this.appointments = data;
        this.getFreeSlots();
      },
      error => console.log("Exception occurred")
    )
  }

  getFreeSlots() {
    this.appointments.forEach(i => this.appointmentTimes.push(Number(i.time.toString().substr(0, 2))));
    this.appointmentTimes = this.appointmentTimes.sort();
    console.log(this.appointmentTimes);
    for (let i = 10; i <= 18; i++) {
      if (!this.appointmentTimes.includes(i)) {
        this.times.push(i);
      }
    }
  }
  createAppointment(time: Time) {
    console.log(time);
    let appointment = new Appointment(this.date, time);
    console.log(appointment.time);
    this.patientService.createAppointment(appointment, this.patientEmailId, this.doctorId).subscribe(
      data => {
        console.log("Response received");
        this.router.navigate(['appointmentList']);
      },
      error => console.log("Exception occured")
    )
  }
}