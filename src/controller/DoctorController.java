package Controller;

import Dao.DoctorDao;
import model.Doctor;

public class DoctorController {
    private final DoctorDao dao;

    public DoctorController() {
        this.dao = new DoctorDao();
    }

    public DoctorController(DoctorDao dao) {
        this.dao = dao;
    }

    public boolean addDoctor(String name, String email, String phone, String department) {
        if (email == null || name == null) return false;
        Doctor d = new Doctor(name, email, phone, department);
        return dao.addDoctor(d);
    }

    public boolean deleteDoctor(String email) {
        if (email == null) return false;
        return dao.deleteDoctorByEmail(email);
    }
}