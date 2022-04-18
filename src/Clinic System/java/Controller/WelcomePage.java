package Controller;

import Entity.*;
import Service.*;

import java.util.List;
import java.util.Scanner;

import static Controller.AddSchedule.addSchedule;
import static Controller.EditProfile.*;
import static Controller.RegisterClinic.registerClinic;
import static Controller.SearchClinics.searchClinics;

public class WelcomePage {

    public static void welcomePatient(Patient patient) {

        System.out.println(" Welcome Patient " + patient.getFirstname() + " to your page\n");

        Scanner in = new Scanner(System.in);
        int input = 0; // Input is set to 0 as a default value before the user inputs values
        boolean status = true; // represents the status of the current state, if false then something went wrong.
        AppointmentService appointmentService = AppointmentService.getInstance();
        do {
            input = 0;
            status = true;
            System.out.println(" Enter 1 to edit your profile\n Enter 2 to search for clinics\n Show all appointments");
            try {
                input = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                System.out.println("try again!");
                in.nextLine();
                status = false;
            }
            switch (input) {
                case 1:
                    editPatientProfile(patient);
                    break;
                case 2:
                    searchClinics(patient);
                    break;
                case 3: {
                    List<Appointment> appointments = appointmentService.getAllAppointmentsForPatient(patient.getUserId());
                    if (appointments.size() > 0)
                        for (Appointment appointment : appointments)
                            System.out.println(appointment);
                    else
                        System.out.println(" You don't have any registered appointments");
                    break;
                }

            }
            System.out.println(" Would you like to show the menu again?\n Enter 0 if yes");
            try {
                input = in.nextInt();
                in.nextLine();
                if (input != 0)
                    status = false;
            } catch (Exception e) {
                System.out.println("try again!");
                in.nextLine();
                status = false;
            }
        } while (status && input != 1 && input != 2);

    }

    public static void welcomeDoctor(Doctor doctor) {
        System.out.println(" Welcome Doctor " + doctor.getFirstname() + " to your page");
        Scanner in = new Scanner(System.in);
        int input = 0; // Input is set to 0 as a default value before the user inputs values
        boolean status = true; // represents the status of the current state, if false then something went wrong.
        AppointmentService appointmentService = AppointmentService.getInstance();
        ClinicService clinicService = ClinicService.getInstance();
        do {
            input = 0;
            status = true;
            System.out.println(" Enter 1 to edit your profile \n Enter 2 to register new clinic\n Show all appointments in your clinic ");
            try {
                input = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                System.out.println("try again!");
                in.nextLine();
                status = false;
            }
            switch (input) {
                case 1:
                    editDoctorProfile(doctor);
                    break;
                case 2:
                    registerClinic(doctor);
                    break;
                case 3: {
                    Clinic clinic = clinicService.getClinicByDoctorId(doctor.getUserId());
                    if (clinic != null) {
                        List<Appointment> appointments = appointmentService.getAllAppointmentsForClinic(clinic.getClinicId());
                        if (appointments.size() > 0)
                            for (Appointment appointment : appointments)
                                System.out.println(appointment);
                        else
                            System.out.println(" You don't have any registered appointments");

                    } else
                        System.out.println(" You are not registered in any clinic, please register a new clinic");
                    break;
                }
            }

            System.out.println(" Would you like to show the menu again?\n Enter 0 if yes");
            try {
                input = in.nextInt();
                in.nextLine();
                if (input != 0)
                    status = false;
            } catch (Exception e) {
                System.out.println("try again!");
                in.nextLine();
                status = false;
            }
        } while (status && input != 1 && input != 2);
    }

    public static void welcomeAdmin(Admin admin) {
        System.out.println(" Welcome Admin " + admin.getFirstname() + " to your page");
        ScheduleService scheduleService = ScheduleService.getInstance();
        ClinicService clinicService = ClinicService.getInstance();
        Scanner in = new Scanner(System.in);
        boolean issue = false;
        do {
            issue = false;
            System.out.println(" To add Schedule for a clinic, please enter the clinicId");
            int clinicId= 0;
            try {
                clinicId = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                System.out.println("try again!");
                in.nextLine();
                issue = true;
            }
            Clinic clinic = clinicService.getClinicById(clinicId);
            if(clinic == null)
            {
                System.out.println("No clinic was found with such an ID, please try again");
                issue = true;
            }
            else
            {addSchedule(clinicId); issue = false;}

        }while(issue);

    }
}
