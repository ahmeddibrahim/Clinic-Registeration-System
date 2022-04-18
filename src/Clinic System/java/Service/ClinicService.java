package Service;
import Entity.Clinic;
import Entity.Doctor;

import Repository.ClinicDao;
import Repository.DoctorDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClinicService {

    ClinicDao clinicDao = ClinicDao.getInstance() ;
    DoctorDao doctorDao = DoctorDao.getInstance();

    /**
     * Singleton instance
     */
    private static ClinicService instance = null;

    public static ClinicService getInstance() {
        if (instance == null)
            instance = new ClinicService();
        return instance;
    }
    public ClinicService(){
        try {
            clinicDao.createClinicTable();
        } catch (SQLException e) {
            if(e.getErrorCode() != 42101 )
                e.printStackTrace();
        }
    }

    public boolean registerClinic(String name, String address ,int doctorId){
        try {
            clinicDao.createClinic(new Clinic(name,address,doctorId));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
    *
     * @param clinicName
     * @return returns a list of all clinics with similar name or null if nothing was found
    * */
    public List<Clinic> getClinicByName(String clinicName){
        List<Clinic> clinics = null;
        try {
            clinics = clinicDao.getByName(clinicName.toLowerCase());
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return clinics;
    }

    public List<Clinic> getAllClinics(){
        List<Clinic> clinics = null;
        try {
            clinics = clinicDao.getAllClinics();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return clinics;
    }

    public Clinic getClinicById(int clinicId){
        Clinic clinic = null;
        try {
            clinic = clinicDao.getClinicById(clinicId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clinic;
    }

    public Clinic getClinicByDoctorId(int doctorId){
        Clinic clinic = null;
        try {
            clinic = clinicDao.getClinicByDoctorId(doctorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clinic;
    }

    /**
     *
     * @param doctorName
     * @return returns list of all clinics with similar doctorName
     * */
    public List<Clinic> getClinicByDoctorName(String doctorName){
        List<Clinic> clinics = new ArrayList<Clinic>();
        List<Doctor> doctors = null;

        try {
            doctors = doctorDao.getByName(doctorName);
            for(Doctor doctor : doctors) {
                Clinic clinic = getClinicByDoctorId(doctor.getUserId());
                if(clinic != null)
                    clinics.add(clinic);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return clinics;
    }


    public boolean updateClinic(Clinic clinic){
        try {
            clinicDao.updateClinic(clinic);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean deleteClinic(int clinicId){
        try {
            clinicDao.deleteClinic(clinicId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


}
