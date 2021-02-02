import { Component, OnInit } from '@angular/core';
import { Doctor } from '../doctor.model';
import { DoctorService } from '../doctor.service';

@Component({
  selector: 'app-doctor-profile',
  templateUrl: './doctor-profile.component.html',
  styleUrls: ['./doctor-profile.component.css']
})
export class DoctorProfileComponent implements OnInit {
  doctor:Doctor;
  doctorEmailId=sessionStorage.getItem('doctorLogged');
  constructor(private doctorService:DoctorService) { }

  ngOnInit(): void {
    this.doctorService.getDoctorDetails(this.doctorEmailId).subscribe(
      data=>{
        console.log("Response received");
        this.doctor=data;
      },
      error=>console.log("Exception occurred")
    )
  }
  deleteDoctorAccount(){
    this.doctorService.deleteDoctorAccount(this.doctorEmailId).subscribe(
      data=>{
        console.log("Response received");
        this.doctor=data;    
      },
      error=>console.log("Exception occurred")
    )
  }
}
