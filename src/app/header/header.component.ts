import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  doctor = sessionStorage.getItem('doctorLogged');
  patient = sessionStorage.getItem('patientLogged');
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  profile() {
    if (this.doctor)
      this.router.navigate(['doctorProfile']);
    else
      this.router.navigate(['patientProfile']);
  }
  logout() {
    if (this.doctor)
      sessionStorage.removeItem('doctorLogged');
    else
      sessionStorage.removeItem('patientLogged');
    this.router.navigate(['']).then(() => {
      window.location.reload();
    });;
  }
}
