package dat3;

import dat3.config.DAO.DAO;
import dat3.config.DAO.DriverDAO;
import dat3.model.Driver;
import dat3.model.WasteTruck;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        DriverDAO driverDAO = new DriverDAO();
        DAO<WasteTruck> wasteTruckDAO = new DAO<>();

        List<Driver> drivers = driverDAO.getAll(Driver.class);
        for (Driver driver : drivers) {
            System.out.println(driver.getWasteTruckId());
            // Set random truck between 1 and 5
            WasteTruck wasteTruck = wasteTruckDAO.findById(WasteTruck.class, new Random().nextInt(5) + 1);
            driver.setWasteTruckId(wasteTruck);
            driverDAO.update(driver);
        }

    }
}