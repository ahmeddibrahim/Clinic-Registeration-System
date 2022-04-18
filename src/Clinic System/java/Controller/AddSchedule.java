package Controller;

import Service.ScheduleService;

import java.util.Scanner;

public class AddSchedule {
    public static void addSchedule(int clinicId){
        ScheduleService scheduleService = ScheduleService.getInstance();
        Scanner in = new Scanner(System.in);
        System.out.println(" Please enter the day ");
        String day = in.nextLine();
        System.out.println(" Please enter the starting time");
        String startTime = in.nextLine();
        System.out.println(" Please enter the ending time");
        String endTime = in.nextLine();
        scheduleService.addScheduleToClinic(day,startTime,endTime,clinicId);
    }
}
