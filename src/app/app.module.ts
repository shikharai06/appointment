import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { DoctorLoginComponent } from './doctor-login/doctor-login.component';
import { PatientLoginComponent } from './patient-login/patient-login.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { DoctorRegisterComponent } from './doctor-register/doctor-register.component';
import { PatientRegisterComponent } from './patient-register/patient-register.component';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PatientListComponent } from './patient-list/patient-list.component';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { AppointmentListComponent } from './appointment-list/appointment-list.component';
import { SlotBookingComponent } from './slot-booking/slot-booking.component';
import { DoctorProfileComponent } from './doctor-profile/doctor-profile.component';
import { PatientProfileComponent } from './patient-profile/patient-profile.component';
import { UpdatePatientComponent } from './update-patient/update-patient.component';
import { UpdateDoctorComponent } from './update-doctor/update-doctor.component';
import { HowToUseComponent } from './how-to-use/how-to-use.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DoctorLoginComponent,
    PatientLoginComponent,
    HomeComponent,
    SignUpComponent,
    DoctorRegisterComponent,
    PatientRegisterComponent,
    PatientListComponent,
    DoctorListComponent,
    AppointmentListComponent,
    SlotBookingComponent,
    DoctorProfileComponent,
    PatientProfileComponent,
    UpdatePatientComponent,
    UpdateDoctorComponent,
    HowToUseComponent,
    AboutUsComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
