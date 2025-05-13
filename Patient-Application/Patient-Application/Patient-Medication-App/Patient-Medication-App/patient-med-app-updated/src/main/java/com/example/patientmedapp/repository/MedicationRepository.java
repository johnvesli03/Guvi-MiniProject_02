package com.example.patientmedapp.repository;

import com.example.patientmedapp.model.Medication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends MongoRepository<Medication, String> {
    List<Medication> findByUserId(String userId);
}
