package com.evaluacion.hospital.service;

import com.evaluacion.hospital.entity.Cita;

import java.time.LocalDate;
import java.util.List;

public interface ICitaService {
    String agendarCita(Cita cita);
    List<Cita> obtenerTodas();
    List<Cita> buscarPorCriterios(LocalDate fecha, Long doctorId, Long consultorioId);
    Cita obtenerPorId(Long id);
    void cancelarCita(Long id);
}