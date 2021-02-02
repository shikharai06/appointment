import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from '../patient.model';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-patient-profile',
  templateUrl: './patient-profile.component.html',
  styleUrls: ['./patient-profile.component.css']
})
export class PatientProfileComponent implements OnInit {
  patient:Patient;
  patientEmailId=sessionStorage.getItem('patientLogged');
  constructor(private patientService:PatientService,private router:Router) { }

  ngOnInit(): void {
    this.patientService.getPatientDetails(this.patientEmailId).subscribe(
      data=>{
        console.log("Response received");
        this.patient=data;
      },
      error=>console.log("Exception occurred")
    )
  }
  deletePatientAccount(){
    this.patientService.deletePatientAccount(this.patientEmailId).subscribe(
      data=>{
        console.log("Respose received");
        this.router.navigate(['']);
      },
      error=> console.log("Exception occured")
    )
    }
  }
