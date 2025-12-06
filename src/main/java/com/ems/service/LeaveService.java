package com.ems.service;

import com.ems.model.LeaveRequest;
import com.ems.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public LeaveRequest applyLeave(LeaveRequest request) {
        request.setStatus("PENDING");
        return leaveRequestRepository.save(request);
    }

    public List<LeaveRequest> getLeavesByEmployee(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    public List<LeaveRequest> getAllPendingLeaves() {
        return leaveRequestRepository.findByStatus("PENDING");
    }

    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }

    public void approveLeave(Long leaveId) {
        Optional<LeaveRequest> leave = leaveRequestRepository.findById(leaveId);
        if (leave.isPresent()) {
            leave.get().setStatus("APPROVED");
            leaveRequestRepository.save(leave.get());
        } else {
            throw new RuntimeException("Leave request not found");
        }
    }

    public void rejectLeave(Long leaveId) {
        Optional<LeaveRequest> leave = leaveRequestRepository.findById(leaveId);
        if (leave.isPresent()) {
            leave.get().setStatus("REJECTED");
            leaveRequestRepository.save(leave.get());
        } else {
            throw new RuntimeException("Leave request not found");
        }
    }
}
