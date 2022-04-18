package Controller;

import Entity.Doctor;
import Entity.Patient;
import Service.DoctorService;
import Service.NurseService;
import Service.PatientService;

import java.util.Scanner;
import java.util.SortedMap;

public class EditProfile {

    public static void editPatientProfile(Patient patient) {
        Scanner in = new Scanner(System.in);
        int input = 0; // Input is set to 0 as a default value before the user inputs values
        boolean status = true; // represents the status of the current state, if false then something went wrong.
        PatientService patientService = PatientService.getInstance();
        do {
            input = 0;
            status = true;
            System.out.println(" Enter 1 to edit your Username\n Enter 2 to edit your Firstname \n Enter 3 to edit your Lastname  \n Enter 4 to edit your password \nEnter 5 to edit your phone number");
            try {
                input = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                System.out.println("try again!");
                in.nextLine();
                status = false;
            }
            switch (input) {
                case 1 : {
                    System.out.println("Please enter your new Username");
                    String oldUsername = patient.getUsername();
                    String username = in.nextLine();
                    patient.setUsername(username);

                    if(patientService.updatePatient(patient))
                        System.out.println(" Username changed Successfully!!");
                    else{
                        System.out.println(" Operation failed, try again!");
                        input = 0; // reset the while loop
                        status = false;
                        patient.setUsername(oldUsername);
                    }
                    break;
                }
                case 2 : {
                    System.out.println("Please enter your new Firstname");
                    String oldFirstname = patient.getFirstname();
                    String firstname = in.nextLine();
                    patient.setFirstname(firstname);

                    if(patientService.updatePatient(patient))
                        System.out.println(" Firstname changed Successfully!!");
                    else{
                        System.out.println(" Operation failed, try again!");
                        input = 0; // reset the while loop
                        status = false;
                        patient.setFirstname(oldFirstname);
                    }
                    break;
                }
                case 3 : {
                    System.out.println("Please enter your new Lastname");
                    String oldLastname = patient.getLastname();
                    String lastname = in.nextLine();
                    patient.setLastname(lastname);

                    if(patientService.updatePatient(patient))
                        System.out.println(" Lastname changed Successfully!!");
                    else{
                        System.out.println(" Operation failed, try again!");
                        input = 0; // reset the while loop
                        status = false;
                        patient.setFirstname(oldLastname);
                    }
                    break;
                }
                case 4 : {
                    System.out.println("Please enter your new password");
                    String oldPassword = patient.getPassword();
                    String password = in.nextLine();
                    patient.setLastname(password);

                    if(patientService.updatePatient(patient))
                        System.out.println(" Password changed Successfully!!");
                    else{
                        System.out.println(" Operation failed, try again!");
                        input = 0; // reset the while loop
                        status = false;
                        patient.setFirstname(oldPassword);
                    }
                    break;
                }
                case 5 : {
                    System.out.println("Please enter your new Phone Number ");
                    String oldPhoneNumber = patient.getPhoneNumber();
                    String phoneNumber = in.nextLine();
                    patient.setLastname(phoneNumber);

                    if(patientService.updatePatient(patient))
                        System.out.println(" Phone Number changed Successfully!!");
                    else{
                        System.out.println(" Operation failed, try again!");
                        input = 0; // reset the while loop
                        status = false;
                        patient.setFirstname(oldPhoneNumber);
                    }
                    break;
                }
            }

            if(status) // check status, if true then nothing went wrong and can ask to edit more
                // if false then exception was fired.
            {
                System.out.println(" Would like to edit anything else? Enter 0 if yes");
                try {
                    input = in.nextInt();
                    in.nextLine();
                } catch (Exception e) {
                    in.nextLine();
                    input = 1; // If exception happened , then let input = 1  to exit the while loop
                }
                if(input!= 0)
                    input = 1;
            }

        } while (input != 1 && input != 2 && input != 3 && input != 4 && input != 5);
    }


    public static void editDoctorProfile(Doctor doctor){
        Scanner in = new Scanner(System.in);
        int input = 0; // Input is set to 0 as a default value before the user inputs values
        boolean status = true; // represents the status of the current state, if false then something went wrong.
        DoctorService doctorService = DoctorService.getInstance();

        do {
            input = 0;
            status = true;
            System.out.println(" Enter 1 to edit your Username\n Enter 2 to edit your Firstname \n Enter 3 to edit your Lastname  \n Enter 4 to edit your password " +
                    "\nEnter 5 to edit your Speciality \n Enter 6 to add a new Nurse under your name");
            try {
                input = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                System.out.println("try again!");
                in.nextLine();
                status = false;
            }
            switch (input) {
                case 1 : {
                    System.out.println("Please enter your new Username");
                    String oldUsername = doctor.getUsername();
                    String username = in.nextLine();
                    doctor.setUsername(username);

                    if(doctorService.updateDoctor(doctor))
                        System.out.println(" Username changed Successfully!!");
                    else{
                        System.out.println(" Operation failed, try again!");
                        input = 0; // reset the while loop
                        status = false;
                        doctor.setUsername(oldUsername);
                    }
                    break;
                }
                case 2 : {
                    System.out.println("Please enter your new Firstname");
                    String oldFirstname = doctor.getFirstname();
                    String firstname = in.nextLine();
                    doctor.setFirstname(firstname);

                    if(doctorService.updateDoctor(doctor))
                        System.out.println(" Firstname changed Successfully!!");
                    else{
                        System.out.println(" Operation failed, try again!");
                        input = 0; // reset the while loop
                        status = false;
                        doctor.setFirstname(oldFirstname);
                    }
                    break;
                }
                case 3 : {
                    System.out.println("Please enter your new Lastname");
                    String oldLastname = doctor.getLastname();
                    String lastname = in.nextLine();
                    doctor.setLastname(lastname);

                    if(doctorService.updateDoctor(doctor))
                        System.out.println(" Lastname changed Successfully!!");
                    else{
                        System.out.println(" Operation failed, try again!");
                        input = 0; // reset the while loop
                        status = false;
                        doctor.setFirstname(oldLastname);
                    }
                    break;
                }
                case 4 : {
                    System.out.println("Please enter your new password");
                    String oldPassword = doctor.getPassword();
                    String password = in.nextLine();
                    doctor.setLastname(password);

                    if(doctorService.updateDoctor(doctor))
                        System.out.println(" Password changed Successfully!!");
                    else{
                        System.out.println(" Operation failed, try again!");
                        input = 0; // reset the while loop
                        status = false;
                        doctor.setFirstname(oldPassword);
                    }
                    break;
                }
                case 5 : {
                    System.out.println("Please enter your new Speciality ");
                    String oldSpeciality = doctor.getSpeciality();
                    String speciality = in.nextLine();
                    doctor.setLastname(speciality);

                    if(doctorService.updateDoctor(doctor))
                        System.out.println(" Speciality changed Successfully!!");
                    else{
                        System.out.println(" Operation failed, try again!");
                        input = 0; // reset the while loop
                        status = false;
                        doctor.setFirstname(oldSpeciality);
                    }
                    break;
                }
                case 6: {
                    System.out.println(" To add a new Nurse we will require some information");
                    System.out.println(" Pleas enter the Nurse's Firstname");
                    String firstname = in.nextLine();
                    System.out.println(" Please enter the Nurse's Lastname");
                    String lastname = in.nextLine();
                    int doctorId = doctor.getUserId();
                    NurseService nurseService = NurseService.getInstance();
                    boolean addNurse = nurseService.addNurseToDoctor(firstname,lastname,doctorId);
                    if(addNurse)
                        System.out.println(" Nurse added Successfully!");
                    else{
                        System.out.println(" Operation failed, try again!");
                        input = 0; // reset the while loop
                        status = false;
                    }
                    break;
                }
            }

            if(status) // check status, if true then nothing went wrong and can ask to edit more
            // if false then exception was fired.
            {
                System.out.println(" Would like to edit anything else? Enter 0 if yes");
                try {
                    input = in.nextInt();
                    in.nextLine();
                } catch (Exception e) {
                    in.nextLine();
                    input = 1; // If exception happened , then let input = 1  to exit the while loop
                }
                if(input!= 0)
                    input = 1;
            }

        } while (input != 1 && input != 2 && input != 3 && input != 4 && input != 5);

    }
}
