package com.example.patientmedapp.controller;

import com.example.patientmedapp.model.Appointment;
import com.example.patientmedapp.service.AppointmentService;
import com.example.patientmedapp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
    }

    @GetMapping
    public String listAppointments(Model model, Principal principal) {
        String username = principal.getName();
        // TODO: Fetch userId by username (will need UserService)
        // For now, assume userId is username for simplicity
        List<Appointment> appointments = appointmentService.getAppointmentsByUserId(username);
        model.addAttribute("appointments", appointments);
        model.addAttribute("appointment", new Appointment());
        return "appointments";
    }

    @GetMapping("/new")
    public String showAppointmentForm(Model model) {
        // Add sample doctors if none exist
        if (doctorService.countDoctors() == 0) {
            doctorService.addSampleDoctors();
        }
        model.addAttribute("appointment", new Appointment());
        List doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "appointment_form";
    }

    @PostMapping
    public String saveAppointment(@ModelAttribute Appointment appointment, Principal principal) {
        String username = principal.getName();
        // TODO: Fetch userId by username (will need UserService)
        // For now, assume userId is username for simplicity
        appointment.setUserId(username);
        appointmentService.saveAppointment(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("id") String id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }
    @GetMapping("/view")
    public String viewAppointments(Model model, Principal principal) {
        String username = principal.getName();
        List<Appointment> appointments = appointmentService.getAppointmentsByUserId(username);
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

}

