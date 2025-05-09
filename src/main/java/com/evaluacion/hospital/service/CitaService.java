package com.evaluacion.hospital.service;

import com.evaluacion.hospital.entity.Cita;
import com.evaluacion.hospital.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CitaService implements ICitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public String agendarCita(Cita nuevaCita) {
        LocalDateTime horario = nuevaCita.getHorario();
        List<Cita> citas = citaRepository.findAll();

        boolean consultorioOcupado = citas.stream().anyMatch(c ->
            c.getConsultorio().getId().equals(nuevaCita.getConsultorio().getId()) &&
            c.getHorario().equals(horario)
        );
        if (consultorioOcupado) {
            return "El consultorio ya tiene una cita en ese horario.";
        }

        boolean doctorOcupado = citas.stream().anyMatch(c ->
            c.getDoctor().getId().equals(nuevaCita.getDoctor().getId()) &&
            c.getHorario().equals(horario)
        );
        if (doctorOcupado) {
            return "El doctor ya tiene una cita en ese horario.";
        }

        boolean pacienteConCitasCercanas = citas.stream().anyMatch(c ->
            c.getNombrePaciente().equalsIgnoreCase(nuevaCita.getNombrePaciente()) &&
            c.getHorario().toLocalDate().equals(horario.toLocalDate()) &&
            Math.abs(ChronoUnit.HOURS.between(c.getHorario(), horario)) < 2
        );
        if (pacienteConCitasCercanas) {
            return "El paciente ya tiene una cita cercana en el mismo día.";
        }

        long citasDoctorHoy = citas.stream().filter(c ->
            c.getDoctor().getId().equals(nuevaCita.getDoctor().getId()) &&
            c.getHorario().toLocalDate().equals(horario.toLocalDate())
        ).count();
        if (citasDoctorHoy >= 8) {
            return "El doctor ya tiene 8 citas programadas para este día.";
        }

        citaRepository.save(nuevaCita);
        return "Cita agendada correctamente.";
    }

    @Override
    public List<Cita> obtenerTodas() {
        return citaRepository.findAll();
    }
}