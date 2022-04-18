package Controller;

import Entity.Doctor;
import Service.ClinicService;

import java.util.Scanner;

public class RegisterClinic {
    public static void registerClinic(Doctor doctor){

        Scanner in = new Scanner(System.in);

        System.out.println(" To register your clinic, we will need some Information");
        System.out.println(" Please Enter the clinic's name");
        String name = in.nextLine();
        System.out.println(" Please Enter clinic's address ");
        String address = in.nextLine();
        int doctorId = doctor.getUserId();

        ClinicService clinicService = ClinicService.getInstance();
        boolean register = clinicService.registerClinic(name, address, doctorId);
        if (register)
            System.out.println(" Clinic was registered successfully!");
        else
            System.out.println(" Operation failed, try again later");

    }
}
