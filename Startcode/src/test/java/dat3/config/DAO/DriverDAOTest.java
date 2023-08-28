package dat3.config.DAO;

import dat3.model.Driver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverDAOTest {

    @Test
    void findAllDriversEmployedAtTheSameYear() {
        DriverDAO driverDAO = new DriverDAO();
        List<Driver> drivers = driverDAO.findAllDriversEmployedAtTheSameYear("2023");
        assertEquals(4, drivers.size());
    }

    @Test
    void fetchAllDriversWithSalaryGreaterThan10000() {
        DriverDAO driverDAO = new DriverDAO();
        List<Driver> drivers = driverDAO.fetchAllDriversWithSalaryGreaterThan10000();
        assertEquals(9, drivers.size());
    }

    @Test
    void fetchHighestSalary() {
        DriverDAO driverDAO = new DriverDAO();
        double highestSalary = driverDAO.fetchHighestSalary();
        assertEquals(65800.00, highestSalary);
    }

    @Test
    void fetchFirstNameOfAllDrivers() {
        DriverDAO driverDAO = new DriverDAO();
        List<String> firstNames = driverDAO.fetchFirstNameOfAllDrivers();
        assertEquals(10, firstNames.size());
    }

    @Test
    void calculateNumberOfDrivers() {
        DriverDAO driverDAO = new DriverDAO();
        long numberOfDrivers = driverDAO.calculateNumberOfDrivers();
        assertEquals(10, numberOfDrivers);
    }

    @Test
    void fetchDriverWithHighestSalary() {
        DriverDAO driverDAO = new DriverDAO();
        Driver driver = driverDAO.fetchDriverWithHighestSalary();
        assertEquals("William", driver.getName());
    }
}