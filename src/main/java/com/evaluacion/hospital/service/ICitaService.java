package com.evaluacion.hospital.service;

import com.evaluacion.hospital.entity.Cita;

import java.util.List;

public interface ICitaService {
    String agendarCita(Cita cita);
    List<Cita> obtenerTodas();
}