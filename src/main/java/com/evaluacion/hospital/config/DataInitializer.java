package com.evaluacion.hospital.config;

import com.evaluacion.hospital.entity.Doctor;
import com.evaluacion.hospital.entity.Consultorio;
import com.evaluacion.hospital.repository.DoctorRepository;
import com.evaluacion.hospital.repository.ConsultorioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadInitialData(DoctorRepository doctorRepo, ConsultorioRepository consultorioRepo) {
        return args -> {
            if (doctorRepo.count() == 0) {
                doctorRepo.save(new Doctor(null, "Carlos", "Ramos", "Lopez", "Medicina Interna"));
                doctorRepo.save(new Doctor(null, "Lucia", "Garcia", "Martinez", "Medicina Interna"));
                doctorRepo.save(new Doctor(null, "Andres", "Hernandez", "Sanchez", "Medicina Interna"));
            }

            if (consultorioRepo.count() == 0) {
                consultorioRepo.save(new Consultorio(null, 101, 1));
                consultorioRepo.save(new Consultorio(null, 102, 1));
                consultorioRepo.save(new Consultorio(null, 201, 2));
            }
        };
    }
}
