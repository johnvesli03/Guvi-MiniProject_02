package com.example.patientmedapp.service;

import com.example.patientmedapp.model.Medication;
import com.example.patientmedapp.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public Medication saveMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    public List<Medication> getMedicationsByUserId(String userId) {
        return medicationRepository.findByUserId(userId);
    }

    public Medication getMedicationById(String id) {
        return medicationRepository.findById(id).orElse(null);
    }

    public void deleteMedication(String medicationId) {
        medicationRepository.deleteById(medicationId);
    }

    public long countMedications() {
        return medicationRepository.count();
    }
}
