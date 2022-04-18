package Service;


import Entity.Patient;

import Repository.PatientDao;

import java.sql.SQLException;
import java.util.List;

public class PatientService {

    private PatientDao patientDao = PatientDao.getInstance();
    /**
     * Singleton instance
     */
    private static PatientService instance = null;

    public static PatientService getInstance() {
        if (instance == null)
            instance = new PatientService();
        return instance;
    }

    public PatientService(){
        try {
            patientDao.createPatientTable();
        } catch (SQLException e) {
            if(e.getErrorCode() != 42101 )
                e.printStackTrace();
        }
    }

    public Patient login(String username, String password){
        Patient patient = null;
        try {
            patient = patientDao.validateLogin(username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public Patient register(String username, String password, String firstname, String lastname,String phoneNumber){
        Patient patient = new Patient(username,password,firstname,lastname,phoneNumber);
        try {
            patientDao.insertUser(patient);
        } catch (SQLException e) {
            if(e.getErrorCode() == 23505)
                System.out.println(" Entry already exists with the same username!");
            else
                e.printStackTrace();
            return null;
        }
        return patient;
    }

    public boolean updatePatient(Patient patient){
        try {
            patientDao.updateUser(patient);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Patient> getAllPatients(){
        List<Patient> patients = null;

        try {
            patients = patientDao.getAllUsers();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return patients;
    }

    public List<Patient>SearchPatientsByName(String name){
        List<Patient> patients = null;

        try {
            patients = patientDao.getByName(name);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return patients;
    }
    public Patient SearchPatientByUsername(String username){
        Patient patient = null;

        try {
            patient = patientDao.getByUsername(username);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return patient;
    }

    public boolean deletePatient(int patientId){
        try {
            patientDao.deleteUser(patientId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
