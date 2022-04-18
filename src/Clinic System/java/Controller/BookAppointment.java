package Controller;

import Entity.Appointment;
import Entity.Clinic;
import Entity.Patient;
import Entity.Schedule;
import Service.AppointmentService;
import Service.ScheduleService;

import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class BookAppointment {

    public static void bookAppointment(Patient patient, Clinic clinic) {
        Scanner in = new Scanner(System.in);
        ScheduleService scheduleService = ScheduleService.getInstance();
        AppointmentService appointmentService = AppointmentService.getInstance();
        boolean status = false;
        Schedule chosenSchedule = null;

        do {
            status = false;
            List<Schedule> schedules = scheduleService.getSchedulesForClinic(clinic.getClinicId());
            if (schedules.size() > 0) {
                System.out.println(" These are days and working hours for the clinic");
                int count = 1;
                for (Schedule schedule : schedules) {
                    System.out.println(count + " - " + schedule.getDays() + ": From" + schedule.getStartTime() + " till " + schedule.getEndTime());
                    count++;
                }
                System.out.println(" To choose a day, please enter the number next to the corresponding day from the Schedule above");
                int choice = -1;
                try {
                    choice = in.nextInt();
                    in.nextLine();
                } catch (Exception e) {
                    System.out.println("Something went wrong try again");
                    in.nextLine();
                    status = true;
                }

                if (choice > 0 && choice <= schedules.size()) {
                    Schedule schedule = schedules.get(choice-1);
                    System.out.println("You chose the following schedule \n" + schedule);
                    String time = schedule.getStartTime();
                    String day = schedule.getDays();
                    System.out.println(" Enter 1 to book an appointment");
                    choice = -1;
                    try {
                        choice = in.nextInt();
                        in.nextLine();
                    } catch (Exception e) {
                        System.out.println("Something went wrong try again");
                        in.nextLine();
                        status = true;
                    }
                    if(choice == 1){
                        appointmentService.bookAppointment(time,day,clinic.getClinicId(),patient.getUserId());
                    }
                }

            }
        }while(status);
    }
}