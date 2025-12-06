package com.ems.controller;

import com.ems.model.User;
import com.ems.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showRoleSelection() {
        return "index";
    }

    // Admin Login Endpoints
    @GetMapping("/admin/login")
    public String showAdminLoginPage() {
        return "admin-login";
    }

    @PostMapping("/admin/login")
    public String adminLogin(@RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        try {
            User user = userService.authenticate(email, password);

            // Verify user is actually an admin
            if (!"ADMIN".equals(user.getRole())) {
                model.addAttribute("error", "Access denied. Admin credentials required.");
                return "admin-login";
            }

            session.setAttribute("user", user);
            return "redirect:/admin/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid email or password");
            return "admin-login";
        }
    }

    // Employee Login Endpoints
    @GetMapping("/employee/login")
    public String showEmployeeLoginPage() {
        return "employee-login";
    }

    @PostMapping("/employee/login")
    public String employeeLogin(@RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        try {
            User user = userService.authenticate(email, password);

            // Verify user is actually an employee
            if (!"EMPLOYEE".equals(user.getRole())) {
                model.addAttribute("error", "Access denied. Employee credentials required.");
                return "employee-login";
            }

            session.setAttribute("user", user);
            return "redirect:/employee/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid email or password");
            return "employee-login";
        }
    }

    // Legacy login endpoint (kept for backward compatibility)
    @GetMapping("/login")
    public String showLoginPage() {
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        try {
            User user = userService.authenticate(email, password);
            session.setAttribute("user", user);

            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/employee/dashboard";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Invalid email or password");
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
