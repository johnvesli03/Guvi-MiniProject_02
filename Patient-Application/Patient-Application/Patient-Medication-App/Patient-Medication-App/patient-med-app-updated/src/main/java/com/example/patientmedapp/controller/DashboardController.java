package com.example.patientmedapp.controller;

import com.example.patientmedapp.repository.AppointmentRepository;
import com.example.patientmedapp.service.AppointmentService;
import com.example.patientmedapp.service.DoctorService;
import com.example.patientmedapp.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class DashboardController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final MedicationService medicationService;


    @PostMapping("/login")
    public String login(@RequestParam String username, Model model) {
        // Set the username in the session or add to model
        model.addAttribute("username", username);
        return "dashboard"; //
    }
    @Autowired
    private AppointmentRepository appointmentRepository;

    @RequestMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal.getName();  // Get the logged-in user's name
        model.addAttribute("username", username);  // Add it to the model
        return "dashboard";  // Return the view
    }

    @Autowired
    public DashboardController(AppointmentService appointmentService, DoctorService doctorService, MedicationService medicationService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.medicationService = medicationService;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        long appointment= appointmentRepository();
        long doctorCount = doctorService();
        long medicationCount = medicationService();

        // Fetch list of doctors and add to model
        model.addAttribute("doctors", doctorService.getAllDoctors());

        return "dashboard";
    }

    private long medicationService() {
        return 0;
    }

    private long doctorService() {
        return 0;
    }

    private long appointmentRepository() {
        return 0;
    }
}