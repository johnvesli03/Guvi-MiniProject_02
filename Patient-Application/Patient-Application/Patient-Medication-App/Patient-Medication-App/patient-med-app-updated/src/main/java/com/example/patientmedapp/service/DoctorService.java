package com.example.patientmedapp.service;

import com.example.patientmedapp.model.Doctor;
import com.example.patientmedapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    public List<LocalDateTime> getAvailableSlots(String doctorId) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        return doctor.map(Doctor::getAvailableSlots).orElse(List.of());
    }

    public long countDoctors() {
        return doctorRepository.count();
    }

    public void addSampleDoctors() {
        if (doctorRepository.count() == 0) {
            List<Doctor> doctors = new ArrayList<>();

            doctors.add(new Doctor(null, "Dr. Alice Johnson", "Cardiology",
                    List.of(
                            LocalDateTime.parse("2025-05-05T10:00:00"),
                            LocalDateTime.parse("2025-05-06T10:00:00"),
                            LocalDateTime.parse("2025-05-07T10:00:00")
                    ),
                    "Mon Wed Fri"));

            doctors.add(new Doctor(null, "Dr. Sakshi", "Neurology",
                    List.of(
                            LocalDateTime.parse("2025-05-05T11:00:00"),
                            LocalDateTime.parse("2025-05-06T11:00:00"),
                            LocalDateTime.parse("2025-05-07T11:00:00")
                    ),
                    "Tue Thu Sat"));

            doctors.add(new Doctor(null, "Dr. Rejith Matthew Phillips", "Orthopedics, Head of the Department",
                    List.of(
                            LocalDateTime.parse("2025-05-05T09:30:00"),
                            LocalDateTime.parse("2025-05-06T09:30:00"),
                            LocalDateTime.parse("2025-05-07T09:30:00")
                    ),
                    "Mon Tue Wed"));

            doctors.add(new Doctor(null, "Dr. Sridhar", "Pediatrics",
                    List.of(
                            LocalDateTime.parse("2025-05-05T14:00:00"),
                            LocalDateTime.parse("2025-05-06T14:00:00"),
                            LocalDateTime.parse("2025-05-07T14:00:00")
                    ),
                    "Mon Wed Fri"));

            doctors.add(new Doctor(null, "Dr. Sarah Matthew Phillips", "Dermatology",
                    List.of(
                            LocalDateTime.parse("2025-05-05T15:00:00"),
                            LocalDateTime.parse("2025-05-06T15:00:00"),
                            LocalDateTime.parse("2025-05-07T15:00:00")
                    ),
                    "Tue Thu Sat"));

            doctorRepository.saveAll(doctors);
        }
    }
}
