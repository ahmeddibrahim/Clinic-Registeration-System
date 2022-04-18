package Service;

import Entity.Doctor;

import Repository.DoctorDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    DoctorDao doctorDao = DoctorDao.getInstance();

    /**
     * Singleton instance
     */
    private static DoctorService instance = null;

    public static DoctorService getInstance() {
        if (instance == null)
            instance = new DoctorService();
        return instance;
    }
    public DoctorService(){
        try {
            doctorDao.createDoctorTable();
        } catch (SQLException e) {
            if(e.getErrorCode() != 42101 )
                e.printStackTrace();
        }
    }
    public Doctor login(String username, String password){
        Doctor doctor = null;
        try {
            doctor = doctorDao.validateLogin(username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public Doctor register(String username, String password, String firstname, String lastname,String speciality){
        Doctor doctor = new Doctor(username,password,firstname,lastname,speciality);
        try {
            doctorDao.insertUser(doctor);
        } catch (SQLException e) {
            if(e.getErrorCode() == 23505)
                System.out.println(" Entry already exists with the same username!");
            else
                e.printStackTrace();
            return null;
        }
        return doctor;
    }

    /**
     *
     * @param doctorId
     * @return returns doctor object if found and if not returns null.
     * */
    public Doctor getDoctor(int doctorId){
        Doctor doctor = null;
        try {
            doctor = doctorDao.getDoctorById(doctorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }
    public List<Doctor> getAllDoctors(){
        List<Doctor> doctors = new ArrayList<Doctor>();

        try {
            doctors = doctorDao.getAllUsers();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return doctors;
    }

    public List<Doctor>getDoctorByName(String name){
        List<Doctor> doctors = new ArrayList<Doctor>();

        try {
            doctors = doctorDao.getByName(name);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return doctors;
    }

    public List<Doctor> getDoctorBySpeciality(String speciality){
        List<Doctor> doctors = new ArrayList<Doctor>();

        try {
            doctors = doctorDao.searchBySpeciality(speciality);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return doctors;
    }

    public List<Doctor> getDoctorByAddress(String address){
        List<Doctor> doctors = new ArrayList<Doctor>();

        try {
            doctors = doctorDao.searchByAddress(address);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return doctors;
    }

    public Doctor getDoctorByUsername(String username){
        Doctor doctor = null;
        try {
            doctor = doctorDao.getByUsername(username);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return doctor;
    }

    public boolean updateDoctor(Doctor doctor){
        try {
            doctorDao.updateUser(doctor);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteDoctor(Doctor doctor){
        try {
            doctorDao.deleteUser(doctor.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
