import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Doctor } from '../doctor.model';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit {
  doctorsList:Doctor[];
  specializations:string[];
  hospitalNames:string[];
  constructor(private patientService:PatientService,private router:Router) { }

  ngOnInit(): void {
    this.patientService.getDoctorsList().subscribe(
      data=>{
        console.log("Response received")
        this.doctorsList=data
      },
      error=>{
        console.log("Exception occured")
      }
    )
    this.patientService.getAllSpecializations().subscribe(
      data=>{
        console.log("Response received");
        this.specializations=data;
      },
      error=>console.log("Exception occurred")
    )
    this.patientService.getAllHospitalNames().subscribe(
      data=>{
        console.log("Response received");
        this.hospitalNames=data;
      },
      error=>console.log("Exception occurred")
    )
    console.log(this.hospitalNames);

  }
  getDoctorsBySpecialization(specialization:string){
    this.patientService.getDoctorsListBySpecialization(specialization).subscribe(
      data=>{
        console.log("Response recieved");
        this.doctorsList=data;
      },
      error=>console.log("Exception occurred")
    )
  }

  getDoctorsByHospitalName(hospitalName:string){
    this.patientService.getDoctorsListByHospitalName(hospitalName).subscribe(
      data=>{
        console.log("Response recieved");
        this.doctorsList=data;
      },
      error=>console.log("Exception occurred")
    )
  
  }
}
