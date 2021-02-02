import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutUsComponent } from './about-us/about-us.component';
import { AppointmentListComponent } from './appointment-list/appointment-list.component';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { DoctorLoginComponent } from './doctor-login/doctor-login.component';
import { DoctorProfileComponent } from './doctor-profile/doctor-profile.component';
import { DoctorRegisterComponent } from './doctor-register/doctor-register.component';
import { HomeComponent } from './home/home.component';
import { HowToUseComponent } from './how-to-use/how-to-use.component';
import { LoginComponent } from './login/login.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { PatientLoginComponent } from './patient-login/patient-login.component';
import { PatientProfileComponent } from './patient-profile/patient-profile.component';
import { PatientRegisterComponent } from './patient-register/patient-register.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SlotBookingComponent } from './slot-booking/slot-booking.component';
import { UpdateDoctorComponent } from './update-doctor/update-doctor.component';
import { UpdatePatientComponent } from './update-patient/update-patient.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'doctorLogin', component: DoctorLoginComponent },
  {path:'patientLogin',component:PatientLoginComponent},
  { path: 'signUp', component: SignUpComponent },
  {path:'doctorRegister',component:DoctorRegisterComponent},
  {path:'patientRegister',component:PatientRegisterComponent},
  {path:'slotBooking',component:SlotBookingComponent},
  {path:'patientList',component:PatientListComponent},
  {path:'doctorList',component:DoctorListComponent},
  {path:'appointmentList',component:AppointmentListComponent},
  {path:'doctorProfile',component:DoctorProfileComponent},
  {path:'patientProfile',component:PatientProfileComponent},
  {path:'updateDoctorDetails',component:UpdateDoctorComponent},
  {path:'updatePatientDetails',component:UpdatePatientComponent},
  {path:'howToUse',component:HowToUseComponent},
  {path:'aboutUs',component:AboutUsComponent},
  {path:'login',component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
