import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { Doctor } from '../doctor.model';
import { DoctorService } from '../doctor.service';

@Component({
  selector: 'app-update-doctor',
  templateUrl: './update-doctor.component.html',
  styleUrls: ['./update-doctor.component.css']
})
export class UpdateDoctorComponent implements OnInit {
  doctor:Doctor;
  doctorEmailId=sessionStorage.getItem('doctorLogged');
  constructor(private doctorService:DoctorService,private router:Router) { }

  ngOnInit(): void {
    this.doctorService.getDoctorDetails(this.doctorEmailId).subscribe(
      data=>{
        console.log("Response received");
        this.doctor=data;
      },
      error=>console.log("Exception occurred")
    )
  }
  updateDoctorDetails(){
    this.doctorService.updateDoctorDetails(this.doctor).subscribe(
      data => {
        console.log("Response received");
        this.router.navigate(['doctorProfile']);
      },
      error => console.log("Exception occured")
    )

    }
  
}

