package com.ems.controller;

import com.ems.model.LeaveRequest;
import com.ems.model.Payroll;
import com.ems.model.User;
import com.ems.service.LeaveService;
import com.ems.service.PayrollService;
import com.ems.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private PayrollService payrollService;

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        model.addAttribute("employees", userService.getAllEmployees());
        model.addAttribute("pendingLeaves", leaveService.getAllPendingLeaves());
        model.addAttribute("allLeaves", leaveService.getAllLeaves());
        model.addAttribute("payrolls", payrollService.getAllPayrolls());

        return "admin-dashboard";
    }

    @PostMapping("/employees")
    public String addEmployee(@RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String department,
            @RequestParam Double salary) {
        User employee = new User();
        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setRole("EMPLOYEE");
        employee.setDepartment(department);
        employee.setSalary(salary);
        employee.setJoinDate(LocalDate.now());

        userService.registerUser(employee);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/leaves/{id}/approve")
    public String approveLeave(@PathVariable Long id) {
        leaveService.approveLeave(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/leaves/{id}/reject")
    public String rejectLeave(@PathVariable Long id) {
        leaveService.rejectLeave(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/payroll")
    public String generatePayroll(@RequestParam Long employeeId,
            @RequestParam String month,
            @RequestParam Double amount) {
        payrollService.generatePayroll(employeeId, month, amount);
        return "redirect:/admin/dashboard";
    }
}
