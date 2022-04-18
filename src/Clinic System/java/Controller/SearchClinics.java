package Controller;

import Entity.Clinic;
import Entity.Doctor;
import Entity.Patient;
import Service.ClinicService;
import Service.DoctorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Controller.BookAppointment.bookAppointment;

public class SearchClinics {

    public static void searchClinics(Patient patient){

        Scanner in = new Scanner(System.in);
        int input = 0; // Input is set to 0 as a default value before the user inputs values.
        ClinicService clinicService = ClinicService.getInstance();
        DoctorService doctorService = DoctorService.getInstance();



        do {
            Clinic chosenClinic = null;
            boolean patientChoseClinic = false;

            input = 0;
            System.out.println(" Enter 1 to Search Clinic by name\n Enter 2 to Search Clinic by doctor name" +
                    "\n Enter 3 to search Clinic by speciality \n Enter 4 to search clinic by address");
            try {
                input = in.nextInt();
                in.nextLine();
            } catch (Exception e) {
                System.out.println("try again!");
                in.nextLine();
            }
            switch (input) {
                case 1 : {
                    System.out.println(" Enter the name of the clinic");
                    String name = in.nextLine();
                    List<Clinic> clinics = clinicService.getClinicByName(name);
                    if(clinics.size()>0)
                    {
                        System.out.println("Clinics Found with similar name ");
                        int count = 1 ;
                        for(Clinic clinic : clinics){
                            System.out.println(count+" -  "+ clinic.getName());
                            count++;
                        }
                        System.out.println(" To choose a clinic from the selection enter the number before its name");
                        int choice = -1;
                        try {
                            choice = in.nextInt();
                            in.nextLine();
                        } catch (Exception e) {
                            System.out.println("try again!");
                            in.nextLine();
                            input = 0; // reset
                        }

                        if(choice>=0 && choice <= clinics.size()) {

                            System.out.println("You chose the following clinic");
                            Clinic clinic = clinics.get(choice-1);
                            System.out.println(clinic);
                            int doctorId = clinic.getDoctorId();
                            Doctor doctor = doctorService.getDoctor(doctorId);
                            System.out.println("Under the supervision of Doctor : "+doctor.getFirstname() + " " + doctor.getLastname());
                            System.out.println("Speciality In : " + doctor.getSpeciality());
                            chosenClinic = clinic;
                            patientChoseClinic = true;
                        }
                        else
                        {
                            System.out.println("try again!");
                            in.nextLine();
                            input = 0; // reset
                        }
                    }
                    else
                        System.out.println(" No clinics found with such a name");
                    break;
                    }

                case 2 : {
                    System.out.println(" Enter the name of the doctor");
                    String name = in.nextLine();
                    List<Clinic> clinics = clinicService.getClinicByDoctorName(name);
                    if(clinics.size()>0)
                    {
                        System.out.println("Clinics Found with similar name ");
                        int count = 1 ;
                        for(Clinic clinic : clinics){
                            System.out.println(count+" -  "+ clinic.getName());
                            count++;
                        }
                        System.out.println(" To choose a clinic from the selection enter the number before its name");
                        int choice = -1;
                        try {
                            choice = in.nextInt();
                            in.nextLine();
                        } catch (Exception e) {
                            System.out.println("try again!");
                            in.nextLine();
                            input = 0; // reset
                        }

                        if(choice> 0 && choice <= clinics.size()) {
                            System.out.println("You chose the following clinic");
                            Clinic clinic = clinics.get(choice-1);
                            System.out.println(clinic);
                            int doctorId = clinic.getDoctorId();
                            Doctor doctor = doctorService.getDoctor(doctorId);
                            System.out.println("Under the supervision of Doctor : "+doctor.getFirstname() + " " + doctor.getLastname());
                            System.out.println("Speciality In : " + doctor.getSpeciality());
                            chosenClinic = clinic;
                            patientChoseClinic = true;
                        }
                        else
                        {
                            System.out.println("try again!");
                            in.nextLine();
                            input = 0; // reset
                        }
                    }
                    else
                        System.out.println(" No clinics found with such a name");
                    break;
                }
                case 3 : {
                    System.out.println(" Enter Speciality");
                    String speciality = in.nextLine();
                    List<Doctor> doctors = doctorService.getDoctorBySpeciality(speciality);
                    List<Clinic> clinics = new ArrayList<Clinic>();
                    if(doctors.size()>0)
                    {
                        System.out.println("Clinics Found with similar speciality ");
                        int count = 1 ;
                        for(Doctor doctor : doctors){
                            Clinic clinic = clinicService.getClinicByDoctorId(doctor.getUserId());
                            System.out.println(count+" -  "+ clinic.getName());
                            count++;
                            clinics.add(clinic);
                        }
                        System.out.println(" To choose a clinic from the selection enter the number before its name");
                        int choice = -1;
                        try {
                            choice = in.nextInt();
                            in.nextLine();
                        } catch (Exception e) {
                            System.out.println("try again!");
                            in.nextLine();
                            input = 0; // reset
                        }

                        if(choice>0 && choice <= clinics.size()) {
                            System.out.println("You chose the following clinic");
                            Clinic clinic = clinics.get(choice-1);
                            System.out.println(clinic);
                            Doctor doctor = doctors.get(choice-1);
                            System.out.println("Under the supervision of Doctor : "+doctor.getFirstname() + " " + doctor.getLastname());
                            System.out.println("Speciality In : " + doctor.getSpeciality());
                            chosenClinic = clinic;
                            patientChoseClinic = true;
                        }
                        else
                        {
                            System.out.println("try again!");
                            in.nextLine();
                            input = 0; // reset
                        }
                    }
                    else
                        System.out.println(" No clinics found with such a name");
                    break;
                }

                case 4 : {
                    System.out.println(" Enter Address");
                    String address = in.nextLine();
                    List<Doctor> doctors = doctorService.getDoctorByAddress(address);
                    List<Clinic> clinics = new ArrayList<Clinic>();
                    if(doctors.size()>0)
                    {
                        System.out.println("Clinics Found with similar Address ");
                        int count = 1 ;
                        for(Doctor doctor : doctors){
                            Clinic clinic = clinicService.getClinicByDoctorId(doctor.getUserId());
                            System.out.println(count+" -  "+ clinic.getName());
                            count++;
                            clinics.add(clinic);
                        }
                        System.out.println(" To choose a clinic from the selection enter the number before its name");
                        int choice = -1;
                        try {
                            choice = in.nextInt();
                            in.nextLine();
                        } catch (Exception e) {
                            System.out.println("try again!");
                            in.nextLine();
                            input = 0; // reset
                        }

                        if(choice>0 && choice <= clinics.size()) {
                            System.out.println("You chose the following clinic");
                            Clinic clinic = clinics.get(choice-1);
                            System.out.println(clinic);
                            Doctor doctor = doctors.get(choice-1);
                            System.out.println("Under the supervision of Doctor : "+doctor.getFirstname() + " " + doctor.getLastname());
                            System.out.println("Speciality In : " + doctor.getSpeciality());
                            chosenClinic = clinic;
                            patientChoseClinic = true;
                        }
                        else
                        {
                            System.out.println("try again!");
                            in.nextLine();
                            input = 0; // reset
                        }
                    }
                    else
                        System.out.println(" No clinics found with such a name");
                    break;
                }
            }

            if(patientChoseClinic && chosenClinic!=null)
            {
                System.out.println(" Enter 1, If you would like to book an clinical appointment with "+ chosenClinic.getName() );
                int choice = 0;
                try {
                    choice = in.nextInt();
                    in.nextLine();
                } catch (Exception e) {
                    System.out.println("try again!");
                    in.nextLine();
                    input = 0; // reset
                }

                if(choice == 1)
                   bookAppointment(patient, chosenClinic);
            }
    }while (input != 1 && input != 2 && input != 3 && input != 4);
}
}
