import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from '../patient.model';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-update-patient',
  templateUrl: './update-patient.component.html',
  styleUrls: ['./update-patient.component.css']
})
export class UpdatePatientComponent implements OnInit {
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
  updatePatientDetails(){
   this.patientService.updatePatientDetails(this.patient).subscribe(
    data => {
      console.log("Response received");
      this.router.navigate(['patientProfile']);
    },
    error => console.log("Exception occured")
  )
  }

}
