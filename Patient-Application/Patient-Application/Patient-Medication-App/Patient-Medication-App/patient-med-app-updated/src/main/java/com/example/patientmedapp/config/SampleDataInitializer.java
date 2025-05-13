package com.example.patientmedapp.config;

import com.example.patientmedapp.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleDataInitializer implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(SampleDataInitializer.class);

    private final DoctorService doctorService;

    public SampleDataInitializer(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("SampleDataInitializer running - adding sample doctors");
        doctorService.addSampleDoctors();
    }
}
