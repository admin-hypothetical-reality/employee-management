package com.ems.controller;

import com.ems.model.LeaveRequest;
import com.ems.model.User;
import com.ems.service.LeaveService;
import com.ems.service.PayrollService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private PayrollService payrollService;

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"EMPLOYEE".equals(user.getRole())) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("leaves", leaveService.getLeavesByEmployee(user.getId()));
        model.addAttribute("payrolls", payrollService.getPayrollHistory(user.getId()));

        return "employee-dashboard";
    }

    @PostMapping("/leaves")
    public String applyLeave(@RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String reason,
            HttpSession session) {
        User user = (User) session.getAttribute("user");

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(user);
        leave.setStartDate(LocalDate.parse(startDate));
        leave.setEndDate(LocalDate.parse(endDate));
        leave.setReason(reason);

        leaveService.applyLeave(leave);
        return "redirect:/employee/dashboard";
    }
}
