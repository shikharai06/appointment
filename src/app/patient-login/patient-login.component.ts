import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from '../patient.model';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-patient-login',
  templateUrl: './patient-login.component.html',
  styleUrls: ['./patient-login.component.css']
})
export class PatientLoginComponent implements OnInit {
  patient=new Patient();
  message:string;
  constructor(private patientService:PatientService,private router:Router) { }

  ngOnInit(): void {
  }

  loginPatient(){
    this.patientService.loginPatient(this.patient).subscribe(
      data=>{
        console.log("Response received");
        sessionStorage.setItem('patientLogged',this.patient.emailId);
        this.router.navigate(['/appointmentList']).then(() => {
          window.location.reload();
        });;
      },
      error=>{
        console.log("Exception Occured");
        this.message="Invalid Credentials,Please enter valid email and password combination";
      }
    );

  }
}
