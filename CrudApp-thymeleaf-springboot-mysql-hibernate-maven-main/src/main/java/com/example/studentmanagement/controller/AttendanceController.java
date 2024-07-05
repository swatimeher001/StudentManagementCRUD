package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Attendance;
import com.example.studentmanagement.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public String viewAttendancePage(Model model) {
        List<Attendance> attendanceList = attendanceService.getAllAttendances();
        model.addAttribute("attendanceList", attendanceList);
        return "attendance";
    }

    @GetMapping("/add")
    public String showAddAttendanceForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        return "add-attendance";
    }

    @PostMapping("/save")
    public String saveAttendance(@ModelAttribute("attendance") Attendance attendance) {
        attendance.setDate(LocalDate.now());
        attendanceService.saveAttendance(attendance);
        return "redirect:/attendance";
    }

    @GetMapping("/edit/{id}")
    public String showEditAttendanceForm(@PathVariable Long id, Model model) {
        Attendance attendance = attendanceService.getAttendanceById(id).orElseThrow(() -> new IllegalArgumentException("Invalid attendance Id:" + id));
        model.addAttribute("attendance", attendance);
        return "edit-attendance";
    }

    @PostMapping("/update/{id}")
    public String updateAttendance(@PathVariable Long id, @ModelAttribute("attendance") Attendance attendance) {
        attendanceService.saveAttendance(attendance);
        return "redirect:/attendance";
    }

    @GetMapping("/delete/{id}")
    public String deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return "redirect:/attendance";
    }
}
