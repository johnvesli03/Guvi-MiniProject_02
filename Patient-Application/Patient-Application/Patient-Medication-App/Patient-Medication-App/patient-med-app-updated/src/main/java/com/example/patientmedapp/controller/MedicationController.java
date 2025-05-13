package com.example.patientmedapp.controller;

import com.example.patientmedapp.model.Medication;
import com.example.patientmedapp.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/medications")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    public String listMedications(Model model, Principal principal) {
        String username = principal.getName();
        // TODO: Fetch userId by username (will need UserService)
        // For now, assume userId is username for simplicity
        List<Medication> medications = medicationService.getMedicationsByUserId(username);
        model.addAttribute("medications", medications);
        return "medications";
    }

    @GetMapping("/new")
    public String showMedicationForm(Model model) {
        model.addAttribute("medication", new Medication());
        return "medication_form";
    }

    @GetMapping("/edit/{id}")
    public String showEditMedicationForm(@PathVariable("id") String id, Model model) {
        Medication medication = medicationService.getMedicationById(id);
        if (medication != null) {
            model.addAttribute("medication", medication);
            return "medication_form";
        } else {
            return "redirect:/medications";
        }
    }

    @PostMapping
    public String saveMedication(@ModelAttribute Medication medication, Principal principal) {
        String username = principal.getName();
        System.out.println("Saving medication for user: " + username);
        System.out.println("Medication details: " + medication);
        // TODO: Fetch userId by username (will need UserService)
        // For now, assume userId is username for simplicity
        medication.setUserId(username);
        medicationService.saveMedication(medication);
        return "redirect:/medications";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedication(@PathVariable("id") String id) {
        medicationService.deleteMedication(id);
        return "redirect:/medications";
    }
}
