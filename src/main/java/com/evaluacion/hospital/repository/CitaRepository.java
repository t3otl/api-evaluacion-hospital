package com.evaluacion.hospital.repository;

import com.evaluacion.hospital.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
}