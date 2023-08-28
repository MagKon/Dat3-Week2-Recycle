package dat3.config.DAO;

import dat3.config.HibernateConfig;
import dat3.model.Driver;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TemporalType;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class DriverDAO extends DAO<Driver>{
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {
        List<Driver> drivers;
        try (EntityManager entityManager = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager()) {
            entityManager.getTransaction().begin();
            drivers = entityManager.createQuery("SELECT d FROM Driver d WHERE YEAR(d.employementDate) = :year")
                    .setParameter("year", year)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        return drivers;
    }

    List<Driver> fetchAllDriversWithSalaryGreaterThan10000() {
        List<Driver> drivers;
        try (EntityManager entityManager = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager()) {
            drivers = entityManager.createQuery("SELECT d FROM Driver d WHERE d.salary > 10000")
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        return drivers;
    }

    double fetchHighestSalary() {
        double highestSalary;
        try (EntityManager entityManager = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager()) {
               highestSalary = Double.parseDouble(entityManager.createQuery("SELECT MAX(d.salary) FROM Driver d")
                    .getSingleResult().toString());
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }

        return highestSalary;
    }
    List<String> fetchFirstNameOfAllDrivers() {
        List<String> firstNames;
        try (EntityManager entityManager = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager()) {
            firstNames = entityManager.createQuery("SELECT d.name FROM Driver d", String.class).getResultList();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        return firstNames;
    }
    long calculateNumberOfDrivers() {
        long numberOfDrivers;
        try (EntityManager entityManager = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager()) {
            numberOfDrivers = (long) entityManager.createQuery("SELECT COUNT(d) FROM Driver d")
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }

        return numberOfDrivers;
    }
    Driver fetchDriverWithHighestSalary() {
        Driver driver;
        try (EntityManager entityManager = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager()) {
            driver = entityManager.createQuery("SELECT d FROM Driver d WHERE d.salary = (SELECT MAX(d.salary) FROM Driver d)", Driver.class)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        return driver;
    }
}
