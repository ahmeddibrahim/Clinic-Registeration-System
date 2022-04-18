package Service;

import Entity.Admin;

import Entity.Doctor;
import Repository.AdminDao;


import java.sql.SQLException;

public class AdminService {
    AdminDao adminDao = AdminDao.getInstance();
    /**
     * Singleton instance
     */
    private static AdminService instance = null;

    public static AdminService getInstance() {
        if (instance == null)
            instance = new AdminService();
        return instance;
    }

    public AdminService(){
        try {
            this.adminDao.createAdminTable();
        } catch (SQLException e) {
            if(e.getErrorCode() != 42101 )
                e.printStackTrace();
        }
    }

    public Admin login(String username , String password){
        Admin admin = null;
        try {
            admin = adminDao.validateLogin(username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public Admin register(String username, String password, String firstname, String lastname){
        Admin admin = new Admin(username,password,firstname,lastname);
        try {
            adminDao.insertUser(admin);
        } catch (SQLException e) {
            if(e.getErrorCode() == 23505)
                System.out.println(" Entry already exists with the same username!");
            else
                e.printStackTrace();
            return null;
        }
        return admin;
    }

    public boolean UpdateAdmin(Admin admin){
        try {
            adminDao.updateUser(admin);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteAdmin(int id){
        try {
            adminDao.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
