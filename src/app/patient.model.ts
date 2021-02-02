import { Appointment } from "./appointment.model";
import { Doctor } from "./doctor.model";

export class Patient{
    patientId:string;
    name:string;
    gender:string;
    address:string;
    phoneNumber:string;
    emailId:string;
    password:string;
    doctor:Doctor;
    constructor(){
        
    }
}