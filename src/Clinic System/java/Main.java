
import Repository.*;
import Repository.Config.DatabaseConnection;
import Service.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Controller.SignUp.signUp;
import static Controller.UserLogin.login;

public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println("Hello, Welcome to the Clinic Registration System ");

        Scanner in = new Scanner(System.in);
        int inLine = 0;
        do {
            System.out.println(" Would you like to Signup or Login?\n Enter 1 to Signup\n Enter 2 to Login\n Enter -1 to Quit ");
            try{
                inLine = in.nextInt();
            }catch (InputMismatchException e){
                System.out.println("try again!");
                in.nextLine();
            }

            inLine = switch (inLine) {
                case 1 -> signUp();
                case 2 -> login();
                default -> inLine;
            };

        }while(inLine!= 1 && inLine!=2 && inLine != -1);

        System.out.println(" Shutdown ");

    }
}

