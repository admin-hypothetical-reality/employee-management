package com.ems.config;

import com.ems.model.User;
import com.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if admin already exists
        if (!userRepository.existsByEmail("admin@ems.com")) {
            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@ems.com");
            admin.setPassword("admin123");
            admin.setRole("ADMIN");
            admin.setDepartment("Management");
            admin.setSalary(100000.0);
            admin.setJoinDate(LocalDate.now());
            userRepository.save(admin);

            System.out.println("✓ Admin user created: admin@ems.com / admin123");
        }

        // Check if demo employee already exists
        if (!userRepository.existsByEmail("employee@ems.com")) {
            User employee = new User();
            employee.setName("John Doe");
            employee.setEmail("employee@ems.com");
            employee.setPassword("emp123");
            employee.setRole("EMPLOYEE");
            employee.setDepartment("IT");
            employee.setSalary(50000.0);
            employee.setJoinDate(LocalDate.now());
            userRepository.save(employee);

            System.out.println("✓ Demo employee created: employee@ems.com / emp123");
        }
    }
}
