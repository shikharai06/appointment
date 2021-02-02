import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Doctor } from '../doctor.model';
import { DoctorService } from '../doctor.service';

@Component({
  selector: 'app-doctor-login',
  templateUrl: './doctor-login.component.html',
  styleUrls: ['./doctor-login.component.css']
})
export class DoctorLoginComponent implements OnInit {
  doctor = new Doctor();
  message: string;
  constructor(private doctorService: DoctorService, private router: Router) { }

  ngOnInit(): void {
  }
  loginDoctor() {
    this.doctorService.loginDoctor(this.doctor).subscribe(
      data => {
        console.log("Response received");
        sessionStorage.setItem('doctorLogged', this.doctor.emailId);
        this.router.navigate(["patientList"]).then(() => {
          window.location.reload();
        });;
      },
      error => {
        console.log("Exception Occured");
        this.message = "Invalid Credentials,Please enter valid email and password combination";
      }
    );

  }
}