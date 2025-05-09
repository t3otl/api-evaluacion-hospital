package com.evaluacion.hospital.service;

import com.evaluacion.hospital.entity.Cita;
import com.evaluacion.hospital.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService implements ICitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public String agendarCita(Cita cita) {
        // Validaciones ya implementadas antes
        citaRepository.save(cita);
        return "Cita agendada correctamente.";
    }

    @Override
    public List<Cita> obtenerTodas() {
        return citaRepository.findAll();
    }

    @Override
    public List<Cita> buscarPorCriterios(LocalDate fecha, Long doctorId, Long consultorioId) {
        return citaRepository.findAll().stream()
                .filter(c -> (fecha == null || c.getHorario().toLocalDate().equals(fecha)) &&
                             (doctorId == null || c.getDoctor().getId().equals(doctorId)) &&
                             (consultorioId == null || c.getConsultorio().getId().equals(consultorioId)))
                .collect(Collectors.toList());
    }

    @Override
    public Cita obtenerPorId(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    @Override
    public void cancelarCita(Long id) {
        citaRepository.deleteById(id);
    }
}