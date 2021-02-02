import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from '../patient.model';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-patient-register',
  templateUrl: './patient-register.component.html',
  styleUrls: ['./patient-register.component.css']
})
export class PatientRegisterComponent implements OnInit {
  patient=new Patient();
  constructor(private patientService:PatientService,private router:Router) { }

  ngOnInit(): void {
  }

  registerPatient(){
    this.patientService.registerPatient(this.patient).subscribe(
      data=>{
        console.log("Response received");
        this.router.navigate(["patientLogin"]);
      },
      error=>console.log("Exception occured")
    )  
    }
    
    }
