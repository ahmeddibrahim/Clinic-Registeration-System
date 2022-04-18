package Controller;

import Entity.Admin;
import Entity.Doctor;
import Entity.Patient;
import Service.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import static Controller.WelcomePage.*;

public class SignUp {
    public static int signUp(){

        Scanner in = new Scanner(System.in);
        int input = 0; // Input is set to 0 as a default value before the user inputs values
        boolean status = true; // represents the status of the current state, if false then something went wrong.

        do {
            input = 0;
            status = true;
            System.out.println(" Welcome to the signing up screen\n Enter 1 If you are a patient \n Enter 2 If you are a doctor \n Enter 3 If you are an administrator \n" +
                    " Enter 0 to go back\n Enter -1 to quit");
            try{
                input = in.nextInt();
                in.nextLine();
            }catch (InputMismatchException e){
                System.out.println("try again!");
                in.nextLine();
                status = false;
            }

            switch (input){
                case 1 :{
                    try {
                        System.out.println(" Please enter your username");
                        String username = in.nextLine();
                        System.out.println(" Please enter your password");
                        String password = in.nextLine();
                        System.out.println(" Please enter your firstname");
                        String firstname = in.nextLine();
                        System.out.println(" Please enter your lastname");
                        String lastname = in.nextLine();
                        System.out.println(" Please enter your phone number");
                        String phoneNumber = in.nextLine();

                        PatientService patientService = PatientService.getInstance();
                        Patient patient = patientService.register(username, password,firstname,lastname,phoneNumber);

                        if(patient != null) // check if the registration was successful or not
                           welcomePatient(patient);
                       else
                           status = false;

                    }catch(Exception e){
                        System.out.println("Something went wrong .. try again!");
                        in.nextLine();
                        input = 0;
                        status = false;
                    }
                    break;
                }
                case 2 :{
                    try {
                        System.out.println(" Please enter your username");
                        String username = in.nextLine();
                        System.out.println(" Please enter your password");
                        String password = in.nextLine();
                        System.out.println(" Please enter your firstname");
                        String firstname = in.nextLine();
                        System.out.println(" Please enter your lastname");
                        String lastname = in.nextLine();
                        System.out.println(" Please enter your specialty");
                        String address = in.nextLine();

                        DoctorService doctorService = DoctorService.getInstance();
                        Doctor doctor =  doctorService.register(username, password,firstname,lastname,address);
                        if(doctor!=null){ // check if the registration was successful or not
                            welcomeDoctor(doctor);
                        }
                        else{
                            status = false;
                        }
                    }catch(Exception e){
                        System.out.println("Something went wrong .. try again!");
                        in.nextLine();
                        input = 0;
                        status = false;
                    }
                    break;
                }
                case 3 :{
                    try {
                        System.out.println(" Please enter your username");
                        String username = in.nextLine();
                        System.out.println(" Please enter your password");
                        String password = in.nextLine();
                        System.out.println(" Please enter your firstname");
                        String firstname = in.nextLine();
                        System.out.println(" Please enter your lastname");
                        String lastname = in.nextLine();

                        AdminService adminService = AdminService.getInstance();
                        Admin admin = adminService.register(username, password,firstname,lastname);
                        if(admin != null){ // check if the registration was successful or not
                            welcomeAdmin(admin);
                        }
                        else{
                            status = false;
                        }
                    }catch(Exception e){
                        System.out.println("Something went wrong .. try again!");
                        in.nextLine();
                        input = 0;
                        status = false;
                    }
                    break;
                }
                case 0 : {
                    if (status) // check status to make sure it's set by the user and not a problem that occurred, as case 0 is also a default input.
                        return 0;
                    break;
                }
                case -1 : return -1;
            }

        }while (status && input!=1 && input!=2 && input!=3);
        return 0;
    }
}

