package com.example.patientmedapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "doctors")
public class Doctor {

    @Id
    private String id;

    private String name;

    private String specialization;

    private List<LocalDateTime> availableSlots;

    private String schedule;

    public Doctor() {
    }

    public Doctor(String id, String name, String specialization, List<LocalDateTime> availableSlots, String schedule) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.availableSlots = availableSlots;
        this.schedule = schedule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<LocalDateTime> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<LocalDateTime> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
