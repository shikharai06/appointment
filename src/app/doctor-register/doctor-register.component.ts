import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Doctor } from '../doctor.model';
import { DoctorService } from '../doctor.service';

@Component({
  selector: 'app-doctor-register',
  templateUrl: './doctor-register.component.html',
  styleUrls: ['./doctor-register.component.css']
})
export class DoctorRegisterComponent implements OnInit {
  doctor=new Doctor();
  constructor(private doctorService:DoctorService,private router:Router) { }

  ngOnInit(): void {
  }
  registerDoctor(){
    this.doctorService.registerDoctor(this.doctor).subscribe(
      data=>{
        console.log("Response received");
        this.doctor=data;
        this.router.navigate(['doctorLogin']);
      },
      error=>{
        console.log("Exception occurred");
      }
    )
  }
}
