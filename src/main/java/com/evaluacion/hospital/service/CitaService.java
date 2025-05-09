package com.evaluacion.hospital.service;

import com.evaluacion.hospital.entity.Cita;
import com.evaluacion.hospital.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService implements ICitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public String agendarCita(Cita nuevaCita) {
        List<Cita> citasExistentes = citaRepository.findAll();

        // Validación 1: No se puede agendar cita para un mismo doctor a la misma hora
        boolean conflictoDoctor = citasExistentes.stream()
                .anyMatch(c -> c.getDoctor().getId().equals(nuevaCita.getDoctor().getId())
                        && c.getHorario().equals(nuevaCita.getHorario())
                        && !c.getId().equals(nuevaCita.getId())); // excluir misma cita al editar

        if (conflictoDoctor) {
            return "Error: El doctor ya tiene una cita programada a esa hora.";
        }

        // Validación 2: No se puede agendar cita para un paciente en la misma hora o con menos de 2 horas de diferencia el mismo día
        boolean conflictoPaciente = citasExistentes.stream()
                .anyMatch(c -> c.getNombrePaciente().equalsIgnoreCase(nuevaCita.getNombrePaciente())
                        && c.getHorario().toLocalDate().equals(nuevaCita.getHorario().toLocalDate())
                        && Math.abs(ChronoUnit.MINUTES.between(c.getHorario(), nuevaCita.getHorario())) < 120
                        && !c.getId().equals(nuevaCita.getId()));

        if (conflictoPaciente) {
            return "Error: El paciente ya tiene una cita cercana el mismo día (mínimo 2 horas entre citas).";
        }

        // Validación 3: Un doctor no puede tener más de 8 citas al día
        long citasPorDoctorEseDia = citasExistentes.stream()
                .filter(c -> c.getDoctor().getId().equals(nuevaCita.getDoctor().getId())
                        && c.getHorario().toLocalDate().equals(nuevaCita.getHorario().toLocalDate())
                        && !c.getId().equals(nuevaCita.getId()))
                .count();

        if (citasPorDoctorEseDia >= 8) {
            return "Error: El doctor ya tiene el límite de 8 citas para ese día.";
        }
        

        citaRepository.save(nuevaCita);
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
