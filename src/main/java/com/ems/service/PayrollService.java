package com.ems.service;

import com.ems.model.Payroll;
import com.ems.model.User;
import com.ems.repository.PayrollRepository;
import com.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private UserRepository userRepository;

    public Payroll generatePayroll(Long employeeId, String month, Double amount) {
        Optional<User> user = userRepository.findById(employeeId);
        if (user.isEmpty()) {
            throw new RuntimeException("Employee not found");
        }

        Payroll payroll = new Payroll();
        payroll.setEmployee(user.get());
        payroll.setMonth(month);
        payroll.setAmount(amount);
        payroll.setStatus("PENDING");

        return payrollRepository.save(payroll);
    }

    public List<Payroll> getPayrollHistory(Long employeeId) {
        return payrollRepository.findByEmployeeId(employeeId);
    }

    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    public void markAsPaid(Long payrollId) {
        Optional<Payroll> payroll = payrollRepository.findById(payrollId);
        if (payroll.isPresent()) {
            payroll.get().setStatus("PAID");
            payrollRepository.save(payroll.get());
        } else {
            throw new RuntimeException("Payroll record not found");
        }
    }
}
