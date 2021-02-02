import { Time } from "@angular/common";
import { Doctor } from "./doctor.model";
import { Patient } from "./patient.model";

export class Appointment{
    appointmentId:number;
    date:Date;
    time:Time;
    doctor:Doctor;
    patApt:Patient;
    constructor(date:Date,time:Time){
        this.date=date;
        this.time=time;
    }
}