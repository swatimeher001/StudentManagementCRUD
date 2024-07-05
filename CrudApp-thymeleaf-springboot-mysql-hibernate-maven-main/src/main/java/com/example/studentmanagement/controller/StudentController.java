package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller 
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String viewHomePage() {
        return "home"; // This will render the home.html template
    }

    @GetMapping("/support")
    public String viewSupportPage() { 
        return "support"; // This will render the home.html template
    }


    @GetMapping("/indexpage")  
    public String viewHomePage(Model model) { 
        model.addAttribute("listStudents", studentService.getAllStudents());
        return "indexpage";  
    }

   

    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "new_student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/indexpage";  
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam(value = "id") Long id, Model model) {
        Student student = studentService.getStudentById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "update_student";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam(value = "id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/indexpage";
    }
    
    




    
    
}


