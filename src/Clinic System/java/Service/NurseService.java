package Service;

import Entity.Nurse;
import Repository.NurseDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NurseService {
    NurseDao nurseDao = NurseDao.getInstance();

    /**
     * Singleton instance
     */
    private static NurseService instance = null;

    public static NurseService getInstance() {
        if (instance == null)
            instance = new NurseService();
        return instance;
    }

    public NurseService(){
        try {
            nurseDao.createNurseTable();
        } catch (SQLException e) {
            if(e.getErrorCode() != 42101 )
                e.printStackTrace();
        }
    }

    public boolean addNurseToDoctor(String firstname,String lastname,int doctorId){
        try {
            nurseDao.insertNurse(new Nurse(firstname,lastname,doctorId));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Nurse> getAllNursesWithDoctor(int doctorId){
        List<Nurse> nurses = new ArrayList<Nurse>();
        try {
            nurses = nurseDao.getAllNursesForDoctor(doctorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurses;
    }

    public Nurse getNurseById(int nurseId ){
        Nurse nurse = null;
        try {
            nurse = nurseDao.getNurseById(nurseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurse;
    }
    public List<Nurse> SearchNurseByName( String name ){
        List<Nurse> nurses = new ArrayList<Nurse>();
        try {
            nurses = nurseDao.getByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurses;
    }
    public List<Nurse> getAllNurses(){
        List<Nurse> nurses = new ArrayList<Nurse>();
        try {
            nurses = nurseDao.getAllNurses();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurses;
    }

    public boolean updateNurse(Nurse nurse){
        try {
            nurseDao.updateNurse(nurse);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteNurse(int id){
        try {
            nurseDao.deleteNurse(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    }
